package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer._

class NormalizerEndpointSpec extends UnitSpec {
  private val baseApi = "/pitaya-finch/api/v1/nlp/normalizer"

  "A NormalizerEndpoint route" should "have lowercase endpoint" in {
    val request = LowerCaseRequest(List("This", "SHOULD", "bE", "lOwErcASE"))
    NormalizerEndpoint.lowercase().apply(Input.post(s"$baseApi/lowercase").withBody[Json](request)).awaitValueUnsafe() should equal {
      Some(LowerCaseResponse(
        List("This", "SHOULD", "bE", "lOwErcASE"),
        List("this", "should", "be", "lowercase")))
    }
  }

  "A NormalizerEndpoint route" should "have stopwords endpoint" in {
    val request = StopwordRequest(List("this", "should", "be", "lowercase"))
    NormalizerEndpoint.stopwords().apply(Input.post(s"$baseApi/stopwords").withBody[Json](request)).awaitValueUnsafe() should equal {
      Some(StopwordResponse(
        List("this", "should", "be", "lowercase"),
        List("should", "lowercase")))
    }
  }

  "A NormalizerEndpoint route" should "have stem endpoint" in {
    val request = StemRequest(List("bank", "banking", "banked"))
    NormalizerEndpoint.stemmer().apply(Input.post(s"$baseApi/stem").withBody[Json](request)).awaitValueUnsafe() should equal {
      Some(StemResponse(
        List("bank", "banking", "banked"),
        List(StemResult("bank", "bank"), StemResult("banking", "bank"), StemResult("banked", "bank"))))
    }
  }
}
