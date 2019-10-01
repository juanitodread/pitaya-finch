package org.juanitodread.pitayafinch.routes.nlp.tools.entities

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.entities._
import org.juanitodread.pitayafinch.nlp.tools.entities.EntityRecognizer
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object EntitiesEndpoint extends NlpEndpoint {
  private final val entitiesPath = basePath :: "entities"

  private val postedEntities: Endpoint[IO, EntitiesRequest] = jsonBody[EntitiesRequest]
  def getEntities(): Endpoint[IO, EntitiesResponse] = post(entitiesPath :: postedEntities) { request: EntitiesRequest =>
    Ok(EntitiesResponse(
      request.text,
      EntityRecognizer(request.text)))
  }
}
