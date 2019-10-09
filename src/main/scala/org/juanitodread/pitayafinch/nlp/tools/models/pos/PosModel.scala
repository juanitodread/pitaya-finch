package org.juanitodread.pitayafinch.nlp.tools.models.pos

import opennlp.tools.postag.POSModel
import org.juanitodread.pitayafinch.nlp.tools.models.AbstractModel

abstract class PosModel(path: String) extends AbstractModel(path) {
  protected val model: POSModel = new POSModel(modelFile)
  logger.info(s"POS training model $getName loaded")

  override def getNlpModel: POSModel = model
}
