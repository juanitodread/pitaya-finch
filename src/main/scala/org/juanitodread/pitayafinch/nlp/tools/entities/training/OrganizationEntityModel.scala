package org.juanitodread.pitayafinch.nlp.tools.entities.training

class OrganizationEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-organization.bin") {
  override def getName(): String = "Organization"
}

object OrganizationEntityModel {
  private val model: OrganizationEntityModel = new OrganizationEntityModel()
  def apply(): OrganizationEntityModel = model
}
