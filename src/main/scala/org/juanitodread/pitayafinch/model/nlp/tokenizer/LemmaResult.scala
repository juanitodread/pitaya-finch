package org.juanitodread.pitayafinch.model.nlp.tokenizer

case class Lemma(tag: String, description: String)
case class LemmaResult(originalWord: String, lemmas: List[Lemma])
