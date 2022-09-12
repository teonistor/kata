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

  private def computePrimes(): Array[Int] = {
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
      logIf(seed / 100)
      sieve(seed << 1, seed)
    }

    set.iterator.foreach(launchSieve)
    set.to(Array)
  }

  def computePointsOfInterest(): Long = {
    (computeZonesAC() * 2 +
      computeZoneB() +
      computeZoneD()) * 6
  }

  private[kata] final def computeZonesAC() = {
  /*  val contributions = Array.fill(primes.size)(0)

    @tailrec
    def computeNumbers(i:Int = 0, x:Int = 1, y:Int = 1): (Int, Int) ={
      if (i >= contributions.length)
        (x, y)
      else if (contributions(i) > 0)
        computeNumbers(i+1, x * brutalPow(primes(i), contributions(i)), y)
      else if (contributions(i) < 0)
        computeNumbers(i+1, x, y * brutalPow(primes(i), -contributions(i)))
      else
        computeNumbers(i+1, x, y)
    } */

    val contributionsX = Array.fill(primes.length)(-1)
    val contributionsY = Array.fill(primes.length)(-1)

    @tailrec
    @inline
    def computeNumber(contributions: Array[Int],
                      i: Int = 0, accumulator: Int = 1): Int = {
      if (i >= contributions.length) {
//        println(s"computeNumber :: $accumulator")
        accumulator
      }
      else {
        computeNumber(contributions, i+1, accumulator * math.pow(primes(i), contributions(i)).asInstanceOf[Int])
      }
    }

    @tailrec
    def iterateCombinations(contributions: Array[Int], i: Int, endCondition: Int=>Boolean, continuation: Int=>Int, hitEnd:Boolean = false, accumulator:Int = 0): Int ={
      if (i < 0) {
//        println(s"iterateCombinations :: $accumulator")
        accumulator
      }

      else if (i >= primes.length) {
        val number = computeNumber(contributions)
        if (endCondition(number))
          iterateCombinations(contributions, i-1, endCondition, continuation, hitEnd=true, accumulator)
        else
          iterateCombinations(contributions, i-1, endCondition, continuation, hitEnd=false, accumulator + continuation(number))

      } else {
        if (hitEnd)
          if (contributions(i) == 0) {
            contributions(i) = -1
            iterateCombinations(contributions, i-1, endCondition, continuation, hitEnd=true, accumulator)
          } else {
            contributions(i) = -1
            iterateCombinations(contributions, i-1, endCondition, continuation, hitEnd=false, accumulator)
          }
        else {
          contributions(i) += 1
          iterateCombinations(contributions, i+1, endCondition, continuation, hitEnd=false, accumulator)
        }
      }
    }
/*
    // To insulate tail and non-tail cals
    def launchIterateCombinations(contributions: Array[Int], i: Int, continuation: Int=>Int): Int =
      iterateCombinations(contributions, i, continuation)

//    @tailrec
    def iterateOneContribution(index: Int = 0
    ,
              accumulator:Long = 0): Long = {
      if (index >= contributions.length) {
        0
      }
      else {

        val resultFromDeep = 9999
        if (contributions(index) > 0)
          contributions(index) = -contributions(index)
        else
          contributions(index) = -contributions(index) + 1

        iterateOneContribution()
      }

    }*/

    iterateCombinations(contributionsX, 0, _ > n,
      x => iterateCombinations(contributionsY, 0, x + _ > n, y => {
        if (x > y) {
//          println(s"Reached top with $x, $y")
          n / (x + y) - 1
        }
        else
          0
      }))
  }

  private[kata] final def computeZoneB() =
    n/2 - 1

  private[kata] final def computeZoneD() =
    n - 1

  @tailrec
  private[kata] final def brutalPow(a: Int, b: Int, more:Int = 1): Int = {
    if (b < 1)
      more
    else if (b == 1)
      a * more
    else if (b % 2 == 0)
      brutalPow(a * a, b / 2, more)
    else
      brutalPow(a, b - 1, more * a)
  }
//  def restOfPoints(level: Int):Long = {
//    if (level >= n - 2)
//      return 0
//    val line = n / level - 1
//    // My initial mind said `level to level * 2 - 2` but that overshot
//    // With `until` 5 and 10 are fine, but 1000 overshoots
//    // There's a more systemic overcounting problem
//    val linesInBetween = (level until level * 2 - 2).map(n / _).sum
//    val restOfIt = restOfPoints(level * 2)
//    line + 2 * restOfIt + linesInBetween
//  }

  private[kata] final def hcd(a: Int, b: Int): Int = {
/*
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
    hcdInner()*/
    0
  }
}
