package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.tokenizer._
import org.juanitodread.pitayafinch.nlp.tools.tokenize._
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object NormalizerEndpoint extends NlpEndpoint {
  private final val normalizerPath = basePath :: "normalizer"
  private final val lowercasePath = normalizerPath :: "lowercase"
  private final val stopwordsPath = normalizerPath :: "stopwords"

  private val postedLower: Endpoint[IO, LowerCaseRequest] = jsonBody[LowerCaseRequest]
  def lowercase(): Endpoint[IO, LowerCaseResponse] = post(lowercasePath :: postedLower) { request: LowerCaseRequest =>
    Ok(LowerCaseResponse(
      request.tokens,
      LowerCaseConverter(request.tokens)))
  }

  private val postedStop: Endpoint[IO, StopwordRequest] = jsonBody[StopwordRequest]
  def stopwords(): Endpoint[IO, StopwordResponse] = post(stopwordsPath :: postedStop) { request: StopwordRequest =>
    Ok(StopwordResponse(
      request.tokens,
      EnglishStopWordsRemover(request.tokens)))
  }
}
