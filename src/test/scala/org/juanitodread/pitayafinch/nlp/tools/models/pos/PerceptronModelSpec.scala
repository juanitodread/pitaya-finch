package org.juanitodread.pitayafinch.nlp.tools.models.pos

import opennlp.tools.postag.POSModel
import org.juanitodread.pitayafinch.UnitSpec

object PerceptronModelFixture {
  val model = new PerceptronModel()
}

class PerceptronModelSpec extends UnitSpec {
  "A PerceptronModel" should "have a defined name" in {
    val model = PerceptronModelFixture.model
    assert(model.getName === "Perceptron")
  }

  it should "have a valid OpenNLP POS Model" in {
    val model = PerceptronModelFixture.model
    assert(model.getNlpModel.isInstanceOf[POSModel] === true)
  }

  it should "have a valid OpenNLP POS Model version" in {
    val model = PerceptronModelFixture.model
    assert(model.getNlpModel.getVersion.toString == "1.5.0")
  }
}
