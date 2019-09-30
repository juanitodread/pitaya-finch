package org.juanitodread.pitayafinch.nlp.tools.tokenize

import org.juanitodread.pitayafinch.UnitSpec

class LowerCaseConverterSpec extends UnitSpec {
  "A LowerCaseConverter" should "convert a given empty string into lowercase" in {
    val converter = new LowerCaseConverter()
    assert(converter.toLowerCase("") === "")
  }

  it should "convert a given string into lowercase" in {
    val converter = new LowerCaseConverter()
    assert(converter.toLowerCase("This Is A Sample String") === "this is a sample string")
  }

  it should "convert a given lowercase string into lowercase" in {
    val converter = new LowerCaseConverter()
    assert(converter.toLowerCase("this is a sample string") === "this is a sample string")
  }

  it should "convert a given uppercase string into lowercase" in {
    val converter = new LowerCaseConverter()
    assert(converter.toLowerCase("THIS IS A SAMPLE STRING") === "this is a sample string")
  }

  it should "convert a given empty list of tokens into lowercase" in {
    val converter = new LowerCaseConverter()
    assert(converter.toLowerCase(List.empty[String]) === List.empty[String])
  }

  it should "convert a given list of tokens into lowercase" in {
    val converter = new LowerCaseConverter()
    val tokens = List("This", "Is", "A", "Sample", "String")
    converter.toLowerCase(tokens) should contain theSameElementsInOrderAs List("this", "is", "a", "sample", "string")
  }
}
