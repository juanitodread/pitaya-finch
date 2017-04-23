package org.juanitodread.pitayafinch

import com.twitter.finagle.Http
import com.twitter.util.Await

import io.finch._
import io.finch.circe._
import org.juanitodread.pitayafinch.routes.EndpointsUtil

object App {
  def main(args: Array[String]): Unit = {
    println("Hello World")

    val api0: Endpoint[String] = get("hello") { Ok("Hello, World!") }
    val api1: Endpoint[String] = get("hello1") { Ok("Hello, World!") }
    val api = api0 :+: api1

    Await.ready(Http.serve(":8989", EndpointsUtil.toService))
  }
}