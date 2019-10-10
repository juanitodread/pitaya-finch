package org.juanitodread.pitayafinch.nlp.tools.chunking

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.chunking.Chunk
import org.juanitodread.pitayafinch.nlp.tools.models.chunking.ChunkerModel

class ChunkerSpec extends UnitSpec {
  "A Chunker" should "split a sentence into chunks" in {
    val chunker = new Chunker(new ChunkerModel)
    val tokens = List("His", "election", "and", "polices", "have", "sparked", "numerous", "protests")
    val tags = List("PRP$", "NN", "CC", "NNS", "VBP", "VBN", "JJ", "NNS")
    assert(chunker.chunk(tokens, tags) === List(
      Chunk("His election and polices", "NP"),
      Chunk("have sparked", "VP"),
      Chunk("numerous protests", "NP")))
  }

  it should "get empty chunks array when sentence is empty" in {
    val chunker = new Chunker(new ChunkerModel)
    assert(chunker.chunk(List.empty[String], List.empty[String]) === List.empty[Chunk])
  }

  "A Chunker Object" should "be able to split a sentence into chunks" in {
    val tokens = List("His", "election", "and", "polices", "have", "sparked", "numerous", "protests")
    val tags = List("PRP$", "NN", "CC", "NNS", "VBP", "VBN", "JJ", "NNS")
    assert(Chunker(tokens, tags) === List(
      Chunk("His election and polices", "NP"),
      Chunk("have sparked", "VP"),
      Chunk("numerous protests", "NP")))
  }

  it should "be able to get empty chunks array when sentence is empty" in {
    assert(Chunker(List.empty[String], List.empty[String]) === List.empty[Chunk])
  }
}
