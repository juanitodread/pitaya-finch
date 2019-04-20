package org.juanitodread.pitayafinch.nlp.tokenize

import org.juanitodread.pitayafinch.UnitSpec

class TokenizerSpec extends UnitSpec {

  "A Tokenizer" should "tokenize an empty string using simple algorithm" in {
    assert(Tokenizer.simple("") === List.empty)
  }

  "A Tokenizer" should "tokenize a string using simple algorithm" in {
    val text = "This's a simple paragraph that requires to be tokenized, or die in the process."
    val tokenizedText = List("This", "'", "s", "a", "simple", "paragraph", "that", "requires", "to", "be",
      "tokenized", ",", "or", "die", "in", "the", "process", ".")

    assert(Tokenizer.simple(text) === tokenizedText)
  }

  "A Tokenizer" should "tokenize an empty string using whitespace algorithm" in {
    assert(Tokenizer.whitespace("") === List.empty)
  }

  "A Tokenizer" should "tokenize a string using whitespace algorithm" in {
    val text = "This's a simple paragraph that requires to be tokenized, or die in the process."
    val tokenizedText = List("This's", "a", "simple", "paragraph", "that", "requires", "to", "be",
      "tokenized,", "or", "die", "in", "the", "process.")

    assert(Tokenizer.whitespace(text) === tokenizedText)
  }

  "A Tokenizer" should "tokenize an empty string using maximum entropy algorithm" in {
    assert(Tokenizer.maxEntropy("") === List.empty)
  }

  "A Tokenizer" should "tokenize a string using maximum entropy algorithm" in {
    val text = "This's a simple paragraph that requires to be tokenized, or die in the process."
    val tokenizedText = List("This's", "a", "simple", "paragraph", "that", "requires", "to", "be",
      "tokenized,", "or", "die", "in", "the", "process.")

    assert(Tokenizer.maxEntropy(text) === tokenizedText)
  }

}
