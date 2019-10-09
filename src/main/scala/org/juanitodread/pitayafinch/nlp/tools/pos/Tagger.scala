package org.juanitodread.pitayafinch.nlp.tools.pos

import scala.concurrent.Await
import scala.concurrent.duration._
import opennlp.tools.postag.POSTaggerME

import org.juanitodread.pitayafinch.model.nlp.pos.Tag
import org.juanitodread.pitayafinch.nlp.tools.models.pos._
import org.juanitodread.pitayafinch.nlp.tools.tokenize.Tokenizer

class Tagger[T <: PosModel](model: T) extends Tags {
  def tag(sentence: String): List[Tag] = {
    val tagger: POSTaggerME = new POSTaggerME(model.getNlpModel)
    val tokens: Array[String] = Tokenizer.maxEntropy(sentence).toArray[String]
    val taggedTokens = tagger.tag(tokens)

    tokens.zip(taggedTokens)
      .map(tag => Tag(tag._1, tag._2, tags.getOrElse(tag._2, "")))
      .toList
  }
}

object Tagger {
  private final val tagger = new Tagger(Await.result(PerceptronModelAsync(), 5 seconds))

  def apply(sentence: String): List[Tag] = {
    tagger.tag(sentence)
  }
}
