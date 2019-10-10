package org.juanitodread.pitayafinch.nlp.tools.models.chunking

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import opennlp.tools.chunker.{ ChunkerModel => NlpChunkerModel }

import org.juanitodread.pitayafinch.nlp.tools.models.AbstractModel

class ChunkerModel extends AbstractModel("/nlp/models/chunking/en-chunker.bin") {
  protected val model: NlpChunkerModel = new NlpChunkerModel(modelFile)
  logger.info(s"Chunker training model $getName loaded")

  override def getNlpModel: NlpChunkerModel = model
  override def getName: String = "Chunker"
}

object ChunkerModel {
  private val model: ChunkerModel = new ChunkerModel()
  def apply(): ChunkerModel = model
}

object ChunkerModelAsync {
  def apply(): Future[ChunkerModel] = Future[ChunkerModel] {
    new ChunkerModel()
  }
}
