package org.juanitodread.pitayafinch.nlp.tools.entities.fixtures

import org.juanitodread.pitayafinch.formatters.NumberFormatter
import org.juanitodread.pitayafinch.model.nlp.entities.Entity
import org.juanitodread.pitayafinch.nlp.tools.entities.EntityRecognizer
import org.juanitodread.pitayafinch.nlp.tools.models.entities._

object EntityRecognizerFixture extends NumberFormatter {
  private final val recognizers = List(
    DateEntityModel(),
    LocationEntityModel(),
    MoneyEntityModel(),
    OrganizationEntityModel(),
    PercentageEntityModel(),
    PersonEntityModel(),
    TimeEntityModel()).map(model => new EntityRecognizer(model))

  def apply(sentence: String): List[Entity] = {
    recognizers.map(_.find(sentence)).flatMap(_.toList)
  }
}