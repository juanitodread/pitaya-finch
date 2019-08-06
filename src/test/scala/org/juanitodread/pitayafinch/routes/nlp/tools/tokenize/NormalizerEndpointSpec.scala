package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ LowerCaseRequest, LowerCaseResponse }

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
}
