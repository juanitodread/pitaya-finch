package org.juanitodread.pitayafinch.model.nlp.tokenizer

import org.juanitodread.pitayafinch.UnitSpec

class AlgorithmsSpec extends UnitSpec {
  "An Algorithms" should "have a Tokenizers algorithm which returns a list of available algorithms" in {
    assert(Tokenizers.getAlgorithms() === List("SIMPLE", "WHITESPACE", "MAX_ENTROPY"))
  }

  it should "have an Initializers algorithm which returns a list of available algorithms" in {
    assert(Initializers.getAlgorithms() === List("TOKENIZER"))
  }

  it should "have a Stagers algorithm which returns a list of available algorithms" in {
    assert(Stagers.getAlgorithms() === List("LOWERCASE", "STOPWORDS"))
  }

  it should "have a Finalizers algorithm which returns a list of available algorithms" in {
    assert(Finalizers.getAlgorithms() === List("STEMMER", "LEMMATIZER"))
  }
}
