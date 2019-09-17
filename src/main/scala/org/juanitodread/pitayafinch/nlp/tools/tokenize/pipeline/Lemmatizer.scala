package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.model.nlp.tokenizer.LemmaResult
import org.juanitodread.pitayafinch.nlp.tools.tokenize.EnglishLemmatizer
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

class Lemmatizer extends Result[Tokens, List[LemmaResult]] {
  override val chain: String = "Lemmatizer"

  override def produce: Tokens => List[LemmaResult] = {
    tokens => EnglishLemmatizer(tokens)
  }
}
