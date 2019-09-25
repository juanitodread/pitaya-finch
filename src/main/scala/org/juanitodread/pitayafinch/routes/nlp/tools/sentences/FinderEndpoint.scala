package org.juanitodread.pitayafinch.routes.nlp.tools.sentences

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.sentences.{ FinderRequest, FinderResponse }
import org.juanitodread.pitayafinch.nlp.tools.sentences.Finder
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object FinderEndpoint extends NlpEndpoint {
  private final val finderPath = basePath :: "sentences" :: "find"

  private val postedFinder: Endpoint[IO, FinderRequest] = jsonBody[FinderRequest]
  def find(): Endpoint[IO, FinderResponse] = post(finderPath :: postedFinder) { request: FinderRequest =>
    Ok(FinderResponse(
      request.text,
      Finder(request.text)))
  }
}
