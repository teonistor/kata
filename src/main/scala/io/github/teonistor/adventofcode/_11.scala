package io.github.teonistor.adventofcode

object _11 {

  private def breakDown(input: String) =
    input.split("\n").map(_.to(List)).to(List)

  def _1(input: String): Int = {
    def isOccupied(seats: Seq[Seq[Char]], row: Int, col: Int) =
      row >= 0 && col >= 0 && row < seats.length && col < seats(row).length && '#' == seats(row)(col)

    def countOccupiedAround(seats: Seq[Seq[Char]], row: Int, col: Int) =
      LazyList((row - 1, col - 1), (row - 1, col), (row - 1, col + 1), (row, col - 1), (row, col + 1), (row + 1, col - 1), (row + 1, col), (row + 1, col + 1))
        .count(rowCol => isOccupied(seats, rowCol._1, rowCol._2))

    def loop(seats: Seq[Seq[Char]], prevSeats: Seq[Seq[Char]]): Seq[Seq[Char]] = {
      if (seats.equals(prevSeats)) {
        return seats
      }

      val newSeats =
        seats.indices.map(row =>
          seats(row).indices.map(col =>
            seats(row)(col) match {
              case '.' => '.'
              case 'L' => if (countOccupiedAround(seats, row, col) == 0) '#' else 'L'
              case '#' => if (countOccupiedAround(seats, row, col) > 3) 'L' else '#'
            }
          )
        )

      loop(newSeats, seats)
    }

    loop(breakDown(input), null)
      .map(_.count(_ == '#'))
      .sum
  }

  def _2(input: String): Int = {
    def isOccupied(seats: Seq[Seq[Char]], rowOrigin: Int, colOrigin: Int, rowDelta: Int, colDelta: Int): Boolean = {
      val row = rowOrigin + rowDelta
      val col = colOrigin + colDelta
      if (!(row >= 0 && col >= 0 && row < seats.length) || !(col < seats(row).length)) {
        false
      } else {
        seats(row)(col) match {
          case '.' => isOccupied(seats, row, col, rowDelta, colDelta)
          case 'L' => false
          case '#' => true
        }
      }
    }

    def countOccupiedAround(seats: Seq[Seq[Char]], row: Int, col: Int) =
      LazyList((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
        .count(deltas => isOccupied(seats, row, col, deltas._1, deltas._2))

    def loop(seats: Seq[Seq[Char]],prevSeats: Seq[Seq[Char]]): Seq[Seq[Char]] ={
      if (seats.equals(prevSeats)) {
        return seats
      }

      val newSeats =
        seats.indices.map(row =>
          seats(row).indices.map(col =>
            seats(row)(col) match {
              case '.' => '.'
              case 'L' => if (countOccupiedAround(seats, row, col) == 0) '#' else 'L'
              case '#' => if (countOccupiedAround(seats, row, col) > 4) 'L' else '#'
            }
          )
        )

      loop(newSeats, seats)
    }

    loop(breakDown(input), null)
      .map(_.count(_ == '#'))
      .sum
  }
}
