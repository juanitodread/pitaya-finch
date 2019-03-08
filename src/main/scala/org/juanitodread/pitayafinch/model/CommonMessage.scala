package org.juanitodread.pitayafinch.model

case class Emitter(id: String)

case class Client(
  id: String,
  name: String,
  time: Long)

case class CommonMessage(
  client: Client,
  emitter: Emitter,
  _type: String,
  body: String)
