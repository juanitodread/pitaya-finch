package org.juanitodread.pitayafinch.nlp.tools.entities.models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class OrganizationEntityModel
  extends AbstractModel("/nlp/models/entities/en-ner-organization.bin") {
  override def getName(): String = "Organization"
}

object OrganizationEntityModel {
  private val model: OrganizationEntityModel = new OrganizationEntityModel()
  def apply(): OrganizationEntityModel = model
}

object OrganizationEntityModelAsync {
  def apply(): Future[OrganizationEntityModel] = Future[OrganizationEntityModel] {
    new OrganizationEntityModel()
  }
}
