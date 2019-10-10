package org.juanitodread.pitayafinch.model.nlp.pos

import org.juanitodread.pitayafinch.model.nlp.chunking.Chunk

case class Tag(token: String, tag: String, description: String)
case class TagsResult(tags: List[Tag], chunks: Option[List[Chunk]])

case class TagsRequest(text: String, chunk: Boolean)
case class TagsResponse(text: String, result: TagsResult)
