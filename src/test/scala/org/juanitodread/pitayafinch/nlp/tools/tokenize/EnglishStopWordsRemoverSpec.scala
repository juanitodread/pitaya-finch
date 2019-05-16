package org.juanitodread.pitayafinch.nlp.tools.tokenize

import org.juanitodread.pitayafinch.UnitSpec

class EnglishStopWordsRemoverSpec extends UnitSpec {
  "An EnglishStopWordsRemover" should "remove the 'this' stop word from a list of tokens" in {
    val remover = new EnglishStopWordsRemover()
    val tokens = List("remove", "this")
    remover.remove(tokens) should contain theSameElementsInOrderAs List("remove")
  }

  it should "remove many 'this' stop word from a list of tokens" in {
    val remover = new EnglishStopWordsRemover()
    val tokens = List("remove", "this", "token", "also", "this", "one")
    remover.remove(tokens) should contain theSameElementsInOrderAs List("remove", "token", "also", "one")
  }

  it should "remove many different stop words from a list of tokens" in {
    val remover = new EnglishStopWordsRemover()
    val tokens = List("remove", "this", "token", "or", "that", "one", "too")
    remover.remove(tokens) should contain theSameElementsInOrderAs List("remove", "token", "one", "too")
  }

  it should "not modify the given list of tokens if there are not stop words present" in {
    val remover = new EnglishStopWordsRemover()
    val tokens = List("just", "valid", "tokens")
    remover.remove(tokens) should contain theSameElementsInOrderAs List("just", "valid", "tokens")
  }

  it should "not modify the given empty list of tokens if there are not stop words present" in {
    val remover = new EnglishStopWordsRemover()
    val tokens = List.empty[String]
    assert(remover.remove(tokens) === List.empty[String])
  }

  it should "return an empty list of tokens if all tokens are stop words" in {
    val remover = new EnglishStopWordsRemover()
    val tokens = List("this", "is", "it")
    assert(remover.remove(tokens) === List.empty[String])
  }
}
