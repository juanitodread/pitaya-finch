package org.juanitodread.pitayafinch.routes

import cats.effect.IO
import io.circe.Json
import io.circe.syntax._
import io.finch._
import io.finch.catsEffect._

object Index extends BaseEndpoint {

  def index(): Endpoint[IO, Json] = get(basePath) {
    Ok(Map(
      "message" -> "Please check the API documentation here: https://github.com/juanitodread/pitaya-finch#api-definition").asJson)
  }

  def help(): Endpoint[IO, String] = get(basePath :: "help") {
    Ok("This is the help for root")
  }
}