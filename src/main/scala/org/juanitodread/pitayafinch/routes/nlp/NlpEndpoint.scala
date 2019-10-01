package org.juanitodread.pitayafinch.routes.nlp

import io.finch.catsEffect._

import org.juanitodread.pitayafinch.routes.BaseEndpoint
import org.juanitodread.pitayafinch.routes.nlp.tools.entities.EntitiesEndpoint
import org.juanitodread.pitayafinch.routes.nlp.tools.tokenize._
import org.juanitodread.pitayafinch.routes.nlp.tools.sentences._

trait NlpEndpoint extends BaseEndpoint {
  override protected def basePath = super.basePath :: "nlp"
}

object NlpEndpoints {
  def build() = {
    TokenizerEndpoint.getAlgorithms() :+:
      TokenizerEndpoint.tokenize() :+:
      NormalizerEndpoint.lowercase() :+:
      NormalizerEndpoint.stopwords() :+:
      NormalizerEndpoint.stemmer() :+:
      NormalizerEndpoint.lemmatizer() :+:
      PipelineEndpoint.pipeline() :+:
      FinderEndpoint.find() :+:
      EntitiesEndpoint.getEntities()
  }
}
