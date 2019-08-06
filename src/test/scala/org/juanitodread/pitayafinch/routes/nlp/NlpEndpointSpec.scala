package org.juanitodread.pitayafinch.routes.nlp

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.routes.nlp.tools.{ NormalizerEndpoint, TokenizerEndpoint }

class NlpEndpointsSpec extends UnitSpec {
  "A NlpEndpointsSpec" should "build NLP endpoints" in {
    NlpEndpoints.build().toString should equal((
      TokenizerEndpoint.getAlgorithms() :+:
      TokenizerEndpoint.tokenize() :+:
      NormalizerEndpoint.lowercase()).toString)
  }
}
