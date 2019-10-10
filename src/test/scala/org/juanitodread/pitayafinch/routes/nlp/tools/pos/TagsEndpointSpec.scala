package org.juanitodread.pitayafinch.routes.nlp.tools.pos

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.pos._

class TagsEndpointSpec extends UnitSpec {
  private val baseApi = "/pitaya/api/v1/nlp/pos/tags"

  "A TagsEndpoint" should "have a tags endpoint" in {
    val request = TagsRequest("He appointed Julian Casablancas", chunk = false)
    TagsEndpointFixture.tags().apply(Input.post(baseApi).withBody[Json](request)).awaitValueUnsafe().get shouldEqual {
      TagsResponse(
        "He appointed Julian Casablancas",
        TagsResult(List(
          Tag("He", "PRP", "Personal pronoun"),
          Tag("appointed", "VBD", "Verb, past tense"),
          Tag("Julian", "NNP", "Proper noun, singular"),
          Tag("Casablancas", "NNP", "Proper noun, singular")), None))
    }
  }
}
