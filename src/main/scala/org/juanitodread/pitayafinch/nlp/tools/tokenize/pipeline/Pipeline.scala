package org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline

import org.juanitodread.pitayafinch.nlp.tools.tokenize.pipeline.Tokenizer.Tokens

sealed abstract class Pipeline[-I, +O] {
  val chain: String

  def produce: I => O

  def ->[N](next: Pipeline[_ >: O, N]): Pipeline[I, N] = {
    val prevChain = this.chain
    val prevFunction = this.produce

    new Pipeline[I, N] {
      val chain: String = s"${prevChain} -> ${next.chain}"

      def produce: I => N = {
        prevFunction.andThen(next.produce)
      }
    }
  }

  override def toString: String = this.chain
}

abstract class Source[+O] extends Pipeline[Unit, O]
abstract class Step[I, O] extends Pipeline[I, O]
abstract class Result[I, O] extends Pipeline[I, O]

case object Empty extends Result[Tokens, Tokens] {
  override val chain: String = "Empty"

  override def produce: Tokens => Tokens = tokens => tokens
}

case class PipelineBuilder[R](
  source: Source[Tokens],
  steps: List[Step[Tokens, Tokens]],
  result: Result[Tokens, R] = Empty) {
  def build() = {
    val chain = steps.reduce {
      (prev: Pipeline[Tokens, Tokens], next: Pipeline[Tokens, Tokens]) =>
        prev -> next
    }
    source -> chain -> result
  }
}
