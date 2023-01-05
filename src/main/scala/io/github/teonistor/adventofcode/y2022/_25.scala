package io.github.teonistor.adventofcode.y2022

import scala.annotation.tailrec

object _25 {

  def _1(input: String): String =
    dtos(input.split("\n")
      .map(stod)
      .sum)

  private def stod(s: String) =
    s.reverse.foldLeft((0L, 1L))((acc, char) => {
      val num = char match {
        case '2' => 2
        case '1' => 1
        case '0' => 0
        case '-' => -1
        case '=' => -2
      }
      (acc._1 + num * acc._2, acc._2 * 5)
    })
      ._1

  private def dtos(d: Long) = {

    @tailrec
    def recu(n: Long, s: List[Char]): String =
      if (n < 1)
        s.mkString
      else
        n % 5 match {
          case 3 => recu(n / 5 + 1, '=' +: s)
          case 4 => recu(n / 5 + 1, '-' +: s)
          case _ => recu(n / 5, (n % 5).toString()(0) +: s)
        }

    recu(d, List.empty)
  }
}
