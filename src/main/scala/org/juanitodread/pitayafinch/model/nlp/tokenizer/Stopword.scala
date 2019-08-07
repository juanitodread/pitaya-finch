package org.juanitodread.pitayafinch.model.nlp.tokenizer

sealed trait Stopword {
  def tokens: List[String]
}

case class StopwordRequest(tokens: List[String]) extends Stopword
case class StopwordResponse(tokens: List[String], result: List[String]) extends Stopword
