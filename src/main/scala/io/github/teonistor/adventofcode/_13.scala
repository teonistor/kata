package io.github.teonistor.adventofcode

object _13 {

  def _1(input: String): Long = {
    val splitInput = input.split('\n')
    val myTime = splitInput(0).toInt
    val buses = splitInput(1).split(',').map(_.toIntOption).filter(_.isDefined).map(_.get)
    val (busTime, bus) = (myTime to Int.MaxValue).to(LazyList).flatMap(time => buses.filter(time % _ == 0).map((time, _))).head
    (busTime - myTime) * bus
  }

  def _2(input: String): Long = {
    val buses = input.split('\n')(1).split(',').map(_.toIntOption)
    val req = buses.indices.filter(buses(_).isDefined).map(i => (i, buses(i).get))
    val indexOfMax = req.maxBy(_._2)._1

    def bringCloser(index: Int, value: Int): Int = {
      if (index == indexOfMax) index
      else if (index < indexOfMax && indexOfMax - index > math.abs(index + value - indexOfMax)) bringCloser(index + value, value)
      else if (index > indexOfMax && index - indexOfMax > math.abs(index - value - indexOfMax)) bringCloser(index - value, value)
      else index
    }

    // This cheats by noticing that in the given inputs the numbers that would fall on the same spot are primes, thus their least
    // common multiple is their product. It gives the most optimised requirement set which for the problem data is:
    // (0 -> 15894127, 10 -> 41, -31 -> 541, -5 -> 13, 3 -> 17, -2 -> 29)
    val optimisedReq = req.groupMapReduce(indexAndValue => bringCloser(indexAndValue._1, indexAndValue._2) - indexOfMax)(_._2)(_*_)

    // Before I realised I could use the maximum of the optimised values, using max from above the problem data took about 3 hours
    val (indexOfOptimisedMax, optimisedMax) = optimisedReq.maxBy(_._2)

    // The necessity for this may seem ridiculous, but without it the example input breaks because the product of two non-max values is
    // greater than the max value. That's not the case with the problem data where the max value stays put after bringCloser()
    val veryOptimisedReq = optimisedReq.map(indexAndValue => (indexAndValue._1 - indexOfOptimisedMax, indexAndValue._2))

    def findStart(t: Long): Long = {
      if (veryOptimisedReq.forall(indexAndValue => (t + indexAndValue._1) % indexAndValue._2 == 0)) t
      else findStart(t + optimisedMax)
    }
    findStart(optimisedMax) - indexOfMax - indexOfOptimisedMax
  }
}
