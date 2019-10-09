package org.juanitodread.pitayafinch.nlp.tools.models.entities

import org.juanitodread.pitayafinch.UnitSpec

object PercentageEntityModelFixture {
  val model: PercentageEntityModel = new PercentageEntityModel()
}

class PercentageEntityModelSpec extends UnitSpec {
  "A PercentageEntityModel" should "have a defined name" in {
    val model = PercentageEntityModelFixture.model
    assert(model.getName === "Percentage")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = PercentageEntityModelFixture.model
    assert(model.getNlpModel.getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = PercentageEntityModelFixture.model
    assert(model.getNlpModel.getVersion.toString === "1.5.0")
  }
}
