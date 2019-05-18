package org.juanitodread.pitayafinch.model.nlp.tokenizer

import io.circe._
import io.circe.generic.extras.semiauto._

object Algorithm {
  sealed trait Algorithm { def name: String }
  case object SIMPLE extends Algorithm { override val name = "SIMPLE" }
  case object WHITESPACE extends Algorithm { override val name = "WHITESPACE" }
  case object MAX_ENTROPY extends Algorithm { override val name = "MAX_ENTROPY" }

  def getAlgorithms() = List(
    SIMPLE,
    WHITESPACE,
    MAX_ENTROPY).map(_.name)

  implicit val algorithmEncoder: Encoder[Algorithm] = deriveEnumerationEncoder[Algorithm]
  implicit val algorithmDecoder: Decoder[Algorithm] = deriveEnumerationDecoder[Algorithm]
}

case class Tokenize(
  text: String,
  algorithm: Algorithm.Algorithm,
  tokens: Option[List[String]])
