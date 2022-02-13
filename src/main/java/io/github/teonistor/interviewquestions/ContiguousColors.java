package io.github.teonistor.interviewquestions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;


public class ContiguousColors {

    private final Map<Coord, Cell> cells;

    public ContiguousColors(final int[][] input) {
        cells = range(0, input.length)
            .boxed()
            // All (i, j) pairs in a single stream, as instances of Coord
            .flatMap(i -> range(0, input[i].length).mapToObj(j -> new Coord(i, j)))
            // Map each Coord to a Cell
            .collect(toMap(identity(), c -> new Cell(c, input[c.i][c.j])));
    }

    List<Set<Coord>> solve() {
        final List<Set<Coord>> sets = cells.values().stream()
            // Turn each cell into a set of itself and its immediate adjacencies, ...
            .map(cell -> Stream.of(cell.coord, cell.coord.up(), cell.coord.down(), cell.coord.left(), cell.coord.right())
                // ...but only if they exist ...
                .filter(coord -> Optional.ofNullable(cells.get(coord))
                // ... and have the same color as the cell we began from
                .filter(c -> c.color == cell.color).isPresent())
                .collect(toSet()))
            .collect(toList());

        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = false;
            final int bound = sets.size();

            // For every pair of sets from above...
            for (int i = 0; i < bound; i++) {
                for (int j = i; j < bound; j++) {

                    // ... if they are different and overlap ...
                    if (i != j && !Collections.disjoint(sets.get(i), sets.get(j))) {
                        // ... pour one into the other
                        sets.get(i).addAll(sets.get(j));
                        sets.get(j).clear();

                        // And keep going until a full iteration incurs no pouring
                        keepGoing = true;
                    }
                }
            }
        }

        // Keep nonempty sets, sorted descendingly by size
        return sets.stream().filter(s -> !s.isEmpty())
                .sorted(Comparator.<Set<Coord>>comparingInt(Set::size).reversed())
                .collect(toList());
    }


    @AllArgsConstructor
    class Cell {
        final Coord coord;
        final int color;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    class Coord {
        final int i,j;

        Coord up() {
            return new Coord(i-1, j);
        }
        Coord down() {
            return new Coord(i+1, j);
        }
        Coord left() {
            return new Coord(i, j-1);
        }
        Coord right() {
            return new Coord(i, j+1);
        }

        public String toString() {
            return String.format("(%d,%d)", i,j);
        }
    }
}
