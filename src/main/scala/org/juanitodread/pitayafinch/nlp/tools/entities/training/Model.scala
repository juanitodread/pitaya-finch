package org.juanitodread.pitayafinch.nlp.tools.entities.training

import java.io.InputStream

import opennlp.tools.namefind.TokenNameFinderModel
import org.slf4j.{ Logger, LoggerFactory }

trait Model {
  def getName(): String
  def getModel(): TokenNameFinderModel
}

abstract class AbstractModel(modelPath: String) extends Model {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  logger.info(s"Reading Entity Recognizer model from: ${modelPath}")
  protected val modelFile: InputStream = getClass.getResourceAsStream(modelPath)
  require(modelFile != null, "Training model must exists")
  protected val model: TokenNameFinderModel = new TokenNameFinderModel(modelFile)
  logger.info(s"Entity Recognizer model ${getName()} loaded")

  override def getModel(): TokenNameFinderModel = model
}
