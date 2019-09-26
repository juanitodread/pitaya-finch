package org.juanitodread.pitayafinch.nlp.tools.entities.training

class MoneyEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-money.bin") {
  override def getName(): String = "Money"
}

object MoneyEntityModel {
  private val model: MoneyEntityModel = new MoneyEntityModel()
  def apply(): MoneyEntityModel = model
}
