package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PersonEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-person.bin") {
  override def getName(): String = "Person"
}

object PersonEntityModel {
  private val model: PersonEntityModel = new PersonEntityModel()
  def apply(): PersonEntityModel = model
}

object PersonEntityModelAsync {
  def apply(): Future[PersonEntityModel] = Future[PersonEntityModel] {
    new PersonEntityModel()
  }
}
