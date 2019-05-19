package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._
import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ Algorithm, Tokenize }

class TokenizerEndpointSpec extends UnitSpec {
  private val baseApi = "/pitaya-finch/api/v1/nlp/tokenizer"

  "A TokenizerEndpoint route" should "have getAlgorithms endpoint" in {
    TokenizerEndpoint.getAlgorithms().apply(Input.get(baseApi)).awaitValueUnsafe().get should equal {
      List("SIMPLE", "WHITESPACE", "MAX_ENTROPY")
    }
  }

  it should "have tokenize endpoint" in {
    val tokenize = Tokenize("This's a sample text", Algorithm.SIMPLE, None)
    TokenizerEndpoint.tokenize().apply(Input.post(baseApi).withBody[Json](tokenize)).awaitValueUnsafe() should equal {
      Some(Tokenize(
        "This's a sample text",
        Algorithm.SIMPLE,
        Some(List("This", "'", "s", "a", "sample", "text"))))
    }
  }
}
