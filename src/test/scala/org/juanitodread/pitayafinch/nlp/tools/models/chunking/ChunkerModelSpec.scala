package org.juanitodread.pitayafinch.nlp.tools.models.chunking

import org.juanitodread.pitayafinch.UnitSpec

object ChunkerModelFixture {
  val model: ChunkerModel = new ChunkerModel()
}

class ChunkerModelSpec extends UnitSpec {
  "A ChunkerModel" should "have a defined name" in {
    val model = ChunkerModelFixture.model
    assert(model.getName === "Chunker")
  }

  it should "have a valid OpenNLP Chunker Model in English" in {
    val model = ChunkerModelFixture.model
    assert(model.getNlpModel.getLanguage === "en")
  }

  it should "have a valid OpenNLP Chunker Model version" in {
    val model = ChunkerModelFixture.model
    assert(model.getNlpModel.getVersion.toString === "1.5.0")
  }
}
