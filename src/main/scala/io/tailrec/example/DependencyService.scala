package io.tailrec.example

import java.util.Base64
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.lambda.AWSLambdaClient
import com.amazonaws.services.lambda.model.{InvocationType, InvokeRequest, LogType}
import com.typesafe.config.ConfigFactory
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.RandomStringUtils

/**
  * Created by Hussachai on 1/11/2017.
  */
class DependencyService {

  val config = ConfigFactory.load()
  val credentialsProvider = new DefaultAWSCredentialsProviderChain()
  val client = new AWSLambdaClient(credentialsProvider)
  val region = Region.getRegion(Regions.fromName(config.getString("aws.lambda.default.region")))
  client.setRegion(region)

  def getRandomString(): String = {
    RandomStringUtils.randomAlphabetic(64)
  }

  def md5RemoteRandomString(): String = {
    val request = new InvokeRequest()
    request.setFunctionName("getRandomString")
    request.setInvocationType(InvocationType.RequestResponse)
    request.setLogType(LogType.Tail)
    val result = client.invoke(request)
    val randomStr = new String(Base64.getDecoder.decode(result.getLogResult), "UTF-8")
    DigestUtils.md5Hex(randomStr)
  }

  def md5LocalRandomString(): String = {
    DigestUtils.md5Hex(getRandomString())
  }

}
