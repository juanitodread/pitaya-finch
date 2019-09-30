package org.juanitodread.pitayafinch.routes

import io.circe.Json
import io.finch._

import org.juanitodread.pitayafinch.UnitSpec

class IndexSpec extends UnitSpec {
  private val baseApi = "/pitaya/api/v1"

  "An Index route" should "have an index endpoint" in {
    Index.index.apply(
      Input.get(baseApi)).awaitValueUnsafe().get shouldEqual {
        Json.obj("message" -> Json.fromString("Please check the API documentation here: https://github.com/juanitodread/pitaya-finch#api-definition"))
      }
  }

  it should "have a help endpoint" in {
    assert(Index.help.apply(
      Input.get(s"$baseApi/help")).awaitValueUnsafe().contains("This is the help for root"))
  }
}
