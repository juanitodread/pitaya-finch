package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PersonEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-person.bin") {
  override def getName: String = "Person"
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
