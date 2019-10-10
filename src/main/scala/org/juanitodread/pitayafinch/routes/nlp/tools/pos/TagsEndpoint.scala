package org.juanitodread.pitayafinch.routes.nlp.tools.pos

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.pos.{ TagsRequest, TagsResponse }
import org.juanitodread.pitayafinch.nlp.tools.pos.Tagger
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object TagsEndpoint extends NlpEndpoint {
  private final val tagsPath = basePath :: "pos" :: "tags"

  private val postedTags: Endpoint[IO, TagsRequest] = jsonBody[TagsRequest]
  def tags(): Endpoint[IO, TagsResponse] = post(tagsPath :: postedTags) { request: TagsRequest =>
    Ok(TagsResponse(
      request.text,
      Tagger(request.text, request.chunk)))
  }
}
