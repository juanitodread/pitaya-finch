package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PercentageEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-percentage.bin") {
  override def getName: String = "Percentage"
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
