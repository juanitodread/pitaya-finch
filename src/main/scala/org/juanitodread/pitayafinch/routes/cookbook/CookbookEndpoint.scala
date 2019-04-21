package org.juanitodread.pitayafinch.routes.cookbook

import cats.effect.IO
import io.finch.Endpoint
import io.finch.catsEffect._
import shapeless._

import org.juanitodread.pitayafinch.routes.BaseEndpoint

trait CookbookEndpoint extends BaseEndpoint {
  override protected def basePath: Endpoint[IO, HNil] = super.basePath :: "crud"
}

object CookbookEndpoints {

  def build() = {
    Users.getUsers() :+:
      Users.getUser() :+:
      Users.postUser() :+:
      Users.patchUser() :+:
      Users.deleteUser()
  }

}
