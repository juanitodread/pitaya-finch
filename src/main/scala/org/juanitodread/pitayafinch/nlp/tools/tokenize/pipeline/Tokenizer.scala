package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.model.nlp.tokenizer.Algorithm.Algorithm
import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens
import org.juanitodread.pitayafinch.nlp.tools.tokenize.{ Tokenizer => TokenizerImpl }

class Tokenizer(paragraph: String, algorithm: Algorithm) extends Source[Tokens] {
  override val chain: String = "Tokenizer"

  override def produce: Unit => Tokens = {
    _ => TokenizerImpl.tokenize(paragraph, algorithm)
  }
}

object Tokenizer {
  type Tokens = List[String]
}
