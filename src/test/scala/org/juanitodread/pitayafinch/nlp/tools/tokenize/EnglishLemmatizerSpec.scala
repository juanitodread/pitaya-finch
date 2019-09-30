package org.juanitodread.pitayafinch.nlp.tools.tokenize

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ Lemma, LemmaResult }

class EnglishLemmatizerSpec extends UnitSpec {
  val dictionary = "/nlp/models/tokenizer/lemmatizer/en-lemmatizer.dict"

  "An EnglishLemmatizer" should "fail when provided dictionary path does not exist" in {
    assertThrows[IllegalArgumentException] {
      new EnglishLemmatizer("invalid-path")
    }
  }

  it should "get the lemma result of a word" in {
    val lemmatizer: EnglishLemmatizer = new EnglishLemmatizer(dictionary)
    val token: String = "better"
    assert(lemmatizer.lemmatize(token) === LemmaResult(
      "better",
      List(
        Lemma("JJR", "Adjective, comparative"),
        Lemma("NN", "Noun, singular or mass"),
        Lemma("VB", "Verb, base form"),
        Lemma("VBP", "Verb, non-3rd person singular present"),
        Lemma("RBR", "Adverb, comparative"))))
  }

  it should "get the lemma result of a not common word" in {
    val lemmatizer: EnglishLemmatizer = new EnglishLemmatizer(dictionary)
    val token: String = "thisisnotavalidword"
    assert(lemmatizer.lemmatize(token) === LemmaResult(
      "thisisnotavalidword",
      List()))
  }

  it should "get the lemma result of an empty word" in {
    val lemmatizer: EnglishLemmatizer = new EnglishLemmatizer(dictionary)
    val token: String = ""
    assert(lemmatizer.lemmatize(token) === LemmaResult(
      "",
      List()))
  }

  it should "get the lemma result of a list of words" in {
    val lemmatizer: EnglishLemmatizer = new EnglishLemmatizer(dictionary)
    val tokens: List[String] = List("better", "meeting")
    assert(
      lemmatizer.lemmatize(tokens) === List(
        LemmaResult(
          "better",
          List(
            Lemma("JJR", "Adjective, comparative"),
            Lemma("NN", "Noun, singular or mass"),
            Lemma("VB", "Verb, base form"),
            Lemma("VBP", "Verb, non-3rd person singular present"),
            Lemma("RBR", "Adverb, comparative"))),
        LemmaResult(
          "meeting",
          List(
            Lemma("VBG", "Verb, gerund, or present participle"),
            Lemma("NNN", "Noun")))))
  }

  it should "get the lemma result of an empty list of words" in {
    val lemmatizer: EnglishLemmatizer = new EnglishLemmatizer(dictionary)
    val tokens: List[String] = List.empty[String]
    assert(lemmatizer.lemmatize(tokens) === List.empty[LemmaResult])
  }
}
