package io.github.teonistor.adventofcode

object _08 {

  def _1(input: String): Int = {
    val splitInput = input.split("\n")

    val accRx = "acc (.+)".r
    val jmpRx = "jmp (.+)".r
    val nopRx = "nop .+".r

    def loop(acc: Int, index: Int, visited: Set[Int]): Int = {
      if (visited contains index) {
        return acc
      }

      splitInput(index) match {
        case accRx(delta) => loop(acc + delta.toInt, index + 1, visited + index)
        case jmpRx(delta) => loop(acc, index + delta.toInt, visited + index)
        case nopRx() => loop(acc, index + 1, visited + index)
      }
    }
    loop(0, 0, Set.empty)
  }

  def _2(input: String): Int = {
    val splitInput = input.split("\n")

    val accRx = "acc (.+)".r
    val jmpRx = "jmp (.+)".r
    val nopRx = "nop (.+)".r
    // TODO Mutable!
    var result = -1

    def loopAcc(acc: Int, index: Int, visited: Set[Int], delta: String, flipped: Int) = {
      loop(acc + delta.toInt, index + 1, visited + index, flipped)
    }

    def loopJmp(acc: Int, index: Int, visited: Set[Int], delta: String, flipped: Int) = {
      loop(acc, index + delta.toInt, visited + index, flipped)
    }

    def loopNop(acc: Int, index: Int, visited: Set[Int], flipped: Int) = {
      loop(acc, index + 1, visited + index, flipped)
    }

    def loop(acc: Int, index: Int, visited: Set[Int], flipped: Int): Boolean = {
      if (index == splitInput.length) {
        result = acc
        return true
      }
      if (visited contains index) {
        return false
      }

      splitInput(index) match {
        case accRx(delta) => loopAcc(acc, index, visited, delta, flipped)
        case nopRx(delta) => -1 == flipped && loopJmp(acc, index, visited, delta, index) ||
          (if (flipped == index) loopJmp(acc, index, visited, delta, index) else loopNop(acc, index, visited, flipped))
        case jmpRx(delta) => -1 == flipped && loopNop(acc, index, visited, index) ||
          (if (flipped == index) loopNop(acc, index, visited, index) else loopJmp(acc, index, visited, delta, flipped))
      }
    }
    loop(0, 0, Set.empty, -1)
    result
  }
}
