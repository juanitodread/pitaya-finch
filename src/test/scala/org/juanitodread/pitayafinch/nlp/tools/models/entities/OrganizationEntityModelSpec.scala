package org.juanitodread.pitayafinch.nlp.tools.models.entities

import org.juanitodread.pitayafinch.UnitSpec

object OrganizationEntityModelFixture {
  val model: OrganizationEntityModel = new OrganizationEntityModel()
}

class OrganizationEntityModelSpec extends UnitSpec {
  "A OrganizationEntityModel" should "have a defined name" in {
    val model = OrganizationEntityModelFixture.model
    assert(model.getName === "Organization")
  }

  it should "have a valid OpenNLP Entity Model in English" in {
    val model = OrganizationEntityModelFixture.model
    assert(model.getNlpModel.getLanguage === "en")
  }

  it should "have a valid OpenNLP Entity Model version" in {
    val model = OrganizationEntityModelFixture.model
    assert(model.getNlpModel.getVersion.toString === "1.5.0")
  }
}
