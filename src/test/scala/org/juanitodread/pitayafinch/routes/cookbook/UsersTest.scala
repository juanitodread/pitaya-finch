package org.juanitodread.pitayafinch.routes.cookbook

import java.util.UUID

import io.finch.Input
import io.finch.Application.Json
import io.finch.circe._
import io.circe.generic.auto._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.{ User => UserModel }

class UsersTest extends UnitSpec {
  private val baseApi = "/pitaya-finch/api/v1/crud/users"

  private val usersFixture = {
    List(
      UserModel(UUID.fromString("7becf740-7f1e-4cf4-b908-d67397b3503f"), "Juan", 10, None, List()),
      UserModel(UUID.fromString("7becf740-7f1e-4cf4-b908-d67397b3503a"), "Antonio", 11, None, List("admin")))
  }

  def prepareUsers: Unit = {
    usersFixture.foreach { user =>
      val userInput = Input.post(baseApi).withBody[Json](user)
      Users.postUser.apply(userInput).awaitValueUnsafe()
    }
  }

  "A Users route" should "have getUsers endpoint" in {
    prepareUsers

    Users.getUsers.apply(Input.get(baseApi)).awaitValueUnsafe().get.map { user =>
      (user.name, user.age, user.address, user.roles)
    } should contain theSameElementsAs List(
      ("Juan", 10, None, List()),
      ("Antonio", 11, None, List("admin")))
  }

}
