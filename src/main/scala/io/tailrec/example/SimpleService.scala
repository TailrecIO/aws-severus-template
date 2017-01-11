package io.tailrec.example

import com.amazonaws.services.lambda.runtime.Context

import scala.concurrent.Future

class SimpleService {

  def echo(text: String, ctx: Context): String = {
    s"Hello ${text}"
  }

  def hello(ctx: Context): String = {
    "Hello SimpleService"
  }

}
