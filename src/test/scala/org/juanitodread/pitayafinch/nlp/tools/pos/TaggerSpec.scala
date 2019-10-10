package org.juanitodread.pitayafinch.nlp.tools.pos

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.chunking.Chunk
import org.juanitodread.pitayafinch.model.nlp.pos.{ Tag, TagsResult }
import org.juanitodread.pitayafinch.nlp.tools.models.pos.PerceptronModel
import org.juanitodread.pitayafinch.nlp.tools.pos.fixtures.TaggerFixture

class TaggerSpec extends UnitSpec {
  val model = new PerceptronModel

  "A Tagger" should "get the tags in a sentence" in {
    val tagger = new Tagger(model)
    val tags = tagger.tag("His election and policies have sparked numerous protests", withChunk = false)

    assert(tags === TagsResult(List(
      Tag("His", "PRP$", "Possessive pronoun"),
      Tag("election", "NN", "Noun, singular or mass"),
      Tag("and", "CC", "Coordinating conjunction"),
      Tag("policies", "NNS", "Noun, plural"),
      Tag("have", "VBP", "Verb, non-3rd person singular present"),
      Tag("sparked", "VBN", "Verb, past participle"),
      Tag("numerous", "JJ", "Adjective"),
      Tag("protests", "NNS", "Noun, plural")), None))
  }

  it should "get empty tags result when sentence is empty" in {
    val tagger = new Tagger(model)
    val tags = tagger.tag("", withChunk = false)

    assert(tags === TagsResult(List.empty[Tag], None))
  }

  it should "get the tags and chunks in a sentence" in {
    val tagger = new Tagger(model)
    val tags = tagger.tag("His election and policies have sparked numerous protests", withChunk = true)

    assert(tags === TagsResult(
      List(
        Tag("His", "PRP$", "Possessive pronoun"),
        Tag("election", "NN", "Noun, singular or mass"),
        Tag("and", "CC", "Coordinating conjunction"),
        Tag("policies", "NNS", "Noun, plural"),
        Tag("have", "VBP", "Verb, non-3rd person singular present"),
        Tag("sparked", "VBN", "Verb, past participle"),
        Tag("numerous", "JJ", "Adjective"),
        Tag("protests", "NNS", "Noun, plural")),
      Some(List(
        Chunk("His election and policies", "NP"),
        Chunk("have sparked", "VP"),
        Chunk("numerous protests", "NP")))))
  }

  it should "get empty tags and chunks result when sentence is empty" in {
    val tagger = new Tagger(model)
    val tags = tagger.tag("", withChunk = true)

    assert(tags === TagsResult(List.empty[Tag], Some(List.empty[Chunk])))
  }

  "A Tagger Object" should "be able to get the tags from a sentence" in {
    assert(TaggerFixture("His election and policies have sparked numerous protests") === TagsResult(List(
      Tag("His", "PRP$", "Possessive pronoun"),
      Tag("election", "NN", "Noun, singular or mass"),
      Tag("and", "CC", "Coordinating conjunction"),
      Tag("policies", "NNS", "Noun, plural"),
      Tag("have", "VBP", "Verb, non-3rd person singular present"),
      Tag("sparked", "VBN", "Verb, past participle"),
      Tag("numerous", "JJ", "Adjective"),
      Tag("protests", "NNS", "Noun, plural")), None))
  }

  it should "be able to get empty tag list when sentence is empty" in {
    assert(TaggerFixture("") === TagsResult(List.empty[Tag], None))
  }

  it should "be able to get the tags and chunks in a sentence" in {
    assert(TaggerFixture("His election and policies have sparked numerous protests", withChunk = true) === TagsResult(
      List(
        Tag("His", "PRP$", "Possessive pronoun"),
        Tag("election", "NN", "Noun, singular or mass"),
        Tag("and", "CC", "Coordinating conjunction"),
        Tag("policies", "NNS", "Noun, plural"),
        Tag("have", "VBP", "Verb, non-3rd person singular present"),
        Tag("sparked", "VBN", "Verb, past participle"),
        Tag("numerous", "JJ", "Adjective"),
        Tag("protests", "NNS", "Noun, plural")),
      Some(List(
        Chunk("His election and policies", "NP"),
        Chunk("have sparked", "VP"),
        Chunk("numerous protests", "NP")))))
  }

  it should "be able to get empty tags and chunks result when sentence is empty" in {
    assert(TaggerFixture("", withChunk = true) === TagsResult(List.empty[Tag], Some(List.empty[Chunk])))
  }
}
