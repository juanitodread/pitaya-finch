package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DateEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-date.bin") {
  override def getName: String = "Date"
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
