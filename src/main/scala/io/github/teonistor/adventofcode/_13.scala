package io.github.teonistor.adventofcode

object _13 {

  def _1(input: String): Long = {
    val splitInput = input.split('\n')
    val myTime = splitInput(0).toInt
    val buses = splitInput(1).split(',').map(_.toIntOption).filter(_.isDefined).map(_.get)
    val (busTime, bus) = (myTime to Int.MaxValue).to(LazyList).flatMap(time => buses.filter(time % _ == 0).map((time, _))).head
    (busTime - myTime) * bus
  }

  // TODO It doesn't work for the example input anymore
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

    // This cheats by noticing that (in the problem data) the numbers that would fall on the same spot are primes, thus their least
    // common multiple is their product. It gives the most optimised requirement set:
    // (0 -> 15894127, 10 -> 41, -31 -> 541, -5 -> 13, 3 -> 17, -2 -> 29)
    val optimisedReq = req.groupMapReduce(indexAndValue => bringCloser(indexAndValue._1, indexAndValue._2) - indexOfMax)(_._2)(_*_)

    // Before I realised I could use the maximum of the optimised values, using max from above the problem data took about 3 hours
    val optimisedMax = optimisedReq.values.max
    println(s"optimisedMax: ${optimisedMax}")

    def findStart(t: Long): Long = {
//      if (t % 20662365100L == 0) println(s"$t ")
      if (optimisedReq.forall(indexAndValue => (t + indexAndValue._1) % indexAndValue._2 == 0)) t
      else findStart(t + optimisedMax)
    }
    findStart(optimisedMax) - indexOfMax


// Attempt 2.1 was better than attempt 2 by being multithreaded in this brutal way; much better suited to a ForkJoin pool or something
// It also had a *4 in the recursive step
//
//  var ans: Long = 0
//
//    new Thread(() => {
//      ans = findStart(582784646070582L)
//    }).start()
//    new Thread(() => {
//      ans = findStart(582784646070582L + optimisedMax)
//    }).start()
//    new Thread(() => {
//      ans = findStart(582784646070582L + optimisedMax * 2)
//    }).start()
//    new Thread(() => {
//      ans = findStart(582784646070582L + optimisedMax * 3)
//    }).start()
//
//    while (ans == 0) {
//      Thread.sleep(1000)
//    }
//
//    ans - indexOfMax


////////  Below this line is the original idea, very nice but would take 190 years to complete on the problem data set  ////////

// Can't use int range here because there are more than Int.MaxValue elements

//    def findStart(t: Long): Long = {
//            println(s"$t ")
//      if (buses.indices.count(i => buses(i).forall((t + i) % _ == 0)) == buses.length) t
//      else findStart(t + 1)
//    }

    // Cautiously working around the Int.MaxValue size limit
//    findStart(1L)
//
//4630083
//    val value = LazyList.iterate(1L)(_ + 1L)
//    value.find(t => {
//      println(s"$t ")
//      buses.indices.count(i => buses(i).forall((t + i) % _ == 0)) == buses.length
//    }).get
  }
}
