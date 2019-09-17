package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.Algorithm

class PipelineSpec extends UnitSpec {
  "A PipelineBuilder" should "builds the given Pipeline object" in {
    val pipeline = PipelineBuilder(
      new Tokenizer("hi", Algorithm.SIMPLE),
      List(new LowerCaseConverter, new StopWordsRemover),
      new Stemmer).build()

    assert(pipeline.toString === "Tokenizer -> LowerCaseConverter -> StopWordsRemover -> Stemmer")
  }
}