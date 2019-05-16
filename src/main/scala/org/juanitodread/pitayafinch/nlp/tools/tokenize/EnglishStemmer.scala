package org.juanitodread.pitayafinch.nlp.tools.tokenize

import opennlp.tools.stemmer.PorterStemmer
import org.juanitodread.pitayafinch.model.nlp.tokenizer.StemResult

class EnglishStemmer {
  private final val porterStemmer: PorterStemmer = new PorterStemmer()

  def stem(token: String): String = {
    porterStemmer.stem(token)
  }

  def stemKeepOriginal(token: String): StemResult = {
    StemResult(token, this.stem(token))
  }

  def stem(tokens: List[String]): List[String] = {
    tokens.map(this.stem)
  }

  def stemKeepOriginal(tokens: List[String]): List[StemResult] = {
    tokens.map(this.stemKeepOriginal)
  }
}
