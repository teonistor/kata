package io.github.teonistor.adventofcode


object _03 {

  def _1(input: String): Long = {
    val splitInput = input.split("\n")

    def navigate(row: Int, col: Int, right: Int, down: Int, encountered: Int): Long = {
      if (row == splitInput.length) {
        return encountered
      }
      return navigate(row + down, (col + right) % splitInput(row).length, right, down, if (splitInput(row)(col) == '#') encountered + 1 else encountered)
    }

    return navigate(0, 0, 3, 1, 0)
  }

  def _2(input: String): Long = {
    val splitInput = input.split("\n")

    def navigate(row: Int, col: Int, right: Int, down: Int, encountered: Int): Long = {
      if (row >= splitInput.length) {
        return encountered
      }
      navigate(row + down, (col + right) % splitInput(row).length, right, down, if (splitInput(row)(col) == '#') encountered + 1 else encountered)
    }

    val results = List(
      navigate(0, 0, 1, 1, 0),
      navigate(0, 0, 3, 1, 0),
      navigate(0, 0, 5, 1, 0),
      navigate(0, 0, 7, 1, 0),
      navigate(0, 0, 1, 2, 0))

    return results.product
  }
}
