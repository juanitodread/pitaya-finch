package org.juanitodread.pitayafinch.routes

import com.twitter.finagle.Service
import com.twitter.finagle.http.{ Request, Response }
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

import org.juanitodread.pitayafinch.routes.cookbook.Users

object Endpoints {
  def toService(): Service[Request, Response] = Bootstrap
    .serve[Text.Plain](
      Index.index() :+:
        Index.help())
    .serve[Text.Plain](
      User.index() :+:
        User.help())
    .serve[Application.Json](
      User.message())
    .serve[Application.Json](
      Users.getUsers() :+:
        Users.getUser() :+:
        Users.postUser() :+:
        Users.patchUser() :+:
        Users.deleteUser())
    .toService
}