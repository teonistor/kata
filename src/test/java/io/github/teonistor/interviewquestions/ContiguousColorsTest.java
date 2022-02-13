package io.github.teonistor.interviewquestions;

import io.github.teonistor.interviewquestions.ContiguousColors.Coord;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ContiguousColorsTest {

    @Test void trivial() {
        final int[][] input = {
                {1, 1},
                {1, 1}};

        final List<Set<Coord>> result = new ContiguousColors(input).solve();
        assertEquals(1, result.size());
        assertEquals(4, result.get(0).size());
    }

    @Test void trivialBig() {
        final int[][] input = {
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};

        final List<Set<Coord>> result = new ContiguousColors(input).solve();
        assertEquals(1, result.size());
        assertEquals(52, result.get(0).size());
    }

    @Test void twoEqualZones() {
        final int[][] input = {
                {5, 7},
                {5, 7}};

        final List<Set<Coord>> result = new ContiguousColors(input).solve();
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).size());
        assertEquals(2, result.get(1).size());
    }

    @Test void twoUnequalZones() {
        final int[][] input = {
                {1, 3},
                {1, 1}};

        final List<Set<Coord>> result = new ContiguousColors(input).solve();
        assertEquals(2, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(1, result.get(1).size());
    }

    @Test void threeZonesOfDifferentColors() {
        final int[][] input = {
                {2, 2, 2, 2, 2, 2, 2, 1, 1, 3, 3, 3, 3},
                {2, 2, 2, 2, 2, 2, 2, 1, 1, 3, 3, 3, 3},
                {2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 3, 3, 3},
                {2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 3, 3, 3}};

        final List<Set<Coord>> result = new ContiguousColors(input).solve();
        assertEquals(3, result.size());
        assertEquals(30, result.get(0).size());
        assertEquals(15, result.get(1).size());
        assertEquals(7, result.get(2).size());
    }

    @Test void threeZonesButTwoOfThemAreTheSameColor() {
        final int[][] input = {
                {2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2}};

        final List<Set<Coord>> result = new ContiguousColors(input).solve();
        assertEquals(3, result.size());
        assertEquals(30, result.get(0).size());
        assertEquals(17, result.get(1).size());
        assertEquals(5, result.get(2).size());
    }
}
