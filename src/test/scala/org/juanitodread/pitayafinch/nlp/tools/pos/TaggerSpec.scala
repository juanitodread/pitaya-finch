package org.juanitodread.pitayafinch.nlp.tools.pos

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.pos.Tag
import org.juanitodread.pitayafinch.nlp.tools.models.pos.PerceptronModel
import org.juanitodread.pitayafinch.nlp.tools.pos.fixtures.TaggerFixture

class TaggerSpec extends UnitSpec {
  val model = new PerceptronModel

  "A Tagger" should "get the tags in a sentence" in {
    val tagger = new Tagger(model)
    val tags = tagger.tag("His election and policies have sparked numerous protests")

    assert(tags === List(
      Tag("His", "PRP$", "Possessive pronoun"),
      Tag("election", "NN", "Noun, singular or mass"),
      Tag("and", "CC", "Coordinating conjunction"),
      Tag("policies", "NNS", "Noun, plural"),
      Tag("have", "VBP", "Verb, non-3rd person singular present"),
      Tag("sparked", "VBN", "Verb, past participle"),
      Tag("numerous", "JJ", "Adjective"),
      Tag("protests", "NNS", "Noun, plural")))
  }

  it should "get empty tags result when sentence is empty" in {
    val tagger = new Tagger(model)
    val tags = tagger.tag("")

    assert(tags === List.empty[Tag])
  }

  "A Tagger Object" should "be able to get the tags from a sentence" in {
    assert(TaggerFixture("His election and policies have sparked numerous protests") === List(
      Tag("His", "PRP$", "Possessive pronoun"),
      Tag("election", "NN", "Noun, singular or mass"),
      Tag("and", "CC", "Coordinating conjunction"),
      Tag("policies", "NNS", "Noun, plural"),
      Tag("have", "VBP", "Verb, non-3rd person singular present"),
      Tag("sparked", "VBN", "Verb, past participle"),
      Tag("numerous", "JJ", "Adjective"),
      Tag("protests", "NNS", "Noun, plural")))
  }

  it should "be able to get empty tag list when sentence is empty" in {
    assert(TaggerFixture("") === List.empty[Tag])
  }
}
