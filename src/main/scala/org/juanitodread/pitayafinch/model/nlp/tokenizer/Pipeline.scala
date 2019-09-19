package org.juanitodread.pitayafinch.model.nlp.tokenizer

trait Results

sealed trait Step {
  def algorithm: Algorithm
}
case class Init(algorithm: Initializers.Initializer, strategy: Tokenizers.Tokenizer) extends Step
case class Stage(algorithm: Stagers.Stage) extends Step
case class Finalizer(algorithm: Finalizers.Finalizer) extends Step

case class Pipeline(init: Init, stages: List[Stage], finalizer: Finalizer)
case class PipelineRequest(text: String, pipeline: Pipeline)
case class PipelineResponse(pipeline: String, stemmerResult: Option[List[StemResult]], lemmaResult: Option[List[LemmaResult]])
