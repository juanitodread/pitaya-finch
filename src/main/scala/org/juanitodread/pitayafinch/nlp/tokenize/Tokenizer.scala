package org.juanitodread.pitayafinch.nlp.tokenize

import opennlp.tools.tokenize.{ SimpleTokenizer, WhitespaceTokenizer }

object Tokenizer {
  private final val simpleTokenizer = SimpleTokenizer.INSTANCE
  private final val whitespaceTokenizer = WhitespaceTokenizer.INSTANCE

  def simple(paragraph: String): List[String] = {
    simpleTokenizer.tokenize(paragraph).toList
  }

  def whitespace(paragraph: String): List[String] = {
    whitespaceTokenizer.tokenize(paragraph).toList
  }

}
