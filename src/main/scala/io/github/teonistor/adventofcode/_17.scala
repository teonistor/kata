package io.github.teonistor.adventofcode

object _17 {

  def neighbours3D(coord: (Int, Int, Int)): LazyList[(Int, Int, Int)] = LazyList(
    (coord._1 - 1, coord._2 - 1, coord._3 - 1),
    (coord._1 - 1, coord._2 - 1, coord._3    ),
    (coord._1 - 1, coord._2 - 1, coord._3 + 1),
    (coord._1 - 1, coord._2,     coord._3 - 1),
    (coord._1 - 1, coord._2,     coord._3    ),
    (coord._1 - 1, coord._2,     coord._3 + 1),
    (coord._1 - 1, coord._2 + 1, coord._3 - 1),
    (coord._1 - 1, coord._2 + 1, coord._3    ),
    (coord._1 - 1, coord._2 + 1, coord._3 + 1),

    (coord._1,     coord._2 - 1, coord._3 - 1),
    (coord._1,     coord._2 - 1, coord._3    ),
    (coord._1,     coord._2 - 1, coord._3 + 1),
    (coord._1,     coord._2,     coord._3 - 1),
    (coord._1,     coord._2,     coord._3 + 1),
    (coord._1,     coord._2 + 1, coord._3 - 1),
    (coord._1,     coord._2 + 1, coord._3    ),
    (coord._1,     coord._2 + 1, coord._3 + 1),

    (coord._1 + 1, coord._2 - 1, coord._3 - 1),
    (coord._1 + 1, coord._2 - 1, coord._3    ),
    (coord._1 + 1, coord._2 - 1, coord._3 + 1),
    (coord._1 + 1, coord._2,     coord._3 - 1),
    (coord._1 + 1, coord._2,     coord._3    ),
    (coord._1 + 1, coord._2,     coord._3 + 1),
    (coord._1 + 1, coord._2 + 1, coord._3 - 1),
    (coord._1 + 1, coord._2 + 1, coord._3    ),
    (coord._1 + 1, coord._2 + 1, coord._3 + 1))

  def neighbours4D(coord: (Int, Int, Int, Int)): LazyList[(Int, Int, Int, Int)] = LazyList(
    (coord._1 - 1, coord._2 - 1, coord._3 - 1, coord._4 - 1),
    (coord._1 - 1, coord._2 - 1, coord._3    , coord._4 - 1),
    (coord._1 - 1, coord._2 - 1, coord._3 + 1, coord._4 - 1),
    (coord._1 - 1, coord._2,     coord._3 - 1, coord._4 - 1),
    (coord._1 - 1, coord._2,     coord._3    , coord._4 - 1),
    (coord._1 - 1, coord._2,     coord._3 + 1, coord._4 - 1),
    (coord._1 - 1, coord._2 + 1, coord._3 - 1, coord._4 - 1),
    (coord._1 - 1, coord._2 + 1, coord._3    , coord._4 - 1),
    (coord._1 - 1, coord._2 + 1, coord._3 + 1, coord._4 - 1),
    (coord._1,     coord._2 - 1, coord._3 - 1, coord._4 - 1),
    (coord._1,     coord._2 - 1, coord._3    , coord._4 - 1),
    (coord._1,     coord._2 - 1, coord._3 + 1, coord._4 - 1),
    (coord._1,     coord._2,     coord._3 - 1, coord._4 - 1),
    (coord._1,     coord._2,     coord._3    , coord._4 - 1),
    (coord._1,     coord._2,     coord._3 + 1, coord._4 - 1),
    (coord._1,     coord._2 + 1, coord._3 - 1, coord._4 - 1),
    (coord._1,     coord._2 + 1, coord._3    , coord._4 - 1),
    (coord._1,     coord._2 + 1, coord._3 + 1, coord._4 - 1),
    (coord._1 + 1, coord._2 - 1, coord._3 - 1, coord._4 - 1),
    (coord._1 + 1, coord._2 - 1, coord._3    , coord._4 - 1),
    (coord._1 + 1, coord._2 - 1, coord._3 + 1, coord._4 - 1),
    (coord._1 + 1, coord._2,     coord._3 - 1, coord._4 - 1),
    (coord._1 + 1, coord._2,     coord._3    , coord._4 - 1),
    (coord._1 + 1, coord._2,     coord._3 + 1, coord._4 - 1),
    (coord._1 + 1, coord._2 + 1, coord._3 - 1, coord._4 - 1),
    (coord._1 + 1, coord._2 + 1, coord._3    , coord._4 - 1),
    (coord._1 + 1, coord._2 + 1, coord._3 + 1, coord._4 - 1),

    (coord._1 - 1, coord._2 - 1, coord._3 - 1, coord._4),
    (coord._1 - 1, coord._2 - 1, coord._3    , coord._4),
    (coord._1 - 1, coord._2 - 1, coord._3 + 1, coord._4),
    (coord._1 - 1, coord._2,     coord._3 - 1, coord._4),
    (coord._1 - 1, coord._2,     coord._3    , coord._4),
    (coord._1 - 1, coord._2,     coord._3 + 1, coord._4),
    (coord._1 - 1, coord._2 + 1, coord._3 - 1, coord._4),
    (coord._1 - 1, coord._2 + 1, coord._3    , coord._4),
    (coord._1 - 1, coord._2 + 1, coord._3 + 1, coord._4),
    (coord._1,     coord._2 - 1, coord._3 - 1, coord._4),
    (coord._1,     coord._2 - 1, coord._3    , coord._4),
    (coord._1,     coord._2 - 1, coord._3 + 1, coord._4),
    (coord._1,     coord._2,     coord._3 - 1, coord._4),
    (coord._1,     coord._2,     coord._3 + 1, coord._4),
    (coord._1,     coord._2 + 1, coord._3 - 1, coord._4),
    (coord._1,     coord._2 + 1, coord._3    , coord._4),
    (coord._1,     coord._2 + 1, coord._3 + 1, coord._4),
    (coord._1 + 1, coord._2 - 1, coord._3 - 1, coord._4),
    (coord._1 + 1, coord._2 - 1, coord._3    , coord._4),
    (coord._1 + 1, coord._2 - 1, coord._3 + 1, coord._4),
    (coord._1 + 1, coord._2,     coord._3 - 1, coord._4),
    (coord._1 + 1, coord._2,     coord._3    , coord._4),
    (coord._1 + 1, coord._2,     coord._3 + 1, coord._4),
    (coord._1 + 1, coord._2 + 1, coord._3 - 1, coord._4),
    (coord._1 + 1, coord._2 + 1, coord._3    , coord._4),
    (coord._1 + 1, coord._2 + 1, coord._3 + 1, coord._4),

    (coord._1 - 1, coord._2 - 1, coord._3 - 1, coord._4 + 1),
    (coord._1 - 1, coord._2 - 1, coord._3    , coord._4 + 1),
    (coord._1 - 1, coord._2 - 1, coord._3 + 1, coord._4 + 1),
    (coord._1 - 1, coord._2,     coord._3 - 1, coord._4 + 1),
    (coord._1 - 1, coord._2,     coord._3    , coord._4 + 1),
    (coord._1 - 1, coord._2,     coord._3 + 1, coord._4 + 1),
    (coord._1 - 1, coord._2 + 1, coord._3 - 1, coord._4 + 1),
    (coord._1 - 1, coord._2 + 1, coord._3    , coord._4 + 1),
    (coord._1 - 1, coord._2 + 1, coord._3 + 1, coord._4 + 1),
    (coord._1,     coord._2 - 1, coord._3 - 1, coord._4 + 1),
    (coord._1,     coord._2 - 1, coord._3    , coord._4 + 1),
    (coord._1,     coord._2 - 1, coord._3 + 1, coord._4 + 1),
    (coord._1,     coord._2,     coord._3 - 1, coord._4 + 1),
    (coord._1,     coord._2,     coord._3    , coord._4 + 1),
    (coord._1,     coord._2,     coord._3 + 1, coord._4 + 1),
    (coord._1,     coord._2 + 1, coord._3 - 1, coord._4 + 1),
    (coord._1,     coord._2 + 1, coord._3    , coord._4 + 1),
    (coord._1,     coord._2 + 1, coord._3 + 1, coord._4 + 1),
    (coord._1 + 1, coord._2 - 1, coord._3 - 1, coord._4 + 1),
    (coord._1 + 1, coord._2 - 1, coord._3    , coord._4 + 1),
    (coord._1 + 1, coord._2 - 1, coord._3 + 1, coord._4 + 1),
    (coord._1 + 1, coord._2,     coord._3 - 1, coord._4 + 1),
    (coord._1 + 1, coord._2,     coord._3    , coord._4 + 1),
    (coord._1 + 1, coord._2,     coord._3 + 1, coord._4 + 1),
    (coord._1 + 1, coord._2 + 1, coord._3 - 1, coord._4 + 1),
    (coord._1 + 1, coord._2 + 1, coord._3    , coord._4 + 1),
    (coord._1 + 1, coord._2 + 1, coord._3 + 1, coord._4 + 1))

  private def minMax3D(coords: Set[(Int,Int,Int)]) = {
    val is = coords.map(_._1)
    val js = coords.map(_._2)
    val ks = coords.map(_._3)
    (is.min, is.max, js.min, js.max, ks.min, ks.max)
  }

  private def minMax4D(coords: Set[(Int,Int,Int,Int)]) = {
    val is = coords.map(_._1)
    val js = coords.map(_._2)
    val ks = coords.map(_._3)
    val ls = coords.map(_._4)
    (is.min, is.max, js.min, js.max, ks.min, ks.max, ls.min, ls.max)
  }

  private def parseInput3D(input: String) = {
    val splitInput = input.split('\n')
    splitInput.indices.flatMap(i => splitInput(i).indices.filter(splitInput(i)(_) == '#').map((i, _, 0))).to(Set)
  }

  private def parseInput4D(input: String) = {
    val splitInput = input.split('\n')
    splitInput.indices.flatMap(i => splitInput(i).indices.filter(splitInput(i)(_) == '#').map((i, _, 0, 0))).to(Set)
  }

  private def willBeActive[T](activeCubes: Set[T], neighboursFunc: T => Seq[T])(coord: T) = {
    val activeNeighbours = neighboursFunc(coord).count(activeCubes.contains)
    activeNeighbours == 3 || activeNeighbours == 2 && (activeCubes contains coord)
  }

  private def evolve3D(activeCubes: Set[(Int, Int, Int)]) = {
    val (minI, maxI, minJ, maxJ, minK, maxK) = minMax3D(activeCubes)
    (minI - 1 to maxI + 1).flatMap(i =>
      (minJ - 1 to maxJ + 1).flatMap(j =>
        (minK - 1 to maxK + 1).map((i, j, _))))
      .filter(willBeActive(activeCubes, neighbours3D)).to(Set)
  }

  private def evolve4D(activeCubes: Set[(Int, Int, Int, Int)]) = {
    val (minI, maxI, minJ, maxJ, minK, maxK, minL, maxL) = minMax4D(activeCubes)
    (minI - 1 to maxI + 1).flatMap(i =>
      (minJ - 1 to maxJ + 1).flatMap(j =>
        (minK - 1 to maxK + 1).flatMap(k =>
          (minL - 1 to maxL + 1).map((i, j, k, _)))))
      .filter(willBeActive(activeCubes, neighbours4D)).to(Set)
  }

  private def evolveRec[T](evolveFunc: T => T, activeCubes: T, iterations: Int): T =
    if (iterations == 0)
      activeCubes
    else
      evolveRec(evolveFunc, evolveFunc(activeCubes), iterations - 1)

  def _1(input: String): Int = {
    evolveRec(evolve3D, parseInput3D(input), 6).size
  }

  def _2(input: String): Int = {
    evolveRec(evolve4D, parseInput4D(input), 6).size
  }
}
