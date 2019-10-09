package org.juanitodread.pitayafinch.nlp.tools.models.entities

import org.juanitodread.pitayafinch.UnitSpec

object TimeEntityModelFixture {
  val model: TimeEntityModel = new TimeEntityModel()
}

class TimeEntityModelSpec extends UnitSpec {
  "A TimeEntityModel" should "have a defined name" in {
    val model = TimeEntityModelFixture.model
    assert(model.getName === "Time")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = TimeEntityModelFixture.model
    assert(model.getNlpModel.getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = TimeEntityModelFixture.model
    assert(model.getNlpModel.getVersion.toString === "1.5.0")
  }
}
