package org.juanitodread.pitayafinch.nlp.tools.models.entities

import opennlp.tools.namefind.TokenNameFinderModel

import org.juanitodread.pitayafinch.nlp.tools.models.AbstractModel

abstract class FinderModel(path: String) extends AbstractModel(path) {
  protected val model: TokenNameFinderModel = new TokenNameFinderModel(modelFile)
  logger.info(s"Entity Recognizer training model $getName loaded")

  override def getNlpModel: TokenNameFinderModel = model
}
