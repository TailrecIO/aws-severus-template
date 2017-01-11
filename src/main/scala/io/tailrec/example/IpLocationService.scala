package io.tailrec.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import com.typesafe.config.ConfigFactory

import scala.math._
import io.tailrec.example.utils.JavaTypeConveters._
import io.tailrec.example.utils.JsonSupport

import scala.concurrent.Future

/**
  * Using Akka in AWS Lambda service is definitely a bad idea, but it doesn't mean that we cannot use it.
  * Creating ActorSystem every time the function get executed is certainly not recommended.
  * We know it is bad but what we want to find out is how bad it is.
  *
  * If it does work, it means that you can use threads in AWS Lambda.
  * No, not just threads, but also a thread pool. Actually, the full blow akka solution is allowed.
  *
  */
class IpLocationService extends LambdaService with JsonSupport {

  val earthRadius = 6371.0

  implicit val system = ActorSystem()

  implicit val executor = system.dispatcher

  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load()

  lazy val ipApiConnectionFlow: Flow[HttpRequest, HttpResponse, Any] =
    Http().outgoingConnection(config.getString("services.ip-api.host"), config.getInt("services.ip-api.port"))

  private def fetchIpInfo(ip: String): Future[IpInfo] = {

    def ipApiRequest(request: HttpRequest) = Source.single(request).via(ipApiConnectionFlow).runWith(Sink.head)

    ipApiRequest(RequestBuilding.Get(s"/json/$ip")).flatMap { response =>
      response.status match {
        case OK => Unmarshal(response.entity).to[String].map(p =>readJson(p, classOf[IpInfo]))
        case BadRequest => sys.error("Bad request")
        case _ => Unmarshal(response.entity).to[String].flatMap { entity =>
          sys.error(s"FreeGeoIP request failed with status code ${response.status} and entity $entity")
        }
      }
    }
  }

  def getIpInfo(ip: String): IpInfo = {
    fetchIpInfo(ip).await()
  }

  def calculateDistance(ipPairSummaryRequest: IpPairSummaryRequest): IpPairSummary = {

    def calculate(ip1Info: IpInfo, ip2Info: IpInfo): Option[Double] = {
      (ip1Info.getLat.asScala, ip1Info.getLon.asScala, ip2Info.getLat.asScala, ip2Info.getLon.asScala) match {
        case (Some(lat1), Some(lon1), Some(lat2), Some(lon2)) =>
          // see http://www.movable-type.co.uk/scripts/latlong.html
          val φ1 = toRadians(lat1)
          val φ2 = toRadians(lat2)
          val Δφ = toRadians(lat2 - lat1)
          val Δλ = toRadians(lon2 - lon1)
          val a = pow(sin(Δφ / 2), 2) + cos(φ1) * cos(φ2) * pow(sin(Δλ / 2), 2)
          val c = 2 * atan2(sqrt(a), sqrt(1 - a))
          Option(earthRadius * c)
        case _ => None
      }
    }

    val futureResult = for {
      info1 <- fetchIpInfo(ipPairSummaryRequest.getIp1)
      info2 <- fetchIpInfo(ipPairSummaryRequest.getIp2)
    } yield {
      new IpPairSummary(calculate(info1, info2).map{ d => new java.lang.Double(d.toString)}.asJava, info1, info2)
    }

    futureResult.await()
  }

}
