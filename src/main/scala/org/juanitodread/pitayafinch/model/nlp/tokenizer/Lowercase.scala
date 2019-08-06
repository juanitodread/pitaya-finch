package org.juanitodread.pitayafinch.model.nlp.tokenizer

sealed trait LowerCase {
  def tokens: List[String]
}

case class LowerCaseRequest(tokens: List[String]) extends LowerCase
case class LowerCaseResponse(tokens: List[String], result: List[String]) extends LowerCase
