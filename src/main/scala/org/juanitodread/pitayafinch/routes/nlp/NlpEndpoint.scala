package org.juanitodread.pitayafinch.routes.nlp

import io.finch.catsEffect._

import org.juanitodread.pitayafinch.routes.BaseEndpoint
import org.juanitodread.pitayafinch.routes.nlp.tools.tokenize.{ NormalizerEndpoint, TokenizerEndpoint }

trait NlpEndpoint extends BaseEndpoint {
  override protected def basePath = super.basePath :: "nlp"
}

object NlpEndpoints {
  def build() = {
    TokenizerEndpoint.getAlgorithms() :+:
      TokenizerEndpoint.tokenize() :+:
      NormalizerEndpoint.lowercase() :+:
      NormalizerEndpoint.stopwords() :+:
      NormalizerEndpoint.stemmer()
  }
}
