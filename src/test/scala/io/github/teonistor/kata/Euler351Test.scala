package io.github.teonistor.kata

import org.scalatest.funsuite.AnyFunSuite

class Euler351Test  extends AnyFunSuite {

  test("Compute Primes") {
    assert(new Euler351(3).primes.equals(List(
      2, 3)))
    assert(new Euler351(10).primes.equals(List(
      2, 3, 5, 7)))
    assert(new Euler351(50).primes.equals(List(
      2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)))
  }

  test("Compute Zones A C") {
    assert(new Euler351(3).computeZonesAC() == 0)
    assert(new Euler351(5).computeZonesAC() == 0)
    assert(new Euler351(6).computeZonesAC() == 1)
 }

  test("Compute Zone B") {
    assert(new Euler351(3).computeZoneB() == 0)
    assert(new Euler351(5).computeZoneB() == 1)
    assert(new Euler351(10).computeZoneB() == 4)
  }

  test("Compute Zone D") {
    assert(new Euler351(3).computeZoneD() == 2)
    assert(new Euler351(5).computeZoneD() == 4)
    assert(new Euler351(10).computeZoneD() == 9)
  }

  test("HCD") {
    val e = new Euler351(100)

    assert(e.hcd(7, 3) == 1)
    assert(e.hcd(2, 4) == 2)
    assert(e.hcd(100, 8) == 4)
    assert(e.hcd(24, 60) == 12)
    assert(e.hcd(8, 100) == 4)
    assert(e.hcd(3, 7) == 1)
    assert(e.hcd(3, 500) == 1)
    assert(e.hcd(4, 2) == 2)
    assert(e.hcd(60, 24) == 12)
    assert(e.hcd(200, 3) == 1)
  }

  test("brutalPow()") {
    val e = new Euler351(0)

    assert(e.brutalPow(1, 10) == 1)
    assert(e.brutalPow(10, 7) == 10000000)
    assert(e.brutalPow(2, 10) == 1024)
    assert(e.brutalPow(3, 4) == 81)
  }

  test("H(5)") {
    assert(new Euler351(5).computePointsOfInterest() == 30L)
  }

  test("H(10)") {
    assert(new Euler351(10).computePointsOfInterest() == 138L)
  }

  test("H(1000)") {
    // v4: 1891920 did not equal 1177848
    assert(new Euler351(1000).computePointsOfInterest() == 1177848L)
  }

  test("H(various)") {
    /* v5 1 min 10 sec this section
       H(1 000) = 1177848
       H(2 000) = 4706472
       H(4 000) = 18830388
       H(8 000) = 75289308
     */

    /* v5.1
       H(250) = 74106, took 806ms
       H(500) = 294804, took 769ms
       H(1 000) = 1177848, took 3037ms
       H(2 000) = 4706472, took 10291ms
       Still seems O(n^2)
     */

    def time[T](prefix: String, t: =>T):Unit ={
      val start = System.currentTimeMillis()
      val result = t
      val end = System.currentTimeMillis()
      println(s"$prefix $result, took ${end-start}ms")
    }

    time("H(250) =", new Euler351(250).computePointsOfInterest())
    time("H(500) =", new Euler351(500).computePointsOfInterest())
    time("H(1 000) =", new Euler351(1_000).computePointsOfInterest())
    time("H(2 000) =", new Euler351(2_000).computePointsOfInterest())
    time("H(4 000) =", new Euler351(4_000).computePointsOfInterest())
    time("H(8 000) =", new Euler351(8_000).computePointsOfInterest())
  }

  test("H(10 000)") {
    val result = new Euler351(10_000).computePointsOfInterest()
    println(s"H(10 000) = $result")
    // v3???
    // After about 1 minute, 117645084
    assert(result == 117645084)
  }

  test("H(100 000)") {
    println(s"H(100 000) = ${new Euler351(100_000).computePointsOfInterest()}")
  }

  test("H(1 000 000)") {
    println(s"H(1 000 000) = ${new Euler351(1_000_000).computePointsOfInterest()}")
  }

  test("H(100 000 000)") {
    println(s"H(100 000 000) = ${new Euler351(100_000_000).computePointsOfInterest()}")
  }
}
