package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-location.bin") {
  override def getName: String = "Location"
}

object LocationEntityModel {
  private val model: LocationEntityModel = new LocationEntityModel()
  def apply(): LocationEntityModel = model
}

object LocationEntityModelAsync {
  def apply(): Future[LocationEntityModel] = Future[LocationEntityModel] {
    new LocationEntityModel()
  }
}
