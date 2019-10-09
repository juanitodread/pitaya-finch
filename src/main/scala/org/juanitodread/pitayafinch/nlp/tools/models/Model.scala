package org.juanitodread.pitayafinch.nlp.tools.models

import java.io.InputStream

import opennlp.tools.util.model.BaseModel
import org.slf4j.{ Logger, LoggerFactory }

trait Model {
  def getName: String
  def getPath: String
  def getNlpModel: BaseModel
}

abstract class AbstractModel(path: String) extends Model {
  protected final val logger: Logger = LoggerFactory.getLogger(getClass)

  logger.info(s"Reading $getName training model from: $getPath")
  protected val modelFile: InputStream = getClass.getResourceAsStream(getPath)
  require(modelFile != null, "Training model must exists")

  override def getPath: String = path
}
