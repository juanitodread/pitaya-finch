package org.juanitodread.pitayafinch.model.nlp.tokenizer

import io.circe.{ Decoder, Encoder }
import io.circe.generic.extras.semiauto.{ deriveEnumerationDecoder, deriveEnumerationEncoder }

sealed trait Algorithm {
  def name: String
}

sealed abstract class Algorithms {
  def getAlgorithms(): List[String]
}

object Tokenizers extends Algorithms {
  sealed trait Tokenizer extends Algorithm
  case object SIMPLE extends Tokenizer { override val name = "SIMPLE" }
  case object WHITESPACE extends Tokenizer { override val name = "WHITESPACE" }
  case object MAX_ENTROPY extends Tokenizer { override val name = "MAX_ENTROPY" }

  def getAlgorithms(): List[String] = List(
    SIMPLE,
    WHITESPACE,
    MAX_ENTROPY).map(_.name)

  implicit val encoder: Encoder[Tokenizer] = deriveEnumerationEncoder[Tokenizer]
  implicit val decoder: Decoder[Tokenizer] = deriveEnumerationDecoder[Tokenizer]
}

object Initializers extends Algorithms {
  sealed trait Initializer extends Algorithm
  case object TOKENIZER extends Initializer { override val name = "TOKENIZER" }

  def getAlgorithms(): List[String] = List(
    TOKENIZER).map(_.name)

  implicit val encoder: Encoder[Initializer] = deriveEnumerationEncoder[Initializer]
  implicit val decoder: Decoder[Initializer] = deriveEnumerationDecoder[Initializer]
}

object Stagers extends Algorithms {
  sealed trait Stage extends Algorithm
  case object LOWERCASE extends Stage { override val name = "LOWERCASE" }
  case object STOPWORDS extends Stage { override val name = "STOPWORDS" }

  def getAlgorithms(): List[String] = List(
    LOWERCASE,
    STOPWORDS).map(_.name)

  implicit val encoder: Encoder[Stage] = deriveEnumerationEncoder[Stage]
  implicit val decoder: Decoder[Stage] = deriveEnumerationDecoder[Stage]
}

object Finalizers extends Algorithms {
  sealed trait Finalizer extends Algorithm
  case object STEMMER extends Finalizer { override val name = "STEMMER" }
  case object LEMMATIZER extends Finalizer { override val name = "LEMMATIZER" }

  def getAlgorithms(): List[String] = List(
    STEMMER,
    LEMMATIZER).map(_.name)

  implicit val encoder: Encoder[Finalizer] = deriveEnumerationEncoder[Finalizer]
  implicit val decoder: Decoder[Finalizer] = deriveEnumerationDecoder[Finalizer]
}
