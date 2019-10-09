package org.juanitodread.pitayafinch.nlp.tools.models.entities

import org.juanitodread.pitayafinch.UnitSpec

object DateEntityModelFixture {
  val model: DateEntityModel = new DateEntityModel()
}

class DateEntityModelSpec extends UnitSpec {
  "A DateEntityModel" should "have a defined name" in {
    val model = DateEntityModelFixture.model
    assert(model.getName === "Date")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = DateEntityModelFixture.model
    assert(model.getNlpModel.getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = DateEntityModelFixture.model
    assert(model.getNlpModel.getVersion.toString === "1.5.0")
  }
}
