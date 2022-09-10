package io.github.teonistor.kata

import scala.annotation.tailrec
import scala.collection.mutable

class Euler351(n: Int) {

  /* Take a 1/6 sector of the hexagon and stretch it to fit an orthogonal grid, aligning the radial lines with the axes.
   * Thus, we have a region of points of integer coordinates such that:
   *   ⎧ x + y <= n
   *   ⎨ x > 0        (we exclude the vertical axis as it will be covered by the next sector over)
   *   ⎩ y >= 0
   *
   * We divide this region into 4 zones (which are referred to in code below) like so:
   *   A:= where x < y
   *   B:= where x = y
   *   C:= where x > y > 0
   *   D:= where y = 0
   *
   * In zones A, B, C, the points of interest are those whose coordinates are _not_ coprimes; in zone D this concept is
   * meaningless, but those points are easily counted, being on a horizontal line. Zone B is also easy, being a diagonal.
   * Zones A and C are mirrors of each other and we will arbitrarily focus on C in the algorithm, which hinges on the
   * observation that, for a given point, the highest common denominator of the coordinates tells un how many points
   * lie on the line between the origin (exclusive) and it (inclusive), which is one more than the points of interest.
   * We precompute the primes up to n/2 to aid the hcd calculation, and ... TODO
   */

  lazy private[kata] val primes = computePrimes()

  private def computePrimes(): List[Int] = {
    val set = 2 to n/2 to mutable.TreeSet

    @tailrec
    def sieve(current: Int, seed:Int): Unit = {
      if (current > n/2)
        return
      set.remove(current)
      sieve(current + seed, seed)
    }

    var logSentinel = 0
    def logIf(i: Int): Unit =
      if (i > logSentinel) {
        logSentinel = i
        println(s"Sieve :: $i")
      }

    def launchSieve(seed: Int): Unit = {
      logIf(seed / 1000)
      sieve(seed << 1, seed)
    }

    set.iterator.foreach(launchSieve)
    set.toList
  }

  def computePointsOfInterest(): Long = {
    (computeZonesAC() * 2 +
      computeZoneB() +
      computeZoneD()) * 6
  }

  private[kata] final def computeZonesAC() = {

    @tailrec
    def step1(intercept:Int = n-1, x:Int = n-1, y:Int = 1,
              ratiosEncountered:Set[(Int,Int)] = Set.empty,
              accumulator:Long = 0): Long = {

      if (x < 2)
        accumulator
      else if (x <= y) {
        if (intercept % 1000 == 0)
          println(s"intercept = $intercept")
        step1(intercept - 1, intercept - 1, 1, ratiosEncountered, accumulator)
      }
      else {

        // Shortcut for an easy case on the computationally unlucky side
        if (x == y + 1)
          step1(intercept, x-1, y+1, ratiosEncountered + ((x,y)), accumulator)
        else {
//          println(s"x = $x, y = $y")

          val cd = hcd(x, y)
          val ratio = (x / cd, y / cd)
          if (ratiosEncountered contains ratio)
            step1(intercept, x-1, y+1, ratiosEncountered, accumulator)
          else
            step1(intercept, x-1, y+1, ratiosEncountered + ratio, accumulator+cd-1)
        }
      }
    }

    step1()
  }

  private[kata] final def computeZoneB() =
    n/2 - 1

  private[kata] final def computeZoneD() =
    n - 1

  def restOfPoints(level: Int):Long = {
    if (level >= n - 2)
      return 0
    val line = n / level - 1
    // My initial mind said `level to level * 2 - 2` but that overshot
    // With `until` 5 and 10 are fine, but 1000 overshoots
    // There's a more systemic overcounting problem
    val linesInBetween = (level until level * 2 - 2).map(n / _).sum
    val restOfIt = restOfPoints(level * 2)
    line + 2 * restOfIt + linesInBetween
  }

  private[kata] final def hcd(a: Int, b: Int): Int = {

    @tailrec
    def hcdInner(remainingPrimes:List[Int] = primes, a:Int = a, b:Int = b, accumulator:Int = 1): Int = {
//      println("hcdInner()")
      if (remainingPrimes.isEmpty)
        accumulator
      else {
        val prime = remainingPrimes.head
        if (prime > a || prime > b)
          accumulator
        else if (a % prime == 0 && b % prime == 0)
          hcdInner(remainingPrimes, a/prime, b/prime, accumulator*prime)
        else
          hcdInner(remainingPrimes.tail, a, b, accumulator)
      }
    }

//    val limit = Math.sqrt(Math.max(a, b))
    hcdInner()
  }
}
