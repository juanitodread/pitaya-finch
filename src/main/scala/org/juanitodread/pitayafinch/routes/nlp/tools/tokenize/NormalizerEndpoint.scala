package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._

import org.juanitodread.pitayafinch.model.nlp.tokenizer.Lowercase
import org.juanitodread.pitayafinch.nlp.tools.tokenize.LowerCaseConverter
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object NormalizerEndpoint extends NlpEndpoint {
  private final val normalizerPath = basePath :: "normalizer"

  private val postedLowercase: Endpoint[IO, Lowercase] = jsonBody[Lowercase]
  def lowercase(): Endpoint[IO, Lowercase] = post(normalizerPath :: "lowercase" :: postedLowercase) { lowercase: Lowercase =>
    Ok(Lowercase(
      lowercase.tokens,
      Some(LowerCaseConverter(lowercase.tokens))))
  }
}
