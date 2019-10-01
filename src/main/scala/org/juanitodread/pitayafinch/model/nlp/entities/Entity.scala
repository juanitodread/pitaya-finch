package org.juanitodread.pitayafinch.model.nlp.entities

case class Entity(entity: String, model: String, confidence: Double)

case class EntitiesRequest(text: String)
case class EntitiesResponse(text: String, entities: List[Entity])
