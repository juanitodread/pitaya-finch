package org.juanitodread.pitayafinch.nlp.tokenize

import org.juanitodread.pitayafinch.UnitSpec

class TokenizerSpec extends UnitSpec {

  "A Tokenizer" should "tokenize an empty string using simple tokenization algorithm" in {
    assert(Tokenizer.simple("") === List.empty)
  }

  "A Tokenizer" should "tokenize a string using simple tokenization algorithm" in {
    val text = "This's a simple paragraph that requires to be tokenized, or die in the process."
    val tokenizedText = List("This", "'", "s", "a", "simple", "paragraph", "that", "requires", "to", "be",
      "tokenized", ",", "or", "die", "in", "the", "process", ".")

    assert(Tokenizer.simple(text) === tokenizedText)
  }

  "A Tokenizer" should "tokenize an empty string using whitespace tokenization algorithm" in {
    assert(Tokenizer.whitespace("") === List.empty)
  }

  "A Tokenizer" should "tokenize a string using whitespace tokenization algorithm" in {
    val text = "This's a simple paragraph that requires to be tokenized, or die in the process."
    val tokenizedText = List("This's", "a", "simple", "paragraph", "that", "requires", "to", "be",
      "tokenized,", "or", "die", "in", "the", "process.")

    assert(Tokenizer.whitespace(text) === tokenizedText)
  }

}
