package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TimeEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-time.bin") {
  override def getName(): String = "Time"
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
