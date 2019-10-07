package org.juanitodread.pitayafinch.nlp.tools.entities

import scala.concurrent.Await
import scala.concurrent.duration._
import opennlp.tools.namefind.NameFinderME
import opennlp.tools.util.Span

import org.juanitodread.pitayafinch.formatters.NumberFormatter
import org.juanitodread.pitayafinch.model.nlp.entities.Entity
import org.juanitodread.pitayafinch.nlp.tools.entities.models._
import org.juanitodread.pitayafinch.nlp.tools.tokenize.Tokenizer

class EntityRecognizer[T <: Model](model: T) extends NumberFormatter {
  def find(sentence: String): List[Entity] = {
    val nameFinder: NameFinderME = new NameFinderME(model.getModel())
    val tokens: Array[String] = Tokenizer.maxEntropy(sentence).toArray[String]
    val spans: Array[Span] = nameFinder.find(tokens)
    nameFinder.clearAdaptiveData()

    spans.map { span =>
      val entityName = tokens.slice(span.getStart, span.getEnd).mkString(" ")
      Entity(entityName, model.getName(), round(span.getProb))
    }.toList
  }
}

object EntityRecognizer extends NumberFormatter {
  private final val recognizers = List(
    DateEntityModelAsync(),
    LocationEntityModelAsync(),
    MoneyEntityModelAsync(),
    OrganizationEntityModelAsync(),
    PercentageEntityModelAsync(),
    PersonEntityModelAsync(),
    TimeEntityModelAsync()).map(futureModel => Await.result(futureModel, 5 seconds))
    .map(model => new EntityRecognizer(model))

  def apply(sentence: String): List[Entity] = {
    recognizers.map(_.find(sentence)).flatMap(_.toList)
  }
}
