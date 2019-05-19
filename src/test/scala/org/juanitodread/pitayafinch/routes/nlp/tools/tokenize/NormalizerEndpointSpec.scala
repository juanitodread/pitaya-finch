package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.Lowercase

class NormalizerEndpointSpec extends UnitSpec {
  private val baseApi = "/pitaya-finch/api/v1/nlp/normalizer"

  "A NormalizerEndpoint route" should "have a lowercase endpoint" in {
    val lowercase = Lowercase(List("ThiS", "RequireS", "TO", "bE", "LOWERCASE"), None)
    NormalizerEndpoint.lowercase().apply(Input.post(baseApi + "/lowercase").withBody[Json](lowercase)).awaitValueUnsafe() should equal {
      Some(Lowercase(
        List("ThiS", "RequireS", "TO", "bE", "LOWERCASE"),
        Some(List("this", "requires", "to", "be", "lowercase"))))
    }
  }
}
