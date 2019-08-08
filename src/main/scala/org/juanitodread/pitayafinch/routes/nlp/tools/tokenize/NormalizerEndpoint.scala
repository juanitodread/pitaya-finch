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
  private final val stemPath = normalizerPath :: "stem"
  private final val lemmaPath = normalizerPath :: "lemma"

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

  private val postedStem: Endpoint[IO, StemRequest] = jsonBody[StemRequest]
  def stemmer(): Endpoint[IO, StemResponse] = post(stemPath :: postedStem) { request: StemRequest =>
    Ok(StemResponse(
      request.tokens,
      EnglishStemmer(request.tokens)))
  }

  private val postedLemma: Endpoint[IO, LemmaRequest] = jsonBody[LemmaRequest]
  def lemmatizer(): Endpoint[IO, LemmaResponse] = post(lemmaPath :: postedLemma) { request: LemmaRequest =>
    Ok(LemmaResponse(
      request.tokens,
      EnglishLemmatizer(request.tokens)))
  }
}
