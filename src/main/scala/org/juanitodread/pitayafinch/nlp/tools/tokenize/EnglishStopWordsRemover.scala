package org.juanitodread.pitayafinch.nlp.tools.tokenize

class EnglishStopWordsRemover {
  private final val stopWords = Set(
    "i", "a", "about", "an", "are", "as",
    "at", "be", "by", "com", "for", "from",
    "how", "in", "is", "it", "of", "on",
    "or", "that", "the", "this", "to", "was",
    "what", "when", "where", "who", "will", "with")

  def remove(tokens: List[String]): List[String] = {
    tokens.filter(token => !stopWords.contains(token))
  }
}
