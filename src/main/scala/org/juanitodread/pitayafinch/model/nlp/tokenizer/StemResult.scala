package org.juanitodread.pitayafinch.model.nlp.tokenizer

case class StemResult(original: String, stem: String)

case class StemRequest(tokens: List[String])
case class StemResponse(tokens: List[String], result: List[StemResult])
