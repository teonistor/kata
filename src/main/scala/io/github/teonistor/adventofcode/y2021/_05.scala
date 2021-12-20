package io.github.teonistor.adventofcode.y2021

object _05 {
  private type Point = (Int, Int)
  private type Line = (Int, Int, Int, Int)
  private val coords = "(\\d+),(\\d+) -> (\\d+),(\\d+)".r

  def _1(input: String): Long =
    solve(input, _.isRectilinear)

  def _2(input: String): Long =
    solve(input, _ => true)

  private def solve(input: String, filter: Line => Boolean) =
    input.split("\n").to(LazyList)
      .map { case coords(x1, y1, x2, y2) => (x1.toInt, y1.toInt, x2.toInt, y2.toInt) }
      .filter(filter)
      .flatMap(_.points)
      .groupMapReduce(identity)(_ => 1)(_+_)
      .count(_._2 > 1)

  private implicit class TwoPointsMakeMorePoints(point: Point) {
    private[_05] def to(other: Point) =

      if (point._1 == other._1)
        if (point._2 > other._2)
          (other._2 to point._2).map((point._1, _))
        else
          (point._2 to other._2).map((point._1, _))

      else if (point._2 == other._2)
        if (point._1 > other._1)
          (other._1 to point._1).map((_, point._2))
        else
          (point._1 to other._1).map((_, point._2))

      else {
        if (point._1 > other._1 == point._2 > other._2)
          (Math.min(point._1, other._1) to Math.max(point._1, other._1)).zip(
            Math.min(point._2, other._2) to Math.max(point._2, other._2))
        else
          (Math.min(point._1, other._1) to Math.max(point._1, other._1)).reverse.zip(
            Math.min(point._2, other._2) to Math.max(point._2, other._2))
      }
  }

  private implicit class LineMethods(line: Line) {
    private[_05] def isRectilinear =
      line._1 == line._3 || line._2 == line._4

    private[_05] def points =
      (line._1, line._2) to (line._3, line._4)
  }
}
