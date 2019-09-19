package org.juanitodread.pitayafinch.routes.nlp.tools.tokenize

import cats.effect.IO
import io.finch._
import io.finch.catsEffect._
import io.finch.circe.dropNullValues._
import io.circe.generic.auto._
import shapeless.syntax.typeable._

import org.juanitodread.pitayafinch.model.nlp.tokenizer._
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.PipelineBuilder
import org.juanitodread.pitayafinch.routes.nlp.NlpEndpoint

object PipelineEndpoint extends NlpEndpoint {
  private final val pipelinePath = basePath :: "pipeline"

  private val postedPipeline: Endpoint[IO, PipelineRequest] = jsonBody[PipelineRequest]
  def pipeline(): Endpoint[IO, PipelineResponse] = post(pipelinePath :: postedPipeline) { request: PipelineRequest =>
    val init = request.pipeline.init.algorithm.getInstance(request.text, request.pipeline.init.strategy)
    val stages = request.pipeline.stages.map(_.algorithm.getInstance()).toList
    val finalizer = request.pipeline.finalizer.algorithm match {
      case stemmer: Finalizers.STEMMER.type => stemmer.getInstance()
      case lemmatizer: Finalizers.LEMMATIZER.type => lemmatizer.getInstance()
    }

    val pipeline = PipelineBuilder(init, stages, finalizer).build()
    val response = pipeline.produce() match {
      case stemmer if stemmer.cast[List[StemResult]].isDefined =>
        PipelineResponse(pipeline.chain, stemmer.cast[List[StemResult]], None)
      case lemmatizer if lemmatizer.cast[List[LemmaResult]].isDefined =>
        PipelineResponse(pipeline.chain, None, lemmatizer.cast[List[LemmaResult]])
    }

    Ok(response)
  }
}
