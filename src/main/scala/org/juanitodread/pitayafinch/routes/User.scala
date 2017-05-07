package org.juanitodread.pitayafinch.routes

import io.finch._
import com.twitter.finagle.http.Response.Ok
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.HttpResponse
import com.twitter.finagle.http.Method
import com.twitter.finagle.http.path.Root
import org.juanitodread.pitayafinch.utils.AppConf

object User extends AppConf {
  final val basePath = serverCtx :: api :: version :: "users"

  def index(): Endpoint[String] = get(basePath) {
    Ok("Hello User")
  }

  def help(): Endpoint[String] = get(basePath :: "help") {
    Ok("This is the help for users")
  }

}