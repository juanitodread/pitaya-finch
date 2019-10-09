package org.juanitodread.pitayafinch.nlp.tools.models.entities

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class OrganizationEntityModel
  extends FinderModel("/nlp/models/entities/en-ner-organization.bin") {
  override def getName: String = "Organization"
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
