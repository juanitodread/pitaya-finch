package org.juanitodread.pitayafinch.nlp.tools.entities.training

class TimeEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-time.bin") {
  override def getName(): String = "Time"
}

object TimeEntityModel {
  private val model: TimeEntityModel = new TimeEntityModel()
  def apply(): TimeEntityModel = model
}
