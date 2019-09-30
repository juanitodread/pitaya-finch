package org.juanitodread.pitayafinch.nlp.tools.entities.models

class PersonEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-person.bin") {
  override def getName(): String = "Person"
}

object PersonEntityModel {
  private val model: PersonEntityModel = new PersonEntityModel()
  def apply(): PersonEntityModel = model
}
