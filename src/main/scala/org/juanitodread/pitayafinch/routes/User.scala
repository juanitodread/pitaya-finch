package org.juanitodread.pitayafinch.routes

import cats.effect.IO
import io.finch._
import io.finch.catsEffect._

import org.juanitodread.pitayafinch.model.{ Client, CommonMessage, Emitter }
import org.juanitodread.pitayafinch.utils.AppConf

object User extends AppConf {
  final val basePath = serverCtx :: api :: version :: "users"

  def index(): Endpoint[IO, String] = get(basePath) {
    Ok("Hello User")
  }

  def help(): Endpoint[IO, String] = get(basePath :: "help") {
    Ok("This is the help for users")
  }

  def message(): Endpoint[IO, CommonMessage] = get(basePath :: "cm") {
    val emitter = Emitter("facebook")
    val client = Client(emitter.##.toString, "John Doe", System.currentTimeMillis)
    val commonMessage = CommonMessage(client, emitter, "simple", "this is the body")
    Ok(commonMessage)
  }

}