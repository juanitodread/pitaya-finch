package org.juanitodread.pitayafinch.nlp.tools.entities

import opennlp.tools.namefind.NameFinderME
import opennlp.tools.util.Span
import org.juanitodread.pitayafinch.model.nlp.entities.Entity
import org.juanitodread.pitayafinch.nlp.tools.entities.models.Model
import org.juanitodread.pitayafinch.nlp.tools.tokenize.Tokenizer

class EntityRecognizer[T <: Model](model: T) {
  private final val nameFinder: NameFinderME = new NameFinderME(model.getModel())

  def find(sentence: String): List[Entity] = {
    val tokens: Array[String] = Tokenizer.maxEntropy(sentence).toArray[String]
    val spans: Array[Span] = nameFinder.find(tokens)
    spans.map { span =>
      val entityName = tokens.slice(span.getStart, span.getEnd).mkString(" ")
      Entity(entityName, model.getName(), span.getProb)
    }.toList
  }
}
