package io.tailrec.example

import java.io.File
import java.util.Base64

import com.amazonaws.auth.{AWSCredentialsProvider, DefaultAWSCredentialsProviderChain}
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.lambda.AWSLambdaClient
import com.amazonaws.services.lambda.model.{InvocationType, InvokeRequest, LogType}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, FunSuite}

/**
  * @author Hussachai Puripunpinyo
  */
class SimpleServiceTest extends FunSuite with BeforeAndAfterAll {

  val config = ConfigFactory.parseFile(new File("severus.conf"))
  val credentialsProvider = new DefaultAWSCredentialsProviderChain()
  val client = new AWSLambdaClient(credentialsProvider)
  val region = Region.getRegion(Regions.fromName(config.getString("aws.lambda.default.region")))
  client.setRegion(region)

  override def afterAll() = {
    client.shutdown()
  }

//  test("hello function should return 'Hello lambda'") {
//    val request = new InvokeRequest()
//    request.setFunctionName("hello")
//    request.setInvocationType(InvocationType.RequestResponse)
//    request.setLogType(LogType.Tail)
//    request.setPayload("")
//
//    val result = client.invoke(request)
//    println("Log result: " + new String(Base64.getDecoder.decode(result.getLogResult), "UTF-8"))
//    println("Function error: " + result.getFunctionError)
//    println("Status code: " + result.getStatusCode)
//    println("============================")
//    println(new String(result.getPayload.array(), "UTF-8"))
//
//    succeed
//  }

  test("hello function should return 'Hello lambda' 2") {
    val request = new InvokeRequest()
    request.setFunctionName("delayVersion1")
    request.setInvocationType(InvocationType.RequestResponse)
    request.setLogType(LogType.Tail)
//    request.setPayload("\"124.122.182.123\"")

    val result = client.invoke(request)
    println("Log result: " + new String(Base64.getDecoder.decode(result.getLogResult), "UTF-8"))
    println("Function error: " + result.getFunctionError)
    println("Function payload: " + result.getPayload)
    println("Status code: " + result.getStatusCode)
    println("============================")
    println(new String(result.getPayload.array(), "UTF-8"))

    succeed
  }
}
