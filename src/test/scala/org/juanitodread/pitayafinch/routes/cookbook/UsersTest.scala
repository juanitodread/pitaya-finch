package org.juanitodread.pitayafinch.routes.cookbook

import java.util.UUID

import io.circe.generic.auto._
import io.finch.Application.Json
import io.finch.Input
import io.finch.circe._

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.{ User => UserModel }

class UsersTest extends UnitSpec {
  private val baseApi = "/pitaya/api/v1/crud/users"

  def prepareUsers(): List[UserModel] = {
    val usersFixture = List(
      UserModel(UUID.fromString("7becf740-7f1e-4cf4-b908-d67397b3503f"), "Juan", 10, None, List()),
      UserModel(UUID.fromString("7becf740-7f1e-4cf4-b908-d67397b3503a"), "Antonio", 11, None, List("admin")))

    usersFixture.map { user =>
      val userInput = Input.post(baseApi).withBody[Json](user)
      Users.postUser().apply(userInput).awaitValueUnsafe().get
    }
  }

  def getUserByName(name: String): Option[UserModel] = {
    Users.getUsers().apply(Input.get(baseApi)).awaitValueUnsafe().get.find(_.name == name)
  }

  "A Users route" should "have getUsers endpoint" in {
    prepareUsers()
    Users.getUsers().apply(Input.get(baseApi)).awaitValueUnsafe().get.map { user =>
      (user.name, user.age, user.address, user.roles)
    } should contain theSameElementsAs List(
      ("Juan", 10, None, List()),
      ("Antonio", 11, None, List("admin")))
  }

  it should "have getUser endpoint" in {
    val juanUser = prepareUsers().find(_.name == "Juan").get
    Users.getUser().apply(Input.get(baseApi + s"/${juanUser.id}")).awaitValueUnsafe() shouldEqual {
      Some(UserModel(juanUser.id, "Juan", 10, None, List()))
    }
  }

  it should "have postUser endpoint" in {
    prepareUsers()
    val user = UserModel(UUID.fromString("7becf740-7f1e-4cf4-b908-d67397b3505f"), "Peter", 22, None, List())
    Users.postUser().apply(Input.post(baseApi).withBody[Json](user)).awaitValueUnsafe() should matchPattern {
      case Some(UserModel(_, "Peter", 22, None, List())) =>
    }
  }

  it should "have patchUser endpoint" in {
    val juanUser = prepareUsers().find(_.name == "Juan").get
    val patchedUser = UserModel(
      juanUser.id,
      juanUser.name + "Edited",
      juanUser.age,
      juanUser.address,
      juanUser.roles)
    Users.patchUser().apply(
      Input.patch(baseApi + s"/${patchedUser.id}").withBody[Json](patchedUser)).awaitValueUnsafe() should matchPattern {
        case Some(UserModel(patchedUser.id, "JuanEdited", 10, None, List())) =>
      }
  }

  it should "have deleteUser endpoint" in {
    val juanUser = prepareUsers().find(_.name == "Juan").get
    Users.deleteUser().apply(Input.delete(baseApi + s"/${juanUser.id}")).awaitValueUnsafe() should matchPattern {
      case Some(UserModel(juanUser.id, "Juan", 10, None, List())) =>
    }
  }

}
