package org.juanitodread.pitayafinch.model.nlp.tokenizer

import org.juanitodread.pitayafinch.UnitSpec

class AlgorithmsSpec extends UnitSpec {
  "A Tokenizers algorithm" should "return a list of available algorithms" in {
    assert(Tokenizers.getAlgorithms() === List("SIMPLE", "WHITESPACE", "MAX_ENTROPY"))
  }

  "An Initializers algorithm" should "return a list of available algorithms" in {
    assert(Initializers.getAlgorithms() === List("TOKENIZER"))
  }

  "A Stagers algorithm" should "return a list of available algorithms" in {
    assert(Stagers.getAlgorithms() === List("LOWERCASE", "STOPWORDS"))
  }

  "A Finalizers algorithm" should "return a list of available algorithms" in {
    assert(Finalizers.getAlgorithms() === List("STEMMER", "LEMMATIZER"))
  }
}
