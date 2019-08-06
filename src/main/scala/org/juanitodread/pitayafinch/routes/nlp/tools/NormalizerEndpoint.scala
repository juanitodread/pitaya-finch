package org.juanitodread.pitayafinch.routes.nlp.tools

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ LowerCaseRequest, LowerCaseResponse }
import org.juanitodread.pitayafinch.nlp.tools.tokenize.LowerCaseConverter
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object NormalizerEndpoint extends NlpEndpoint {
  private final val normalizerPath = basePath :: "normalizer"
  private final val lowercasePath = normalizerPath :: "lowercase"

  private val postedTokens: Endpoint[IO, LowerCaseRequest] = jsonBody[LowerCaseRequest]
  def lowercase(): Endpoint[IO, LowerCaseResponse] = post(lowercasePath :: postedTokens) { request: LowerCaseRequest =>
    val lowerCase = new LowerCaseConverter()
    Ok(LowerCaseResponse(
      request.tokens,
      lowerCase.toLowerCase(request.tokens)))
  }
}
