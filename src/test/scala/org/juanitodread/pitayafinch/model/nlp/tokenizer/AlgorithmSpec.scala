package org.juanitodread.pitayafinch.model.nlp.tokenizer

import org.juanitodread.pitayafinch.UnitSpec

class AlgorithmSpec extends UnitSpec {
  "An Algorithm" should "return a list of available tokenizer algorithms" in {
    assert(Algorithm.getAlgorithms() === List("SIMPLE", "WHITESPACE", "MAX_ENTROPY"))
  }
}
