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
}
