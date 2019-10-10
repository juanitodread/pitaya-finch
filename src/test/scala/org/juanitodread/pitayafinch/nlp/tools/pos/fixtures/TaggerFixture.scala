package org.juanitodread.pitayafinch.nlp.tools.pos.fixtures

import org.juanitodread.pitayafinch.model.nlp.pos.TagsResult
import org.juanitodread.pitayafinch.nlp.tools.models.pos.PerceptronModel
import org.juanitodread.pitayafinch.nlp.tools.pos.Tagger

object TaggerFixture {
  private final val tagger = new Tagger(PerceptronModel())

  def apply(sentence: String, withChunk: Boolean = false): TagsResult = {
    tagger.tag(sentence, withChunk)
  }
}
