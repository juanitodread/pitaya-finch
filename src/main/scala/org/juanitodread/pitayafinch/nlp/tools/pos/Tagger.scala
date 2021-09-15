package org.juanitodread.pitayafinch.nlp.tools.pos

import scala.concurrent.Await
import scala.concurrent.duration._
import opennlp.tools.postag.POSTaggerME

import org.juanitodread.pitayafinch.model.nlp.chunking.Chunk
import org.juanitodread.pitayafinch.model.nlp.pos.{ Tag, TagsResult }
import org.juanitodread.pitayafinch.nlp.tools.chunking.Chunker
import org.juanitodread.pitayafinch.nlp.tools.models.pos._
import org.juanitodread.pitayafinch.nlp.tools.tokenize.Tokenizer

class Tagger[T <: PosModel](model: T) extends Tags {
  def tag(sentence: String, withChunk: Boolean): TagsResult = {
    val tagger: POSTaggerME = new POSTaggerME(model.getNlpModel)
    val tokens: Array[String] = Tokenizer.maxEntropy(sentence).toArray[String]
    val taggedTokens = tagger.tag(tokens)

    val chunks: Option[List[Chunk]] = withChunk match {
      case true => Some(Chunker(tokens.toList, taggedTokens.toList))
      case false => None
    }

    val tags = tokens.zip(taggedTokens)
      .map(tag => Tag(tag._1, tag._2, tagsMap.getOrElse(tag._2, "")))
      .toList

    TagsResult(tags, chunks)
  }
}

object Tagger {
  private final val tagger = new Tagger(Await.result(PerceptronModelAsync(), 5.seconds))

  def apply(sentence: String, withChunk: Boolean = false): TagsResult = {
    tagger.tag(sentence, withChunk)
  }
}
