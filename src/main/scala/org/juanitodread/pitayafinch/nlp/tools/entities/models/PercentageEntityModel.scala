package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PercentageEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-percentage.bin") {
  override def getName(): String = "Percentage"
}

object PercentageEntityModel {
  private val model: PercentageEntityModel = new PercentageEntityModel()
  def apply(): PercentageEntityModel = model
}

object PercentageEntityModelAsync {
  def apply(): Future[PercentageEntityModel] = Future[PercentageEntityModel] {
    new PercentageEntityModel()
  }
}
