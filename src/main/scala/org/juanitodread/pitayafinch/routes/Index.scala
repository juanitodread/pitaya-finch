package org.juanitodread.pitayafinch.routes

import cats.effect.IO
import io.finch._
import io.finch.catsEffect._

import org.juanitodread.pitayafinch.utils.AppConf

object Index extends AppConf {
  final val basePath = serverCtx :: api :: version

  def index(): Endpoint[IO, String] = get(basePath) {
    Ok("Hello World")
  }

  def help(): Endpoint[IO, String] = get(basePath :: "help") {
    Ok("This is the help for root")
  }

}