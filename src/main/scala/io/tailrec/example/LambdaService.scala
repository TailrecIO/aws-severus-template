package io.tailrec.example

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.duration._

/**
  * @author Hussachai Puripunpinyo
  */
trait LambdaService {

  implicit class FutureAwait[T](future: Future[T]) {
    def await(duration: Duration = 1.minute): T = {
      Await.result(future, duration)
    }
  }

}
