package io.tailrec.example

import java.util.Optional

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.tailrec.example.utils.JsonSupport

/**
  * @author Hussachai Puripunpinyo
  */
object DogSon extends App with JsonSupport {

//  println(mapper.writeValueAsString(new Dog("Bob", Optional.of("Brown"))))

  case class Hello(query: String)

  val json =
    """
      |{
      | "query": "something"
      |}
    """.stripMargin

//  println(mapper.readValue(json, classOf[IpInfo]).getQuery)
  println(readJson(json, classOf[IpInfo]))
  println(readJson(json, classOf[Hello]))
}
