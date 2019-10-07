package org.juanitodread.pitayafinch.model

sealed abstract class PitayaError(tit: String, msg: String) extends Exception(msg) {
  def title: String = tit
  def message: String
}

case class ResourceNotAvailable(
  override val title: String = "ResourceNotAvailable",
  message: String) extends PitayaError(title, message)
