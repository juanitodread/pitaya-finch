package org.juanitodread.pitayafinch.routes.cookbook

import java.util.UUID

import scala.collection.mutable

import cats.effect.IO
import com.twitter.finagle.http.Status
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import io.finch.catsEffect._

import org.juanitodread.pitayafinch.model.User
import org.juanitodread.pitayafinch.routes.BaseEndpoint

object Users extends BaseEndpoint {

  private final val usersPath = basePath :: "crud" :: "users"
  private final val usersStore: mutable.Map[UUID, User] = mutable.Map.empty[UUID, User]

  def getUsers(): Endpoint[IO, List[User]] = get(usersPath) {
    Ok(usersStore.values.toList)
  }

  def getUser(): Endpoint[IO, User] = get(usersPath :: path[UUID]) { id: UUID =>
    usersStore.get(id) match {
      case Some(user) => Ok(user)
      case None => Output.empty(Status.NotFound)
    }
  }

  private val postedUser: Endpoint[IO, User] = jsonBody[UUID => User].map(postedUsr => postedUsr(UUID.randomUUID()))
  def postUser(): Endpoint[IO, User] = post(usersPath :: postedUser) { user: User =>
    usersStore += (user.id -> user)
    Created(user)
  }

  private val patchedUser: Endpoint[IO, User => User] = jsonBody[User => User]
  def patchUser(): Endpoint[IO, User] = patch(usersPath :: path[UUID] :: patchedUser) { (id: UUID, user: User => User) =>
    usersStore.get(id) match {
      case Some(currentUser) =>
        val newUser = user(currentUser)
        usersStore(id) = newUser
        Ok(newUser)
      case None => Output.empty(Status.NotFound)
    }
  }

  def deleteUser(): Endpoint[IO, User] = delete(usersPath :: path[UUID]) { id: UUID =>
    usersStore.get(id) match {
      case Some(user) =>
        usersStore -= id
        Ok(user)
      case None => Output.empty(Status.NotFound)
    }

  }

}
