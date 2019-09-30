package org.juanitodread.pitayafinch.routes.nlp.tools.entities

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.entities.{ EntitiesRequest, EntitiesResponse, Entity }

class EntitiesEndpointSpec extends UnitSpec {
  private val baseApi: String = "/pitaya/api/v1/nlp/entities"

  "A EntitiesEndpoint" should "have a getEntities endpoint" in {
    val request = EntitiesRequest("He appointed Julian Casablancas")
    EntitiesEndpoint.getEntities().apply(Input.post(baseApi).withBody[Json](request)).awaitValueUnsafe().get shouldEqual {
      EntitiesResponse(
        "He appointed Julian Casablancas",
        List(Entity("Julian Casablancas", "Person", 0.939)))
    }
  }
}
