package org.juanitodread.pitayafinch.model.nlp.tokenizer

import io.circe.{ Decoder, Encoder }
import io.circe.generic.extras.semiauto._

import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline._
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

sealed trait Algorithm {
  def name: String
}

sealed abstract class Algorithms {
  def getAlgorithms(): List[String]
}

object Tokenizers extends Algorithms {
  sealed trait Tokenizer extends Algorithm
  case object SIMPLE extends Tokenizer { override val name: String = "SIMPLE" }
  case object WHITESPACE extends Tokenizer { override val name: String = "WHITESPACE" }
  case object MAX_ENTROPY extends Tokenizer { override val name: String = "MAX_ENTROPY" }

  def getAlgorithms(): List[String] = List(
    SIMPLE,
    WHITESPACE,
    MAX_ENTROPY).map(_.name)

  implicit val encoder: Encoder[Tokenizer] = deriveEnumerationEncoder[Tokenizer]
  implicit val decoder: Decoder[Tokenizer] = deriveEnumerationDecoder[Tokenizer]
}

object Initializers extends Algorithms {
  sealed trait Initializer extends Algorithm {
    def getInstance(text: String, algorithm: Tokenizers.Tokenizer): Source[Tokens]
  }
  case object TOKENIZER extends Initializer {
    override val name: String = "TOKENIZER"
    override def getInstance(text: String, algorithm: Tokenizers.Tokenizer): Source[Tokens] = {
      new Tokenizer(text, algorithm)
    }
  }

  def getAlgorithms(): List[String] = List(
    TOKENIZER).map(_.name)

  implicit val encoder: Encoder[Initializer] = deriveEnumerationEncoder[Initializer]
  implicit val decoder: Decoder[Initializer] = deriveEnumerationDecoder[Initializer]
}

object Stagers extends Algorithms {
  sealed trait Stage extends Algorithm {
    def getInstance(): pipeline.Step[Tokens, Tokens]
  }
  case object LOWERCASE extends Stage {
    override val name: String = "LOWERCASE"
    def getInstance(): LowerCaseConverter = new LowerCaseConverter
  }
  case object STOPWORDS extends Stage {
    override val name: String = "STOPWORDS"
    def getInstance(): StopWordsRemover = new StopWordsRemover
  }

  def getAlgorithms(): List[String] = List(
    LOWERCASE,
    STOPWORDS).map(_.name)

  implicit val encoder: Encoder[Stage] = deriveEnumerationEncoder[Stage]
  implicit val decoder: Decoder[Stage] = deriveEnumerationDecoder[Stage]
}

object Finalizers extends Algorithms {
  sealed trait Finalizer extends Algorithm
  case object STEMMER extends Finalizer {
    override val name: String = "STEMMER"
    def getInstance(): Stemmer = new Stemmer
  }
  case object LEMMATIZER extends Finalizer {
    override val name: String = "LEMMATIZER"
    def getInstance(): Lemmatizer = new Lemmatizer
  }

  def getAlgorithms(): List[String] = List(
    STEMMER,
    LEMMATIZER).map(_.name)

  implicit val encoder: Encoder[Finalizer] = deriveEnumerationEncoder[Finalizer]
  implicit val decoder: Decoder[Finalizer] = deriveEnumerationDecoder[Finalizer]
}
