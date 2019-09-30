package org.juanitodread.pitayafinch.nlp.tools.entities.models

class PercentageEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-percentage.bin") {
  override def getName(): String = "Percentage"
}

object PercentageEntityModel {
  private val model: PercentageEntityModel = new PercentageEntityModel()
  def apply(): PercentageEntityModel = model
}
