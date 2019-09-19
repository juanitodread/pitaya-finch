package org.juanitodread.pitayafinch.nlp.tools.tokenize

import java.io.InputStream

import opennlp.tools.lemmatizer.DictionaryLemmatizer
import org.juanitodread.pitayafinch.model.nlp.tokenizer.{ Lemma, LemmaResult }

class EnglishLemmatizer(dictionaryPath: String) {
  private final val tags: Map[String, String] = Map(
    "CC" -> "Coordinating conjunction",
    "CD" -> "Cardinal number",
    "DT" -> "Determiner",
    "EX" -> "Existential there",
    "FW" -> "Foreign word",
    "IN" -> "Preposition or subordinating conjunction",
    "JJ" -> "Adjective",
    "JJR" -> "Adjective, comparative",
    "JJS" -> "Adjective, superlative",
    "MD" -> "Modal",
    "NN" -> "Noun, singular or mass",
    "NNN" -> "Noun",
    "NNS" -> "Noun, plural",
    "PDT" -> "Predeterminer",
    "POS" -> "Possessive ending",
    "PRP" -> "Personal pronoun",
    "PRP$" -> "Possessive pronoun",
    "RB" -> "Adverb",
    "RBR" -> "Adverb, comparative",
    "RBS" -> "Adverb, superlative",
    "RP" -> "Particle",
    "TO" -> "to",
    "UH" -> "Interjection",
    "VB" -> "Verb, base form",
    "VBD" -> "Verb, past tense",
    "VBG" -> "Verb, gerund, or present participle",
    "VBN" -> "Verb, past participle",
    "VBP" -> "Verb, non-3rd person singular present",
    "VBZ" -> "Verb, 3rd person singular present",
    "WDT" -> "Wh-determiner",
    "WP" -> "Wh-pronoun",
    "WP$" -> "Possessive wh-pronoun",
    "WRB" -> "Wh-adverb")

  private final val dictionaryFile: InputStream = getClass.getResourceAsStream(dictionaryPath)
  require(dictionaryFile != null, "Dictionary file must exists")
  private final val englishLemmas = new DictionaryLemmatizer(dictionaryFile)
  dictionaryFile.close()

  def lemmatize(token: String): LemmaResult = {
    val lemmas: List[Lemma] = tags.keys
      .map { tag =>
        (englishLemmas.lemmatize(List(token).toArray, List(tag).toArray).mkString(""), Lemma(tag, tags(tag)))
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
