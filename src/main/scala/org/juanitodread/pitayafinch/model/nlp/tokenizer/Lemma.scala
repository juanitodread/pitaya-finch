package org.juanitodread.pitayafinch.model.nlp.tokenizer

case class Lemma(tag: String, description: String)
case class LemmaResult(original: String, lemmas: List[Lemma])

case class LemmaRequest(tokens: List[String])
case class LemmaResponse(tokens: List[String], result: List[LemmaResult])
