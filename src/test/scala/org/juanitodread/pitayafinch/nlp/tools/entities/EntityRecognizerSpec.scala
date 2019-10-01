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
      case Entity(entity, model, confidence) =>
        entity shouldEqual "ALPAC"
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

  "An EntityRecognizer object" should "be able to evaluate DateEntityModel" in {
    val entity = EntityRecognizer("This is a date: Nov 12 1993").head
    assert(entity.entity === "Nov 12 1993")
    assert(entity.model === "Date")
  }

  it should "be able to evaluate LocationEntityModel" in {
    val entity = EntityRecognizer("This is a location: California").head
    assert(entity.entity === "California")
    assert(entity.model === "Location")
  }

  it should "be able to evaluate MoneyEntityModel" in {
    // In Money 2 entities are returned always. (First one with '$' only)
    val entity = EntityRecognizer("This is currency money: $45.50").tail.head
    assert(entity.entity === "45.50")
    assert(entity.model === "Money")
  }

  it should "be able to evaluate OrganizationEntityModel" in {
    val entity = EntityRecognizer("This is an organization: IBM").head
    assert(entity.entity === "IBM")
    assert(entity.model === "Organization")
  }

  it should "be able to evaluate PersonEntityModel" in {
    val entity = EntityRecognizer("This is a person: Julian Casablancas").head
    assert(entity.entity === "Julian Casablancas")
    assert(entity.model === "Person")
  }
}
