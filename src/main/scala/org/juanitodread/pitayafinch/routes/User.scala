package org.juanitodread.pitayafinch.routes

import cats.effect.IO
import io.finch._
import io.finch.catsEffect._

import org.juanitodread.pitayafinch.model.{
  Client,
  CommonMessage,
  Emitter
}

object User extends BaseEndpoint {

  private final val usersPath = basePath :: "users"

  def index(): Endpoint[IO, String] = get(usersPath) {
    Ok("Hello User")
  }

  def help(): Endpoint[IO, String] = get(usersPath :: "help") {
    Ok("This is the help for users")
  }

  def message(): Endpoint[IO, CommonMessage] = get(usersPath :: "cm") {
    val emitter = Emitter("facebook")
    Ok(CommonMessage(
      Client(emitter.##.toString, "John Doe", System.currentTimeMillis),
      emitter,
      "simple",
      "this is the body"))
  }

}