package org.juanitodread.pitayafinch.model

import java.util.UUID

case class Address(
  street: String,
  zipcode: String)

case class User(
  id: UUID,
  name: String,
  age: Int,
  address: Option[Address],
  roles: List[String])

