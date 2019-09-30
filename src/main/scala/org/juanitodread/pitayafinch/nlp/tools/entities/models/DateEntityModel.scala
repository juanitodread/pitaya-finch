package org.juanitodread.pitayafinch.nlp.tools.entities.models

class DateEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-date.bin") {
  override def getName(): String = "Date"
}

object DateEntityModel {
  private val model: DateEntityModel = new DateEntityModel()
  def apply(): DateEntityModel = model
}
