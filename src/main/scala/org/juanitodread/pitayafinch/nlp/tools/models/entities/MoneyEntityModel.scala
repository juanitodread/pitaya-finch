package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MoneyEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-money.bin") {
  override def getName: String = "Money"
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
