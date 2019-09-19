package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer._

class PipelineEndpointSpec extends UnitSpec {
  private val baseApi = "/pitaya-finch/api/v1/nlp/pipeline"

  "A PipelineEndpoint route" should "have pipeline endpoint" in {
    val request = PipelineRequest(
      "This's a sample text",
      Pipeline(
        Init(Initializers.TOKENIZER, Tokenizers.WHITESPACE),
        List(Stage(Stagers.LOWERCASE)),
        Finalizer(Finalizers.STEMMER)))
    PipelineEndpoint.pipeline().apply(Input.post(baseApi).withBody[Json](request)).awaitValueUnsafe() should equal {
      Some(PipelineResponse(
        "Tokenizer -> LowerCaseConverter -> Stemmer",
        Some(List(
          StemResult("this's", "this'"),
          StemResult("a", "a"),
          StemResult("sample", "sampl"),
          StemResult("text", "text"))),
        None))
    }
  }
}
