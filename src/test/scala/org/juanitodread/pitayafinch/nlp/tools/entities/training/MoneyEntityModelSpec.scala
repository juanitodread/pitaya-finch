package org.juanitodread.pitayafinch.nlp.tools.entities.training

import org.juanitodread.pitayafinch.UnitSpec

object MoneyEntityModelFixture {
  val model: MoneyEntityModel = new MoneyEntityModel()
}

class MoneyEntityModelSpec extends UnitSpec {
  "A MoneyEntityModel" should "have a defined name" in {
    val model = MoneyEntityModelFixture.model
    assert(model.getName() === "Money")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = MoneyEntityModelFixture.model
    assert(model.getModel().getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = MoneyEntityModelFixture.model
    assert(model.getModel().getVersion.toString === "1.5.0")
  }
}
