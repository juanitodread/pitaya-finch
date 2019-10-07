package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LocationEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-location.bin") {
  override def getName(): String = "Location"
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
