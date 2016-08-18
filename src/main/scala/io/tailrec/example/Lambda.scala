package io.tailrec.example

import com.amazonaws.services.lambda.runtime.Context

class Lambda {

  def handleRequest1(ctx: Context): String = {
    "Hello Function 11"
  }

  def handleRequest2(ctx: Context): String = {
    "Hello Function 2"
  }

  def handleRequest3(ctx: Context): String = {
    "Hello Function 3"
  }

}
