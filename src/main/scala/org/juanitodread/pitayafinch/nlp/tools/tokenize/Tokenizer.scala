package org.juanitodread.pitayafinch.nlp.tools.tokenize

import opennlp.tools.tokenize.{ SimpleTokenizer, TokenizerME, TokenizerModel, WhitespaceTokenizer }

object Tokenizer {
  private final val simpleTokenizer = SimpleTokenizer.INSTANCE
  private final val whitespaceTokenizer = WhitespaceTokenizer.INSTANCE
  private final val maxEntropyTokenizer = new TokenizerME(MaxEntropyModel.instance())

  def simple(paragraph: String): List[String] = {
    simpleTokenizer.tokenize(paragraph).toList
  }

  def whitespace(paragraph: String): List[String] = {
    whitespaceTokenizer.tokenize(paragraph).toList
  }

  def maxEntropy(paragraph: String): List[String] = {
    maxEntropyTokenizer.tokenize(paragraph).toList
  }

}

object MaxEntropyModel {
  final val MODEL_PATH = "/nlp/models/tokenizer/en-token.bin"

  private final val modelFile = getClass.getResourceAsStream(MODEL_PATH)
  private final val model = new TokenizerModel(modelFile)

  def instance(): TokenizerModel = {
    model
  }

}
