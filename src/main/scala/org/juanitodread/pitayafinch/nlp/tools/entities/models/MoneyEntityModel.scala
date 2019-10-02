package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class MoneyEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-money.bin") {
  override def getName(): String = "Money"
}

object MoneyEntityModel {
  private val model: MoneyEntityModel = new MoneyEntityModel()
  def apply(): MoneyEntityModel = model
}

object MoneyEntityModelAsync {
  def apply(): Future[MoneyEntityModel] = Future[MoneyEntityModel] {
    new MoneyEntityModel()
  }
}
