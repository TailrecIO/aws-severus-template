package io.tailrec.example.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * @author Hussachai Puripunpinyo
  */
private[this] object JsonSupport {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.registerModule(new Jdk8Module)

}

trait JsonSupport {

  def readJson[T](json:String, targetType: Class[T]): T = {
    JsonSupport.mapper.readValue(json, targetType)
  }

  def writeJson(obj: AnyRef): String = {
    JsonSupport.mapper.writeValueAsString(obj)
  }

}