package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.nlp.tools.tokenize.{ LowerCaseConverter => LowerCaseConverterImpl }
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

class LowerCaseConverter extends Step[Tokens, Tokens] {
  override val chain: String = "LowerCaseConverter"

  override def produce: Tokens => Tokens = {
    tokens => LowerCaseConverterImpl(tokens)
  }
}
