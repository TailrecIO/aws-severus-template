package io.tailrec.example

import org.apache.commons.lang3.RandomStringUtils

import scala.util.Random

/**
  * Created by Hussachai on 1/11/2017.
  */
class DelayedInitService {

  lazy val initData = {
    val str = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).mkString
    (1 to 5000000).map(_ => Random.nextInt(str.length)).mkString
  }

  lazy val lazyInitData = {
    Thread.sleep(3000)
    List(1, 2, 3)
  }

  def delaySum(): String = {
    (initData.sum + lazyInitData.sum).toString
  }

  def delayVersion(): String = {
    s"1.0-${RandomStringUtils.randomAlphabetic(12)}"
  }

}
