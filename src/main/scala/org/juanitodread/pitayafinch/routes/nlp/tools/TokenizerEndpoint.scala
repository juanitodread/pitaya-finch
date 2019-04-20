package org.juanitodread.pitayafinch.routes.nlp.tools

import cats.effect.IO
import io.circe.generic.auto._
import io.finch._
import io.finch.catsEffect._
import io.finch.circe._
import org.juanitodread.pitayafinch.model.nlp.tokenizer.Algorithm
import org.juanitodread.pitayafinch.model.nlp.tokenizer.Tokenize
import org.juanitodread.pitayafinch.nlp.tools.tokenize.Tokenizer
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object TokenizerEndpoint extends NlpEndpoint {
  private final val tokenizerPath = basePath :: "tokenizer"

  def getAlgorithms(): Endpoint[IO, List[String]] = get(tokenizerPath) {
    Ok(Algorithm.getAlgorithms())
  }

  private val postedTokenize: Endpoint[IO, Tokenize] = jsonBody[Tokenize]
  def tokenize(): Endpoint[IO, Tokenize] = post(tokenizerPath :: postedTokenize) { tokenize: Tokenize =>
    Ok(Tokenize(
      tokenize.content,
      tokenize.algorithm,
      Some(Tokenizer.tokenize(tokenize.content, tokenize.algorithm))))
  }

}
