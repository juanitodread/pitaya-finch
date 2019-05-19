package org.juanitodread.pitayafinch.nlp.tools.tokenize

class LowerCaseConverter {
  def toLowerCase(token: String): String = {
    token.toLowerCase
  }

  def toLowerCase(tokens: List[String]): List[String] = {
    tokens.map(this.toLowerCase)
  }
}

object LowerCaseConverter {
  def apply(tokens: List[String]): List[String] = {
    new LowerCaseConverter().toLowerCase(tokens)
  }
}