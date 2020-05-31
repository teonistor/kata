package io.github.teonistor.mpmp;

import io.vavr.Tuple2;
import io.vavr.collection.List;

import java.util.function.Function;

import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;


/** Place n tokens on an nxn grid such that any two distances between any two tokens are different
 *  http://www.think-maths.co.uk/uniquedistance
 */
public class UniqueDistance {
    public static void main(final String[] arg) {
        // This program never terminates
        iterate(1, n -> n+1).forEach(UniqueDistance::solve);
    }

    static void solve(final int n) {
        solve(List.empty(), List.empty(), n, n);
    }

    static void solve(final List<Tuple2<Integer,Integer>> occupied, final List<Integer> squareDistances, final int leftToPlace, final int n) {
        if (leftToPlace < 1) {
            System.out.printf("Solution for %d:\t%s\t square distances: %s%n", n, occupied, squareDistances/*.map(Math::sqrt)*/);
            return;
        }

        range(0,n).parallel().forEach(i -> range(0,n).forEach(j -> {
            final Tuple2<Integer,Integer> newOccupied = new Tuple2<>(i, j);

            if (!occupied.contains(newOccupied)) {
                final List<Integer> newDistances = squareDistances.appendAll(occupied.map(sqDist(i, j)));

                // Distances are unique (existing & new) <=> a set made out of this list has the same size as the list itself
                if (newDistances.toSet().size() == newDistances.size()) {
                    solve(occupied.append(newOccupied), newDistances, leftToPlace-1, n);
                }
            }
        }));
    }

    static Function<Tuple2<Integer,Integer>, Integer> sqDist(final int i, final int j) {
        return t -> {
            final int ai = t._1 - i;
            final int bj = t._2 - j;
            return ai * ai + bj * bj;
        };
    }
}
