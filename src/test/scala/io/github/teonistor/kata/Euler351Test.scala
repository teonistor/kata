package io.github.teonistor.kata

import org.scalatest.funsuite.AnyFunSuite

class Euler351Test  extends AnyFunSuite {

  test("Generate Points") {
    assert(new Euler351(3).generatePoints().toSet.equals(Set(
      (1, 0),
      (2, 0),
      (3, 0),
      (1, 1),
      (2, 1),
      (1, 2))))
    assert(new Euler351(5).generatePoints().toSet.equals(Set(
      (1, 0), (2, 0), (3, 0), (4, 0), (5, 0),
      (1, 1), (2, 1), (3, 1), (4, 1),
      (1, 2), (2, 2), (3, 2),
      (1, 3), (2, 3),
      (1, 4))))
  }

  test("HCD") {
    val e = new Euler351(0)

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
    assert(new Euler351(5).hiddenPoints() == 30L)
  }

  test("H(10)") {
    assert(new Euler351(10).hiddenPoints() == 138L)
  }

  test("H(1000)") {
    assert(new Euler351(1000).hiddenPoints() == 1177848L)
  }

  test("H(100 000 000)") {
    println(s"H(100 000 000) = ${new Euler351(100_000_000).hiddenPoints()}")
  }
}
