package org.juanitodread.pitayafinch.routes

import io.finch._
import com.twitter.finagle.http.Response.Ok
import org.juanitodread.pitayafinch.utils.AppConf
import org.juanitodread.pitayafinch.model._

object User extends AppConf {
  final val basePath = serverCtx :: api :: version :: "users"

  def index(): Endpoint[String] = get(basePath) {
    Ok("Hello User")
  }

  def help(): Endpoint[String] = get(basePath :: "help") {
    Ok("This is the help for users")
  }

  def message(): Endpoint[CommonMessage] = get(basePath :: "cm") {
    val emitter = Emitter("facebook")
    val client = Client(emitter.##.toString, "Jhon Doe", System.currentTimeMillis)
    val commonMessage = CommonMessage(client, emitter, "simple", "this is the body")
    println(s"CommonMessage => $commonMessage")
    Ok(commonMessage)
  }

}