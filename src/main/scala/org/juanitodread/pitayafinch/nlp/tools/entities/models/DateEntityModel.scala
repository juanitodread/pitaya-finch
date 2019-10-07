package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DateEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-date.bin") {
  override def getName(): String = "Date"
}

object DateEntityModel {
  private val model: DateEntityModel = new DateEntityModel()
  def apply(): DateEntityModel = model
}

object DateEntityModelAsync {
  def apply(): Future[DateEntityModel] = Future[DateEntityModel] {
    new DateEntityModel()
  }
}
