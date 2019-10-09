package org.juanitodread.pitayafinch.nlp.tools.models.pos

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PerceptronModel
  extends PosModel("/nlp/models/pos/en-pos-perceptron.bin") {
  override def getName: String = "Perceptron"
}

object PerceptronModel {
  private val model: PerceptronModel = new PerceptronModel()
  def apply(): PerceptronModel = model
}

object PerceptronModelAsync {
  def apply(): Future[PerceptronModel] = Future[PerceptronModel] {
    new PerceptronModel()
  }
}
