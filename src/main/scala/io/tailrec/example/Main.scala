package io.tailrec.example

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author Hussachai Puripunpinyo
  */
object Main extends App with LambdaService {

  val f1 = Future(1)
  val f2 = Future(2)

  val r = for {
    one <- f1
    two <- f2
  } yield {
    one + two
  }

  System.out.println(r.await())

}
