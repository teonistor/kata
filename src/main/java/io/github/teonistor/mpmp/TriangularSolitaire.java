
package io.github.teonistor.mpmp;

import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.stream.Stream;


/** Solve a 10-position triangular solitaire
 *  http://www.think-maths.co.uk/coin-puzzle
 */
public class TriangularSolitaire {

    public static final int SOLUTION_LENGTH = 26;
    private final boolean printDeadEnd;
    private final Step[] steps;

    public TriangularSolitaire(final boolean printDeadEnd) {
        this.printDeadEnd = printDeadEnd;
        steps = Arrays.stream(new int[][]{
                {1, 2, 4},
                {2, 4, 7},
                {3, 5, 8},
                {1, 3, 6},
                {3, 6, 10},
                {2, 5, 9},
                {4, 5, 6},
                {7, 8, 9},
                {8, 9, 10}
        })
                .flatMap(this::makeSteps)
                .toArray(Step[]::new);
    }

    private Stream<Step> makeSteps(final int[] raw) {
        final int allMask = (1 << raw[0]) + (1 << raw[1]) + (1 << raw[2]);
        final int oneMask = 1 << raw[0];
        final int otherMask = 1 << raw[2];
        return Stream.of(new Step(raw[2], raw[0], allMask, oneMask), new Step(raw[0], raw[2], allMask, otherMask));
    }

    private boolean isPowerOfTwo(int x) {
        while ((x & 1) == 0) {
            x >>= 1;
        }
        return x == 1;
    }

    @AllArgsConstructor
    private class Step {
        final int from, to, allMask, toMask;
    }

    public void solve(final int board, final int[] partialSolution, final int solutionIndex) {

        if (isPowerOfTwo(board)) {
            printEnd("Solution: ", board, partialSolution, solutionIndex);

            return;
        }

        boolean deadEnd = true;

        for (final Step s : steps) {
            if ((((board ^ s.toMask) & s.allMask) ^ s.allMask) == 0) {
                deadEnd = false;

                if (partialSolution[solutionIndex - 1] == s.from) {
                    partialSolution[solutionIndex] = s.to;
                    solve(board ^ s.allMask, partialSolution, solutionIndex + 1);

                } else {
                    partialSolution[solutionIndex] = 0;
                    partialSolution[solutionIndex + 1] = s.from;
                    partialSolution[solutionIndex + 2] = s.to;
                    solve(board ^ s.allMask, partialSolution, solutionIndex + 3);
                }
            }
        }

        if (deadEnd && printDeadEnd) {
            printEnd("Dead end: ", board, partialSolution, solutionIndex);
        }
    }

    private void printEnd(final String msg, final int board, final int[] partialSolution, final int partialSolutionIndex) {
        System.out.print(msg);
        int steps = -1;

        for (int i = 0; i < partialSolutionIndex; i++) {
            if (partialSolution[i] == 0) {
                steps++;
            }
            System.out.print(partialSolution[i]);
            System.out.print(" ");
        }
        System.out.printf(". %d steps%n%s%n", steps, displayBoard(board));
    }

    private static String displayBoard(final int board) {
        return String.format("     %s%n    %s %s%n   %s %s %s%n  %s %s %s %s",
                (board & (1 << 1)) == 0 ? "O" : "X",
                (board & (1 << 2)) == 0 ? "O" : "X",
                (board & (1 << 3)) == 0 ? "O" : "X",
                (board & (1 << 4)) == 0 ? "O" : "X",
                (board & (1 << 5)) == 0 ? "O" : "X",
                (board & (1 << 6)) == 0 ? "O" : "X",
                (board & (1 << 7)) == 0 ? "O" : "X",
                (board & (1 << 8)) == 0 ? "O" : "X",
                (board & (1 << 9)) == 0 ? "O" : "X",
                (board & (1 << 10)) == 0 ? "O" : "X");
    }

    public static void main(final String[] arg) {
        final int fullBoard = 2046; // 1 + 2 + 4 + ... + 2^10
        final TriangularSolitaire solver = new TriangularSolitaire(false);
        final int[] emptySolution = new int[SOLUTION_LENGTH];
        emptySolution[1] = 0;

        for (int start = 1; start < 11; start++) {
            final int board = fullBoard ^ (1 << start);
            System.out.printf("%nStarting position:%n%s%n", displayBoard(board));

            emptySolution[0] = start;
            solver.solve(board, emptySolution, 2);
        }
    }
}

//    Solution: 2 0 0 7 2 0 6 4 0 1 6 0 10 3 0 8 10 0 4 1 6 0 10 3 . 7 steps
//    Solution: 2 0 0 7 2 0 6 4 0 1 6 0 10 3 0 4 1 0 8 10 0 1 6 0 10 3 . 8 steps
//    Solution: 2 0 0 7 2 0 6 4 0 1 6 0 10 3 0 4 1 6 0 8 10 3 . 6 steps
//    Solution: 2 0 0 7 2 0 6 4 0 1 6 0 4 1 0 10 3 0 8 10 0 1 6 0 10 3 . 8 steps
//    Solution: 2 0 0 7 2 0 6 4 0 1 6 0 4 1 0 10 3 0 1 6 0 8 10 3 . 7 steps
//    Solution: 2 0 0 7 2 0 9 7 0 1 4 0 6 1 0 7 2 0 1 4 6 0 10 3 . 7 steps
//    Solution: 2 0 0 7 2 0 9 7 0 1 4 0 7 2 0 6 4 1 6 0 10 3 . 6 steps
//    Solution: 2 0 0 7 2 0 9 7 0 1 4 0 7 2 0 6 1 4 6 0 10 3 . 6 steps
//    Solution: 2 0 0 7 2 0 1 4 0 6 1 0 4 6 0 10 3 0 8 10 0 1 6 0 10 3 . 8 steps
//    Solution: 2 0 0 7 2 0 1 4 0 6 1 0 4 6 0 10 3 0 1 6 0 8 10 3 . 7 steps
//    Solution: 2 0 0 7 2 0 1 4 0 6 1 0 9 7 2 0 1 4 6 0 10 3 . 6 steps
//    Solution: 2 0 0 7 2 0 1 4 0 9 7 0 6 1 0 7 2 0 1 4 6 0 10 3 . 7 steps
//    Solution: 2 0 0 7 2 0 1 4 0 9 7 2 0 6 4 1 6 0 10 3 . 5 steps
//    Solution: 2 0 0 7 2 0 1 4 0 9 7 2 0 6 1 4 6 0 10 3 . 5 steps
//    Solution: 3 0 0 10 3 0 4 6 0 1 4 0 6 1 0 7 2 0 9 7 0 1 4 0 7 2 . 8 steps
//    Solution: 3 0 0 10 3 0 4 6 0 1 4 0 6 1 0 7 2 0 1 4 0 9 7 2 . 7 steps
//    Solution: 3 0 0 10 3 0 4 6 0 1 4 0 7 2 0 6 1 0 9 7 0 1 4 0 7 2 . 8 steps
//    Solution: 3 0 0 10 3 0 4 6 0 1 4 0 7 2 0 6 1 4 0 9 7 2 . 6 steps
//    Solution: 3 0 0 10 3 0 4 6 0 1 4 0 7 2 0 9 7 0 6 1 4 0 7 2 . 7 steps
//    Solution: 3 0 0 10 3 0 8 10 0 1 6 0 10 3 0 4 6 1 4 0 7 2 . 6 steps
//    Solution: 3 0 0 10 3 0 8 10 0 1 6 0 10 3 0 4 1 6 4 0 7 2 . 6 steps
//    Solution: 3 0 0 10 3 0 8 10 0 1 6 0 4 1 0 10 3 0 1 6 4 0 7 2 . 7 steps
//    Solution: 3 0 0 10 3 0 1 6 0 8 10 3 0 4 6 1 4 0 7 2 . 5 steps
//    Solution: 3 0 0 10 3 0 1 6 0 8 10 3 0 4 1 6 4 0 7 2 . 5 steps
//    Solution: 3 0 0 10 3 0 1 6 0 8 10 0 4 1 0 10 3 0 1 6 4 0 7 2 . 7 steps
//    Solution: 3 0 0 10 3 0 1 6 0 4 1 0 6 4 0 7 2 0 9 7 0 1 4 0 7 2 . 8 steps
//    Solution: 3 0 0 10 3 0 1 6 0 4 1 0 6 4 0 7 2 0 1 4 0 9 7 2 . 7 steps
//    Solution: 3 0 0 10 3 0 1 6 0 4 1 0 8 10 3 0 1 6 4 0 7 2 . 6 steps
//    Solution: 4 0 0 1 4 0 6 1 0 7 2 0 9 7 0 1 4 0 7 2 9 0 10 8 . 7 steps
//    Solution: 4 0 0 1 4 0 6 1 0 7 2 0 1 4 0 9 2 7 9 0 10 8 . 6 steps
//    Solution: 4 0 0 1 4 0 6 1 0 7 2 0 1 4 0 9 7 2 9 0 10 8 . 6 steps
//    Solution: 4 0 0 1 4 0 9 2 0 7 9 0 2 7 0 10 8 0 3 10 0 7 9 0 10 8 . 8 steps
//    Solution: 4 0 0 1 4 0 9 2 0 7 9 0 2 7 0 10 8 0 7 9 0 3 10 8 . 7 steps
//    Solution: 4 0 0 1 4 0 9 2 0 7 9 0 10 8 0 2 7 0 3 10 0 7 9 0 10 8 . 8 steps
//    Solution: 4 0 0 1 4 0 9 2 0 7 9 0 10 8 0 2 7 9 0 3 10 8 . 6 steps
//    Solution: 4 0 0 1 4 0 9 2 0 7 9 0 10 8 0 3 10 0 2 7 9 0 10 8 . 7 steps
//    Solution: 4 0 0 1 4 0 7 2 0 6 1 0 9 7 0 1 4 0 7 2 9 0 10 8 . 7 steps
//    Solution: 4 0 0 1 4 0 7 2 0 6 1 4 0 9 2 7 9 0 10 8 . 5 steps
//    Solution: 4 0 0 1 4 0 7 2 0 6 1 4 0 9 7 2 9 0 10 8 . 5 steps
//    Solution: 4 0 0 1 4 0 7 2 0 9 7 0 6 1 4 0 7 2 9 0 10 8 . 6 steps
//    Solution: 4 0 0 1 4 0 7 2 0 9 7 0 2 9 0 10 8 0 3 10 0 7 9 0 10 8 . 8 steps
//    Solution: 4 0 0 1 4 0 7 2 0 9 7 0 2 9 0 10 8 0 7 9 0 3 10 8 . 7 steps
//    Solution: 6 0 0 1 6 0 10 3 0 8 10 0 3 8 0 7 9 0 2 7 0 10 8 0 7 9 . 8 steps
//    Solution: 6 0 0 1 6 0 10 3 0 8 10 0 3 8 0 7 9 0 10 8 0 2 7 9 . 7 steps
//    Solution: 6 0 0 1 6 0 10 3 0 8 10 0 4 1 6 0 10 3 8 0 7 9 . 6 steps
//    Solution: 6 0 0 1 6 0 10 3 0 4 1 0 8 10 0 1 6 0 10 3 8 0 7 9 . 7 steps
//    Solution: 6 0 0 1 6 0 10 3 0 4 1 6 0 8 10 3 8 0 7 9 . 5 steps
//    Solution: 6 0 0 1 6 0 10 3 0 4 1 6 0 8 3 10 8 0 7 9 . 5 steps
//    Solution: 6 0 0 1 6 0 4 1 0 10 3 0 8 10 0 1 6 0 10 3 8 0 7 9 . 7 steps
//    Solution: 6 0 0 1 6 0 4 1 0 10 3 0 1 6 0 8 10 3 8 0 7 9 . 6 steps
//    Solution: 6 0 0 1 6 0 4 1 0 10 3 0 1 6 0 8 3 10 8 0 7 9 . 6 steps
//    Solution: 6 0 0 1 6 0 8 3 0 10 8 0 3 10 0 7 9 0 2 7 0 10 8 0 7 9 . 8 steps
//    Solution: 6 0 0 1 6 0 8 3 0 10 8 0 3 10 0 7 9 0 10 8 0 2 7 9 . 7 steps
//    Solution: 6 0 0 1 6 0 8 3 0 10 8 0 7 9 0 2 7 0 3 10 8 0 7 9 . 7 steps
//    Solution: 6 0 0 1 6 0 8 3 0 10 8 0 7 9 0 3 10 0 2 7 0 10 8 0 7 9 . 8 steps
//    Solution: 6 0 0 1 6 0 8 3 0 10 8 0 7 9 0 3 10 8 0 2 7 9 . 6 steps
//    Solution: 8 0 0 10 8 0 3 10 0 7 9 0 2 7 0 10 8 0 7 9 2 0 1 4 . 7 steps
//    Solution: 8 0 0 10 8 0 3 10 0 7 9 0 10 8 0 2 7 9 2 0 1 4 . 6 steps
//    Solution: 8 0 0 10 8 0 3 10 0 7 9 0 10 8 0 2 9 7 2 0 1 4 . 6 steps
//    Solution: 8 0 0 10 8 0 7 9 0 2 7 0 3 10 8 0 7 9 2 0 1 4 . 6 steps
//    Solution: 8 0 0 10 8 0 7 9 0 2 7 0 9 2 0 1 4 0 6 1 0 7 2 0 1 4 . 8 steps
//    Solution: 8 0 0 10 8 0 7 9 0 2 7 0 9 2 0 1 4 0 7 2 0 6 1 4 . 7 steps
//    Solution: 8 0 0 10 8 0 7 9 0 3 10 0 2 7 0 10 8 0 7 9 2 0 1 4 . 7 steps
//    Solution: 8 0 0 10 8 0 7 9 0 3 10 8 0 2 7 9 2 0 1 4 . 5 steps
//    Solution: 8 0 0 10 8 0 7 9 0 3 10 8 0 2 9 7 2 0 1 4 . 5 steps
//    Solution: 8 0 0 10 8 0 2 9 0 7 2 0 9 7 0 1 4 0 6 1 0 7 2 0 1 4 . 8 steps
//    Solution: 8 0 0 10 8 0 2 9 0 7 2 0 9 7 0 1 4 0 7 2 0 6 1 4 . 7 steps
//    Solution: 8 0 0 10 8 0 2 9 0 7 2 0 1 4 0 6 1 0 9 7 2 0 1 4 . 7 steps
//    Solution: 8 0 0 10 8 0 2 9 0 7 2 0 1 4 0 9 7 0 6 1 0 7 2 0 1 4 . 8 steps
//    Solution: 8 0 0 10 8 0 2 9 0 7 2 0 1 4 0 9 7 2 0 6 1 4 . 6 steps
//    Solution: 9 0 0 7 9 0 2 7 0 10 8 0 3 10 0 7 9 0 10 8 3 0 1 6 . 7 steps
//    Solution: 9 0 0 7 9 0 2 7 0 10 8 0 7 9 0 3 8 10 3 0 1 6 . 6 steps
//    Solution: 9 0 0 7 9 0 2 7 0 10 8 0 7 9 0 3 10 8 3 0 1 6 . 6 steps
//    Solution: 9 0 0 7 9 0 3 8 0 10 3 0 8 10 0 1 6 0 10 3 0 4 1 6 . 7 steps
//    Solution: 9 0 0 7 9 0 3 8 0 10 3 0 8 10 0 1 6 0 4 1 0 10 3 0 1 6 . 8 steps
//    Solution: 9 0 0 7 9 0 3 8 0 10 3 0 1 6 0 8 10 3 0 4 1 6 . 6 steps
//    Solution: 9 0 0 7 9 0 3 8 0 10 3 0 1 6 0 8 10 0 4 1 0 10 3 0 1 6 . 8 steps
//    Solution: 9 0 0 7 9 0 3 8 0 10 3 0 1 6 0 4 1 0 8 10 3 0 1 6 . 7 steps
//    Solution: 9 0 0 7 9 0 10 8 0 2 7 0 3 10 0 7 9 0 10 8 3 0 1 6 . 7 steps
//    Solution: 9 0 0 7 9 0 10 8 0 2 7 9 0 3 8 10 3 0 1 6 . 5 steps
//    Solution: 9 0 0 7 9 0 10 8 0 2 7 9 0 3 10 8 3 0 1 6 . 5 steps
//    Solution: 9 0 0 7 9 0 10 8 0 3 10 0 2 7 9 0 10 8 3 0 1 6 . 6 steps
//    Solution: 9 0 0 7 9 0 10 8 0 3 10 0 8 3 0 1 6 0 10 3 0 4 1 6 . 7 steps
//    Solution: 9 0 0 7 9 0 10 8 0 3 10 0 8 3 0 1 6 0 4 1 0 10 3 0 1 6 . 8 steps
//
//  {7 steps=30, 6 steps=24, 5 steps=12, 8 steps=18}
