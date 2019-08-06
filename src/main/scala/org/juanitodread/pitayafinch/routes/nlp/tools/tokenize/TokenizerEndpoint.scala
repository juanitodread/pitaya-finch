package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ Algorithm, TokenizeRequest, TokenizeResponse }
import org.juanitodread.pitayafinch.nlp.tools.tokenize.Tokenizer
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object TokenizerEndpoint extends NlpEndpoint {
  private final val tokenizerPath = basePath :: "tokenizer"

  def getAlgorithms(): Endpoint[IO, List[String]] = get(tokenizerPath) {
    Ok(Algorithm.getAlgorithms())
  }

  private val postedTokenize: Endpoint[IO, TokenizeRequest] = jsonBody[TokenizeRequest]
  def tokenize(): Endpoint[IO, TokenizeResponse] = post(tokenizerPath :: postedTokenize) { request: TokenizeRequest =>
    Ok(TokenizeResponse(
      request.text,
      request.algorithm,
      Tokenizer.tokenize(request.text, request.algorithm)))
  }
}
