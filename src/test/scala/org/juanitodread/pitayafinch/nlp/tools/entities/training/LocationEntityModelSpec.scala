package org.juanitodread.pitayafinch.nlp.tools.entities.training

import org.juanitodread.pitayafinch.UnitSpec

object LocationEntityModelFixture {
  val model: LocationEntityModel = new LocationEntityModel()
}

class LocationEntityModelSpec extends UnitSpec {
  "A LocationEntityModel" should "have a defined name" in {
    val model = LocationEntityModelFixture.model
    assert(model.getName() === "Location")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = LocationEntityModelFixture.model
    assert(model.getModel().getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = LocationEntityModelFixture.model
    assert(model.getModel().getVersion.toString === "1.5.0")
  }
}
