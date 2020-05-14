package io.github.teonistor.mpmp;

import static java.util.stream.IntStream.range;


/** Million Bank Balance Puzzle
 *  https://www.think-maths.co.uk/BankBalance
 */
public class MillionBank {

    private static int maxD = 0;

    public static void main(final String[] arg) {

        range(2,1_000_001).parallel()
            .forEach(i -> range(1,i)
            .forEach(j -> f(j, i - j)));
    }

    static void f (final int first, final int second) {
        int a = first;
        int b = second;
        int c = a + b;
        int d = 2;

        while (c < 1_000_000) {
            a = b;
            b = c;
            c = a + b;
            d++;
            if (c == 1_000_000 && d > maxD) {
                maxD = d;
                System.out.printf("Solution: from %d and %d it took %d days%n", first, second, d);
            }
        }
    }
}

/*

Solution: from 250004 and 374998 it took 3 days
Solution: from 312506 and 124996 it took 4 days
Solution: from 281255 and 31247 it took 5 days
Solution: from 16 and 124990 it took 6 days
Solution: from 39073 and 23431 it took 8 days
Solution: from 154 and 144 it took 19 days

Due to the way the problem is phrased the numbers must be input into
the solution form in the opposite order from how they are printed here

*/
