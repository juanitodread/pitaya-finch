package org.juanitodread.pitayafinch.routes

import io.finch._

import org.juanitodread.pitayafinch.UnitSpec

class IndexTest extends UnitSpec {
  private val baseApi = "/pitaya/api/v1"

  "An Index route" should "have an index endpoint" in {
    assert(Index.index.apply(
      Input.get(baseApi)).awaitValueUnsafe().contains("Hello World"))
  }

  it should "have a help endpoint" in {
    assert(Index.help.apply(
      Input.get(s"$baseApi/help")).awaitValueUnsafe().contains("This is the help for root"))
  }
}
