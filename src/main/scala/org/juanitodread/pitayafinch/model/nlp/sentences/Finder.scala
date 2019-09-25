package org.juanitodread.pitayafinch.model.nlp.sentences

case class FinderResult(sentence: String, confidence: Double)

case class FinderRequest(text: String)
case class FinderResponse(text: String, sentences: List[FinderResult])