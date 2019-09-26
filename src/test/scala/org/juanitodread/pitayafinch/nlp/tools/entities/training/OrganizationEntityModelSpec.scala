package org.juanitodread.pitayafinch.nlp.tools.entities.training

import org.juanitodread.pitayafinch.UnitSpec

object OrganizationEntityModelFixture {
  val model: OrganizationEntityModel = new OrganizationEntityModel()
}

class OrganizationEntityModelSpec extends UnitSpec {
  "A OrganizationEntityModel" should "have a defined name" in {
    val model = OrganizationEntityModelFixture.model
    assert(model.getName() === "Organization")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = OrganizationEntityModelFixture.model
    assert(model.getModel().getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = OrganizationEntityModelFixture.model
    assert(model.getModel().getVersion.toString === "1.5.0")
  }
}
