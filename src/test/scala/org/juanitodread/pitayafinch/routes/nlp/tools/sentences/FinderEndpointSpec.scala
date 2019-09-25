package org.juanitodread.pitayafinch.routes.nlp.tools.sentences

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.sentences._

class FinderEndpointSpec extends UnitSpec {
  private val baseAPI: String = "/pitaya/api/v1/nlp/sentences/find"

  "A FinderEndpoint" should "have a find endpoint" in {
    val request = FinderRequest("This is a sample. This is another sample")
    FinderEndpoint.find().apply(Input.post(baseAPI).withBody[Json](request)).awaitValueUnsafe().get shouldEqual {
      FinderResponse(
        "This is a sample. This is another sample",
        List(
          FinderResult("This is a sample.", 1.0),
          FinderResult("This is another sample", 1.0)))
    }
  }
}
