package org.juanitodread.pitayafinch.nlp.tools.tokenize

abstract class Pipeline[-I, +O] {
  val chain: String

  def produce: I => O

  def ->[N](next: Pipeline[_ >: O, N]): Pipeline[I, N] = {
    val prevChain = this.chain
    val prevFunction = this.produce

    new Pipeline[I, N] {
      val chain: String = s"${prevChain} => ${next.chain}"

      def produce: I => N = {
        prevFunction.andThen(next.produce)
      }
    }
  }

  override def toString: String = this.chain
}

abstract class Source[+O] extends Pipeline[Unit, O]

class Prepare(token: String) extends Source[String] {
  override val chain: String = "Prepare"

  override def produce: Unit => String = {
    _ => this.token
  }
}

class LowerCase extends Pipeline[String, String] {
  override val chain: String = "LowerCase"

  override def produce: String => String = {
    token: String => token.toLowerCase
  }
}

class UpperCase extends Pipeline[String, String] {
  override val chain: String = "UpperCase"

  override def produce: String => String = {
    token: String => token.toUpperCase
  }
}

class Reverse extends Pipeline[String, String] {
  override val chain: String = "Reverse"

  override def produce: String => String = {
    token: String => token.reverse
  }
}
