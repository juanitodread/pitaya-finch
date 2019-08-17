package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.tokenizer.Algorithm
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

class PipelineSpec extends UnitSpec {
  "A PipelineBuilder" should "create builds the given Pipeline object" in {
    val pipeline = PipelineBuilder(
      new Tokenizer("Vamos buscando las formas OCULTAs de UnA mAnTarraya", Algorithm.SIMPLE),
      List(new LowerCaseConverter, new StopWordsRemover),
      new Stemmer).build()

    println(pipeline)
    println(pipeline.produce())
    assert(pipeline.toString === "Tokenizer -> LowerCaseConverter -> StopWordsRemover -> Stemmer")
  }

  "A Pipeline" should "be invalid if not start with Source object" in {
    //    val start: Tokenizer = new Tokenizer("", Algorithm.SIMPLE)
    //    val end: LowerCaseConverter = new LowerCaseConverter
    //    var result: Pipeline[Unit, Tokens] = start
    //    val res = new Stemmer
    //    val pip = start -> end
    //    val x = pip -> end -> end
    //    val xx = pip -> end -> end -> end
    //    val yyy: Pipeline[Unit, Tokens] = start
    //    val tttt = start -> end -> end -> res
    //    result = result -> end
    //    result = result -> end
    //    println(result -> res)
    //
    //    val builder = PipelineBuilder(
    //      new Tokenizer("", Algorithm.SIMPLE),
    //      List(end, end, end),
    //      res)
    //    println(builder)
    //    println(builder.build())
    //val pipeline = end -> start
    //assert(pipeline.toString === "LowerCaseConverter -> Tokenizer")
  }

  //  val paragraph =
  //    """As machines become increasingly capable, tasks considered to require "intelligence"
  //      |are often removed from the definition of AI, a phenomenon known as the AI effect.
  //      |""".stripMargin
  //  "A Pipeline" should "perform a chain of tasks according to the Pipeline definition" in {
  //    val pipeline = new Tokenizer(paragraph, Algorithm.MAX_ENTROPY) ->
  //      new LowerCaseConverter ->
  //      new StopWordsRemover
  //
  //    println(pipeline)
  //    println(pipeline.produce())
  //
  //    assert(pipeline.toString === "Prepare => LowerCase => Reverse => Reverse => Reverse => UpperCase")
  //    assert(pipeline.produce() === "AINROFILAC")
  //  }
}