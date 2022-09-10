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

  test("H(5)") {
    assert(new Euler351(5).computePointsOfInterest() == 30L)
  }

  test("H(10)") {
    assert(new Euler351(10).computePointsOfInterest() == 138L)
  }

  test("H(1000)") {
    assert(new Euler351(1000).computePointsOfInterest() == 1177848L)
  }

  test("H(10 000)") {
    println(s"H(10 000) = ${new Euler351(10_000).computePointsOfInterest()}")
    // After about 1 minute, 117645084
  }

  test("H(100 000 000)") {
    println(s"H(100 000 000) = ${new Euler351(100_000_000).computePointsOfInterest()}")
  }
}
