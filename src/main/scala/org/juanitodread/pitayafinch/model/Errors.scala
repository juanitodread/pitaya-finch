package org.juanitodread.pitayafinch.model

sealed abstract class PitayaError(title: String, msg: String) extends Exception(msg) {
  def getTitle: String = title
  def message: String
}

case class ResourceNotAvailable(
  title: String = "ResourceNotAvailable",
  message: String) extends PitayaError(title, message)
