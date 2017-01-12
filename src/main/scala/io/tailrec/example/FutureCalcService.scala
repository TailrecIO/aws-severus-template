package io.tailrec.example

import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Hussachai on 1/11/2017.
  */
class FutureCalcService extends LambdaService {

  def plus(input: NumberAB): String = {
    (input.getA + input.getB).toString
  }

  def minus(input: NumberAB): String = {
    (input.getA - input.getB).toString
  }

  def factorial(input: NumberAB): String = {
    @tailrec
    def fact(current: Int, acc: BigInt = 1): BigInt = {
      if(current == 0) acc
      else fact(current -1, current * acc)
    }
    (for {
      r1 <- Future { fact(input.getA)}
      r2 <- Future { fact(input.getB)}
    } yield {
      (r1, r2)
    }).await().toString()
  }

}
