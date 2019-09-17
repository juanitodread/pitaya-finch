package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.model.nlp.tokenizer.StemResult
import org.juanitodread.pitayafinch.nlp.tools.tokenize.EnglishStemmer
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

class Stemmer extends Result[Tokens, List[StemResult]] {
  override val chain: String = "Stemmer"

  override def produce: Tokens => List[StemResult] = {
    tokens => EnglishStemmer(tokens)
  }
}
