package org.juanitodread.pitayafinch.routes

import com.twitter.finagle.Service
import com.twitter.finagle.http.{ Request, Response }
import io.finch._

import io.circe.generic.auto._
import io.finch.circe._

object Endpoints {
  def toService(): Service[Request, Response] = Bootstrap
    .serve[Text.Plain](Index.index() :+: Index.help())
    .serve[Text.Plain](User.index() :+: User.help())
    .serve[Application.Json](User.message())
    .toService
}