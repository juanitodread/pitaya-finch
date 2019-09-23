package org.juanitodread.pitayafinch.nlp.tools.sentences

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.sentences.FinderResult

class FinderSpec extends UnitSpec {
  val trainingModel: String = "/nlp/models/sentences/en-sent.bin"

  "A Finder" should "fail when provided training model does not exist" in {
    assertThrows[IllegalArgumentException] {
      new Finder("invalid-path")
    }
  }

  it should "get an empty list of sentences when empty text" in {
    val finder: Finder = new Finder(trainingModel)
    assert(finder.find("") === List.empty[FinderResult])
  }

  it should "get a list of sentences" in {
    val finder: Finder = new Finder(trainingModel)
    val text: String = "When determining the end of sentences we need to consider several factors. " +
      "Sentences may end with exclamation marks! Or possibly questions marks? " +
      "Within sentences we may find numbers like 3.14159, abbreviations such as found in Mr. Smith, " +
      "and possibly ellipses either within a sentence ..., or at the end of a sentence..."

    val sentences: List[String] = finder.find(text).map(result => result.sentence) // ignoring confidence
    assert(sentences === List(
      "When determining the end of sentences we need to consider several factors.",
      "Sentences may end with exclamation marks!",
      "Or possibly questions marks?",
      "Within sentences we may find numbers like 3.14159, abbreviations such as found in Mr. Smith, " +
        "and possibly ellipses either within a sentence ..., or at the end of a sentence..."))
  }

  "A Finder object" should "format the confidence result number with default precision" in {
    assert(Finder.round(0.123456789) === 0.123)
  }

  it should "format the confidence result number with min precision number allowed" in {
    assert(Finder.round(0.123456789, 1) === 0.1)
  }

  it should "format the confidence result number with max precision number allowed" in {
    assert(Finder.round(0.123456789, 5) === 0.12346) // 5 rounds to 6
  }

  it should "fail when zero precision value is provided" in {
    assertThrows[IllegalArgumentException] {
      Finder.round(0.123456789, 0)
    }
  }

  it should "fail when negative precision value is provided" in {
    assertThrows[IllegalArgumentException] {
      Finder.round(0.123456789, -1)
    }
  }

  it should "fail when precision value provided is greater than max allowed" in {
    assertThrows[IllegalArgumentException] {
      Finder.round(0.123456789, 5 + 1)
    }
  }
}
