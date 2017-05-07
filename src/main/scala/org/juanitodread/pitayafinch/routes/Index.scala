package org.juanitodread.pitayafinch.routes

import io.finch._
import com.twitter.finagle.http.Response.Ok
import org.juanitodread.pitayafinch.utils.AppConf

object Index extends AppConf {

  final val basePath = serverCtx :: api :: version

  def index(): Endpoint[String] = get(basePath) {
    Ok("Hello World")
  }

  def help(): Endpoint[String] = get(basePath :: "help") {
    Ok("This is the help for root")
  }

}