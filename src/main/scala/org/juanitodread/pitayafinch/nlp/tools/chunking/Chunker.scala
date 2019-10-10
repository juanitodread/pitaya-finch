package org.juanitodread.pitayafinch.nlp.tools.chunking

import scala.concurrent.Await
import scala.concurrent.duration._
import opennlp.tools.chunker.ChunkerME

import org.juanitodread.pitayafinch.model.nlp.chunking.Chunk
import org.juanitodread.pitayafinch.nlp.tools.models.chunking.{ ChunkerModel, ChunkerModelAsync }

class Chunker(model: ChunkerModel) {
  def chunk(tokens: List[String], tags: List[String]): List[Chunk] = {
    val chunker: ChunkerME = new ChunkerME(model.getNlpModel)
    (for (span <- chunker.chunkAsSpans(tokens.toArray, tags.toArray)) yield {
      Chunk(tokens.slice(span.getStart, span.getEnd).mkString(" "), span.getType)
    }).toList
  }
}

object Chunker {
  private final val chunker = new Chunker(Await.result(ChunkerModelAsync(), 5 seconds))

  def apply(tokens: List[String], tags: List[String]): List[Chunk] = {
    chunker.chunk(tokens, tags)
  }
}
