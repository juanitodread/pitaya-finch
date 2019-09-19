package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.nlp.tools.tokenize.EnglishStopWordsRemover
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

class StopWordsRemover extends Step[Tokens, Tokens] {
  override val chain: String = "StopWordsRemover"

  override def produce: Tokens => Tokens = {
    tokens => EnglishStopWordsRemover(tokens)
  }
}
