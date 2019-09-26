package org.juanitodread.pitayafinch.nlp.tools.entities.training

import org.juanitodread.pitayafinch.UnitSpec

object PercentageEntityModelFixture {
  val model: PercentageEntityModel = new PercentageEntityModel()
}

class PercentageEntityModelSpec extends UnitSpec {
  "A PercentageEntityModel" should "have a defined name" in {
    val model = PercentageEntityModelFixture.model
    assert(model.getName() === "Percentage")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = PercentageEntityModelFixture.model
    assert(model.getModel().getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = PercentageEntityModelFixture.model
    assert(model.getModel().getVersion.toString === "1.5.0")
  }
}
