package io.github.teonistor.kata

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class FibTest extends AnyFunSuite with BeforeAndAfterAll {
  private val sequence = List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
  private val repetitions = List(10, 100, 1000, 10_000, 20_000/*, 200_000, 1000_000*/)
  private val variants: Map[String, Int => Long] = Map(
//    ("Naive", Fib.fibNaive), // Don't enable this unless you have time to waste
    ("Inner func", Fib.fibWithInnerFunc),
    ("Cunning", Fib.fibCunning(_)),
    ("Condensed", Fib.fibCondensed(_)),
    ("Misfeatured", Fib.fibMisfeatured(_)), // Results in stack overflow somewhere between 20_000 and 50_000
    ("More cunning", Fib.fibMoreCunning(_)))

  /* Observations:

   * The naive approach takes too long for my patience as low as 50 (obviously).
   * There is no noticeable time difference between the next 3 approaches (with inner function, "cunning" and "condensed").
   *
   * However, the "condensed" version technically overshoots by calculating one more element than is needed. In this case
   * the calculation is just one addition, but theoretically, if it were a lot more complicated, one may attempt to avoid
   * this by using Scala's "pass by name" syntactic sugar, which the "misfeatured" version does naively; this brings its
   * demise because each subsequent element nests another lambda, which slows it down and ultimately kills it with a stack
   * overflow somewhere between 20_000 and 50_000 (on my machine).
   * Finally, the "more cunning" version fixes the "pass by name" problem by combining it with "lazy val" (a common idiom)
   * but the lambda overhead, incurred at every step, is still worse than one extra addition at the end.
   *
   * To sum up: the "condensed" version is the best, with extra points for brevity.
   */

  private val benchmarks = variants.keys.flatMap(variant => {
    sequence.indices.foreach(i => {

      // Create test for the output
      val expected = sequence(i)
      test(s"$variant: Fib($i) = $expected") {
        assert(variants(variant)(i) == expected)
      }
    })

    // Create benchmark
    repetitions.map(repetition => ((variant, repetition), () => {
      val start = System.currentTimeMillis()
      variants(variant)(repetition)
      System.currentTimeMillis() - start
    }))
  }).toMap

  override def afterAll(): Unit = {
    println("Running benchmarks...")
    val benchmarksRun = benchmarks.map(kv => (kv._1, kv._2()))

    // TODO Format
    val pad = variants.keys.map(_.length).max + 1
    println(repetitions.mkString("".padTo(pad, ' '), " ", ""))
    variants.keys.foreach(variant =>
      println(repetitions.map(repetition => benchmarksRun((variant, repetition)))
        .mkString(variant.padTo(pad, ' '), " ", "")))
  }
}
