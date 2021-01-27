package io.github.teonistor.adventofcode

object _25 {

  private def seek(seed: Int, subject: Int, cap: Int, loopCount: Int, key1: Int, key2: Int, result1: Option[Int], result2: Option[Int]): (Int, Int) =
    if (result1.isDefined && result2.isDefined)
      (result1.get, result2.get)
    else
      seek(transform(seed, subject, cap), subject, cap,
        loopCount + 1, key1, key2,
        result1.orElse(if (seed == key1) Option(loopCount) else Option.empty),
        result2.orElse(if (seed == key2) Option(loopCount) else Option.empty))

  private def transform(seed: Int, subject: Int, cap: Int) =
    math.toIntExact((seed.toLong * subject.toLong) % cap.toLong)

  def _1(input: String): Int = {
    val keys = input.split('\n').map(_.toInt)
    val (result1, result2) = seek(1, 7, 20201227, 0, keys(0), keys(1), Option.empty, Option.empty)

    val encryptionKey = Set(
        (keys(0), result2),
        (keys(1), result1))
      .map(keyAndLoopCount => LazyList.iterate(1)(transform(_, keyAndLoopCount._1, 20201227)).drop(keyAndLoopCount._2).head)

    if (encryptionKey.size != 1)
      throw new RuntimeException(s"Postcondition failure: Both devices should arrive at the same key but ${encryptionKey.size} found!")

    encryptionKey.head
  }
}
