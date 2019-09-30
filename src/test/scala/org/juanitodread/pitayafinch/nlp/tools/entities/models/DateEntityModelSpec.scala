package org.juanitodread.pitayafinch.nlp.tools.entities.models

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.nlp.tools.entities.models.DateEntityModel

object DateEntityModelFixture {
  val model: DateEntityModel = new DateEntityModel()
}

class DateEntityModelSpec extends UnitSpec {
  "A DateEntityModel" should "have a defined name" in {
    val model = DateEntityModelFixture.model
    assert(model.getName() === "Date")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = DateEntityModelFixture.model
    assert(model.getModel().getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = DateEntityModelFixture.model
    assert(model.getModel().getVersion.toString === "1.5.0")
  }
}
