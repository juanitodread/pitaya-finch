package org.juanitodread.pitayafinch.model.nlp.tokenizer

import org.juanitodread.pitayafinch.model.nlp.tokenizer.Tokenizers.Tokenizer

sealed trait Tokenize {
  def text: String
  def algorithm: Tokenizer
}

case class TokenizeRequest(text: String, algorithm: Tokenizer) extends Tokenize
case class TokenizeResponse(text: String, algorithm: Tokenizer, tokens: List[String]) extends Tokenize
