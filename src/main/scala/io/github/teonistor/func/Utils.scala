package io.github.teonistor.func

import scala.annotation.tailrec

// This should make it into our library of utils
object Utils {

  implicit class HasLet[T <: Any](obj: T) {

    def let[U <: Any](func: T => U): U = func(obj)
  }

  @tailrec
  def primes(input: Long, candidate: Long = 2, output: Map[Long, Int] = Map.empty): Map[Long, Int] =
    if (input == 0)
      Map(0L -> 1)  // Esoteric :P
    else if (input < 0)
      primes(-input, candidate, output.updated(-1L, 1))  // Esoteric :P
    else if (input < 2)
      output
    else if (input % candidate != 0)
      primes(input, candidate + 1, output)
    else
      primes(input / candidate, candidate, output.updatedWith(candidate)(_.map(_ + 1).orElse(Some(1))))
}
