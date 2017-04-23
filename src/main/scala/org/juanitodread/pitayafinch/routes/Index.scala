package org.juanitodread.pitayafinch.routes

import io.finch._
import com.twitter.finagle.http.Response.Ok

object Index {
  final val basePath = /

  def index(): Endpoint[String] = get(basePath) {
    Ok("Hello World")
  }

  def help(): Endpoint[String] = get(basePath + "help") {
    Ok("This is the help for root")
  }

}