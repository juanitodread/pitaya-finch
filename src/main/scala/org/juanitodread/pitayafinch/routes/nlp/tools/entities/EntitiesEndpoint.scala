package org.juanitodread.pitayafinch.routes.nlp.tools.entities

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.ResourceNotAvailable
import org.juanitodread.pitayafinch.model.nlp.entities._
import org.juanitodread.pitayafinch.nlp.tools.entities._
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint
import org.juanitodread.pitayafinch.utils.AppConf

object EntitiesEndpoint extends NlpEndpoint with AppConf {
  private final val entitiesPath = basePath :: "entities"

  private val postedEntities: Endpoint[IO, EntitiesRequest] = jsonBody[EntitiesRequest]
  def getEntities(): Endpoint[IO, EntitiesResponse] = post(entitiesPath :: postedEntities) { request: EntitiesRequest =>
    if (lowResourcesMode) {
      Conflict(ResourceNotAvailable(message = "This resource is not allowed when running in low resources mode"))
    } else {
      Ok(EntitiesResponse(
        request.text,
        EntityRecognizer(request.text)))
    }
  }
}
