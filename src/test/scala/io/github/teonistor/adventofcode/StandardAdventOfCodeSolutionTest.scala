package io.github.teonistor.adventofcode

import org.scalatest.funsuite.AnyFunSuite

class StandardAdventOfCodeSolutionTest extends AnyFunSuite {

    test("Identify year and day") {
        assert(y9999zap._30b.year == 9999)
        assert(y9999zap._30b.day == 30)
    }
}

package y9999zap {
    // noinspection NotImplementedCode
    private[adventofcode] object _30b extends StandardAdventOfCodeSolution {
        override def _1(input: String): Nothing = ???
        override def _2(input: String): Nothing = ???
    }
}

