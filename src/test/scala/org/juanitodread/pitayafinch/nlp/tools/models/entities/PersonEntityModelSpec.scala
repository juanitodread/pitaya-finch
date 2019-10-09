package org.juanitodread.pitayafinch.nlp.tools.models.entities

import org.juanitodread.pitayafinch.UnitSpec

object PersonEntityModelFixture {
  val model: PersonEntityModel = new PersonEntityModel()
}

class PersonEntityModelSpec extends UnitSpec {
  "A PersonEntityModel" should "have a defined name" in {
    val model = PersonEntityModelFixture.model
    assert(model.getName === "Person")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = PersonEntityModelFixture.model
    assert(model.getNlpModel.getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = PersonEntityModelFixture.model
    assert(model.getNlpModel.getVersion.toString === "1.5.0")
  }
}
