package org.juanitodread.pitayafinch.nlp.tools.tokenize

import opennlp.tools.tokenize.{
  SimpleTokenizer,
  TokenizerME,
  TokenizerModel,
  WhitespaceTokenizer
}
import org.slf4j.{ Logger, LoggerFactory }

import org.juanitodread.pitayafinch.model.nlp.tokenizer.Tokenizers._

object Tokenizer {
  private final val simpleTokenizer = SimpleTokenizer.INSTANCE
  private final val whitespaceTokenizer = WhitespaceTokenizer.INSTANCE

  def simple(paragraph: String): List[String] = {
    simpleTokenizer.tokenize(paragraph).toList
  }

  def whitespace(paragraph: String): List[String] = {
    whitespaceTokenizer.tokenize(paragraph).toList
  }

  def maxEntropy(paragraph: String): List[String] = {
    val maxEntropyTokenizer = new TokenizerME(MaxEntropyModel.instance())
    maxEntropyTokenizer.tokenize(paragraph).toList
  }

  def tokenize(paragraph: String, algorithm: Tokenizer): List[String] = algorithm match {
    case SIMPLE => simple(paragraph)
    case WHITESPACE => whitespace(paragraph)
    case MAX_ENTROPY => maxEntropy(paragraph)
  }
}

object MaxEntropyModel {
  private final val logger: Logger = LoggerFactory.getLogger(getClass)
  final val MODEL_PATH = "/nlp/models/tokenizer/en-token.bin"

  logger.info(s"Reading TokenizerModel model from: $MODEL_PATH")
  private final val modelFile = getClass.getResourceAsStream(MODEL_PATH)
  private final val model = new TokenizerModel(modelFile)
  logger.info(s"TokenizerModel model loaded from: $MODEL_PATH")

  def instance(): TokenizerModel = {
    model
  }
}
