package org.juanitodread.pitayafinch.nlp.tools.entities.models

class LocationEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-location.bin") {
  override def getName(): String = "Location"
}

object LocationEntityModel {
  private val model: LocationEntityModel = new LocationEntityModel()
  def apply(): LocationEntityModel = model
}
