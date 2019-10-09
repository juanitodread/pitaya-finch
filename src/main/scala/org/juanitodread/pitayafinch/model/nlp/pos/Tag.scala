package org.juanitodread.pitayafinch.model.nlp.pos

case class Tag(token: String, tag: String, description: String)

case class TagsRequest(text: String)
case class TagsResponse(text: String, tags: List[Tag])
