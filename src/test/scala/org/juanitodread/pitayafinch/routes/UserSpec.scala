package org.juanitodread.pitayafinch.routes

import io.finch._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.CommonMessage

class UserSpec extends UnitSpec {
  private val baseApi = "/pitaya/api/v1/users"

  "A User route" should "have an index endpoint" in {
    assert(User.index.apply(
      Input.get(baseApi)).awaitValueUnsafe().contains("Hello User"))
  }

  it should "have a help endpoint" in {
    assert(User.help.apply(
      Input.get(s"$baseApi/help")).awaitValueUnsafe().contains("This is the help for users"))
  }

  it should "have a message endpoint" in {
    assert(User.message.apply(
      Input.get(s"$baseApi/cm")).awaitValueUnsafe().get.isInstanceOf[CommonMessage])
  }
}
