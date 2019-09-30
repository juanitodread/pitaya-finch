package org.juanitodread.pitayafinch.nlp.tools.entities

import org.juanitodread.pitayafinch.UnitSpec
import org.juanitodread.pitayafinch.model.nlp.entities.Entity
import org.juanitodread.pitayafinch.nlp.tools.entities.models.{ OrganizationEntityModel, PersonEntityModel }

class EntityRecognizerSpec extends UnitSpec {
  val sentence = "However, real progress was much slower, and after the ALPAC report in 1966, which found " +
    "that ten-year-long research had failed to fulfill the expectations, " +
    "funding for machine translation was dramatically reduced."

  "An EntityRecognizer" should "be of type Organization and recognize an Organization in a sentence" in {
    val recognizer = new EntityRecognizer(OrganizationEntityModel())
    val entities = recognizer.find(sentence)

    assert(entities.length === 1)
    inside(entities.head) {
      case Entity(name, model, confidence) =>
        name shouldEqual "ALPAC"
        model shouldEqual "Organization"
        confidence should be >= 0.0
        confidence should be <= 1.0
    }
  }

  it should "be of type Person and not recognize a Person in a sentence" in {
    val recognizer = new EntityRecognizer(PersonEntityModel())
    val entities = recognizer.find(sentence)
    assert(entities.isEmpty === true)
  }
}
