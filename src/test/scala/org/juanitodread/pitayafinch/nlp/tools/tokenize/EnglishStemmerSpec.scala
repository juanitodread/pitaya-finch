package org.juanitodread.pitayafinch.nlp.tools.tokenize

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.StemResult

class EnglishStemmerSpec extends UnitSpec {
  "An EnglishStemmer" should "stem a word" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = "banking"
    assert(stemmer.stem(token) === "bank")
  }

  it should "stem a not common word" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = "!#$&/(&"
    assert(stemmer.stem(token) === "!#$&/(&")
  }

  it should "keep the original word when it is in simple form" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = "bank"
    assert(stemmer.stem(token) === "bank")
  }

  it should "keep the original word when it is an empty string" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = ""
    assert(stemmer.stem(token) === "")
  }

  it should "stem a list of words" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val tokens: List[String] = List("banking", "bank", "banked")
    stemmer.stem(tokens) should contain theSameElementsInOrderAs List("bank", "bank", "bank")
  }

  it should "keep the original list of words when it is an empty list" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val tokens: List[String] = List.empty[String]
    assert(stemmer.stem(tokens) === List.empty[String])
  }

  it should "stem a word and keep original word" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = "banking"
    assert(stemmer.stemKeepOriginal(token) === StemResult("banking", "bank"))
  }

  it should "keep the original word when it is in simple form and keep original word" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = "bank"
    assert(stemmer.stemKeepOriginal(token) === StemResult("bank", "bank"))
  }

  it should "keep the original word when it is an empty string and keep original word" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val token: String = ""
    assert(stemmer.stemKeepOriginal(token) === StemResult("", ""))
  }

  it should "stem a list of words and keep original words" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val tokens: List[String] = List("banking", "bank", "banked")
    stemmer.stemKeepOriginal(tokens) should contain theSameElementsInOrderAs List(
      StemResult("banking", "bank"),
      StemResult("bank", "bank"),
      StemResult("banked", "bank"))
  }

  it should "keep the original list of words when it is an empty list and keep original words" in {
    val stemmer: EnglishStemmer = new EnglishStemmer()
    val tokens: List[String] = List.empty[String]
    assert(stemmer.stemKeepOriginal(tokens) === List.empty[String])
  }
}
