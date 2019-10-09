package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TimeEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-time.bin") {
  override def getName: String = "Time"
}

object TimeEntityModel {
  private val model: TimeEntityModel = new TimeEntityModel()
  def apply(): TimeEntityModel = model
}

object TimeEntityModelAsync {
  def apply(): Future[TimeEntityModel] = Future[TimeEntityModel] {
    new TimeEntityModel()
  }
}
