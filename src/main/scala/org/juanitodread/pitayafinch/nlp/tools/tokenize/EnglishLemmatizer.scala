package org.juanitodread.pitayafinch.nlp.tools.tokenize

import java.io.InputStream

import opennlp.tools.lemmatizer.DictionaryLemmatizer

import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ Lemma, LemmaResult }
import org.juanitodread.pitayafinch.nlp.tools.pos.Tags

class EnglishLemmatizer(dictionaryPath: String) extends Tags {
  private final val dictionaryFile: InputStream = getClass.getResourceAsStream(dictionaryPath)
  require(dictionaryFile != null, "Dictionary file must exists")
  private final val englishLemmas = new DictionaryLemmatizer(dictionaryFile)
  dictionaryFile.close()

  def lemmatize(token: String): LemmaResult = {
    val lemmas: List[Lemma] = tagsMap.keys.map { tag =>
      (
        englishLemmas.lemmatize(
        List(token).toArray,
        List(tag).toArray).mkString(""),
        Lemma(tag, tagsMap(tag)))
    }.filter(result => result._1 != "O")
      .map(_._2).toList
    LemmaResult(token, lemmas)
  }

  def lemmatize(tokens: List[String]): List[LemmaResult] = {
    tokens.map(this.lemmatize)
  }
}

object EnglishLemmatizer {
  val dictionary = "/nlp/models/tokenizer/lemmatizer/en-lemmatizer.dict"
  private val lemmatizer = new EnglishLemmatizer(dictionary)

  def apply(tokens: List[String]): List[LemmaResult] = {
    lemmatizer.lemmatize(tokens)
  }
}
