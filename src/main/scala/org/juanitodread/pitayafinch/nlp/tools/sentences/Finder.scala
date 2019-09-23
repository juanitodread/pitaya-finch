package org.juanitodread.pitayafinch.nlp.tools.sentences

import java.io.InputStream

import opennlp.tools.sentdetect.{ SentenceDetectorME, SentenceModel }

import org.juanitodread.pitayafinch.model.nlp.sentences.FinderResult

class Finder(modelPath: String) {
  private final val modelFile: InputStream = getClass.getResourceAsStream(modelPath)
  require(modelFile != null, "Training model must exists")
  private final val sentenceModel: SentenceModel = new SentenceModel(modelFile)
  private final val sentenceDetector: SentenceDetectorME = new SentenceDetectorME(sentenceModel)
  modelFile.close()

  def find(text: String): List[FinderResult] = {
    val sentences: List[String] = sentenceDetector.sentDetect(text).toList
    val confidences: List[Double] = sentenceDetector.getSentenceProbabilities.toList
    (sentences zip confidences).map { result =>
      FinderResult(
        sentence = result._1,
        confidence = Finder.round(result._2))
    }
  }
}

object Finder {
  val trainingModel: String = "/nlp/models/sentences/en-sent.bin"
  private val finder: Finder = new Finder(trainingModel)

  def apply(text: String): List[FinderResult] = {
    finder.find(text)
  }

  def round(confidence: Double, precision: Int = 3): Double = {
    require(precision > 0 && precision < 6)
    val decimals = Math.pow(10, precision)
    Math.rint(confidence * decimals) / decimals
  }
}
