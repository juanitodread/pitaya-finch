package org.juanitodread.pitayafinch.nlp.tools.tokenize

import org.juanitodread.pitayafinch.UnitSpec

class PipelineSpec extends UnitSpec {
  "A Pipeline" should "perform a chain of tasks according to the Pipeline definition" in {
    val pipeline = new Prepare("CaLiForNiA") ->
      new LowerCase ->
      new Reverse ->
      new Reverse ->
      new Reverse ->
      new UpperCase

    println(pipeline)
    println(pipeline.produce())

    assert(pipeline.toString === "Prepare => LowerCase => Reverse => Reverse => Reverse => UpperCase")
    assert(pipeline.produce() === "AINROFILAC")
  }
}