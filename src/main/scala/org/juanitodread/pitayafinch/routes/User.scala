package org.juanitodread.pitayafinch.routes

import io.finch._
import com.twitter.finagle.http.Response.Ok

object User {
  final val basePath = / + "user" + /

  def index(): Endpoint[String] = get(basePath) {
    Ok("Hello User")
  }

  def help(): Endpoint[String] = get(basePath + / + "help") {
    Ok("This is the help for users")
  }

}