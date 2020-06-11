package io.github.teonistor.mpmp;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.function.Consumer;

import static io.github.teonistor.mpmp.Orientation.Flipped;
import static io.github.teonistor.mpmp.Orientation.Mirror;
import static io.github.teonistor.mpmp.Orientation.Normal;
import static io.github.teonistor.mpmp.Orientation.UpsideDown;
import static java.util.stream.Collectors.joining;


/** How many 2x4 folding challenges are there?
 *  http://www.think-maths.co.uk/foldingchallenge
 */
public class StackFold {
    public static void main(final String[] arg) {

        final ArrayList<List<Orientation>> allStacks = new ArrayList<>();
        final List<Orientation> orientations = List.of(Normal, UpsideDown, UpsideDown, Mirror, Mirror, Flipped, Flipped);
        buildStacks(List.of(Normal), orientations, allStacks::add);

        final Stream<List<Rect>> uniqueStacks = Stream.ofAll(allStacks).distinct().map(stack -> stack.map(Rect::new));

        final ArrayList<List<Rect>> solutions = new ArrayList<>();
        uniqueStacks.forEach(stack -> hingeBottom(stack, 0, 4, 4, 2, solutions::add));

        final List<String> distinct = Stream.ofAll(solutions).map(StackFold::explicitate).filter(str -> !str.isEmpty()).distinct().toList();
        distinct.forEach(System.out::println);
        System.out.println("Total explicit: " + distinct.size());
        System.out.println("Total raw: " + solutions.size());
    }

    static void buildStacks(final List<Orientation> currentStack, final List<Orientation> orientations, final Consumer<List<Orientation>> consumer) {
        if (currentStack.size() == 8) {
            consumer.accept(currentStack);
            return;
        }

        for (int i = 0; i < orientations.size(); i++) {
            buildStacks(currentStack.append(orientations.get(i)), orientations.removeAt(i), consumer);
        }
    }

    static void hingeBottom(final List<Rect> stack, final int index, final int bottomsLeft, final int rightsLeft, final int leftsLeft, final Consumer<List<Rect>> consumer) {
        if (index == 8) {
            if (bottomsLeft + rightsLeft + leftsLeft == 0) {
                consumer.accept(stack);
            }
            return;
        }

        final Rect thisRect = stack.get(index);
        if (thisRect.bottom < 0) {
            if (bottomsLeft > 0) {
                for (int thatIndex = index + 1; thatIndex < 8; thatIndex++) {
                    final Rect thatRect = stack.get(thatIndex);
                    if (thatRect.bottom < 0) {
                        if (thisRect.orientation.flip() == thatRect.orientation) {
                            hingeRight(stack.update(index, thisRect.hingeBottom(thatIndex)).update(thatIndex, thatRect.hingeBottom(index)),
                                    index, bottomsLeft - 1, rightsLeft, leftsLeft, consumer);
                        }
                    } else {
                        break;
                    }
                }
            }

        } else {
            hingeRight(stack, index, bottomsLeft, rightsLeft, leftsLeft, consumer);
        }
    }

    static void hingeRight(final List<Rect> stack, final int index, final int bottomsLeft, final int rightsLeft, final int leftsLeft, final Consumer<List<Rect>> consumer) {
        final Rect thisRect = stack.get(index);
        if (thisRect.right < 0) {
            if (rightsLeft > 0) {
                for (int thatIndex = index + 1; thatIndex < 8; thatIndex++) {
                    final Rect thatRect = stack.get(thatIndex);
                    if (thatRect.right < 0) {
                        if (thisRect.orientation.mirror() == thatRect.orientation) {

                            // Left hinge is optional so we make one call going to the next item in the stack and one adding another hinge
                            final List<Rect> newStack = stack.update(index, thisRect.hingeRight(thatIndex)).update(thatIndex, thatRect.hingeRight(index));
                            hingeBottom(newStack, index + 1, bottomsLeft, rightsLeft - 1, leftsLeft, consumer);
                            hingeLeft(newStack, index, bottomsLeft, rightsLeft - 1, leftsLeft, consumer);
                        }
                    } else {
                        break;
                    }
                }
            }

        } else {
            // Left hinge is optional so we make one call going to the next item in the stack and one adding another hinge
            hingeBottom(stack, index+1, bottomsLeft, rightsLeft, leftsLeft, consumer);
            hingeLeft(stack, index, bottomsLeft, rightsLeft, leftsLeft, consumer);
        }
    }

    static void hingeLeft(final List<Rect> stack, final int index, final int bottomsLeft, final int rightsLeft, final int leftsLeft, final Consumer<List<Rect>> consumer) {
        // "A" never has left hinge
        if (index == 0) {
            return;
        }

        final Rect thisRect = stack.get(index);
        if (thisRect.left < 0) {
            if (rightsLeft > 0) {
                for (int thatIndex = index + 1; thatIndex < 8; thatIndex++) {
                    final Rect thatRect = stack.get(thatIndex);
                    if (thatRect.left < 0) {
                        if (thisRect.orientation.mirror() == thatRect.orientation) {
                            hingeBottom(stack.update(index, thisRect.hingeLeft(thatIndex)).update(thatIndex, thatRect.hingeLeft(index)),
                                    index + 1, bottomsLeft, rightsLeft, leftsLeft - 1, consumer);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    static String explicitate(final List<Rect> stack) {
        final String orientationStrings = stack.toJavaStream()
                .map(rect -> rect.orientation.toString().substring(0, 1))
                .collect(joining());

        // Navigate top row
        final Rect m = stack.get(0);
        final Rect n = stack.get(m.right);
        if (n.left < 0) {
            System.err.println("Explicitate bail because n had no left");
            return "";
        }
        final Rect o = stack.get(n.left);
        final Rect p = stack.get(o.right);

        // Navigate bottom row
        final Rect q = stack.get(m.bottom);
        final Rect r = stack.get(q.right);
        if (r.left < 0) {
            System.err.println("Explicitate bail because r had no left");
            return "";
        }
        final Rect s = stack.get(r.left);
        final Rect t = stack.get(s.right);

        // Ensure remaining hinges connect correctly
        if (stack.get(n.bottom) != r
         || stack.get(o.bottom) != s
         || stack.get(p.bottom) != t) {
            System.err.println("Explicitate bail because the small hinges did not match");
            return "";
        }

        final String sheetString = List.of(m, n, o, p, q, r, s, t).toJavaStream()
                .mapToInt(stack::indexOf)
                .mapToObj(i -> "" + (char) ('A' + i))
                .collect(joining());
        return sheetString + " / " + orientationStrings;
    }
}

enum Orientation {
    Normal, Mirror, Flipped, UpsideDown;

    private Orientation flip, mirror;

    public Orientation flip() {
        return flip;
    }

    public Orientation mirror() {
        return mirror;
    }

    static {
        Normal    .flip   = Flipped;
        Normal    .mirror = Mirror;
        Mirror    .flip   = UpsideDown;
        Mirror    .mirror = Normal;
        Flipped   .flip   = Normal;
        Flipped   .mirror = UpsideDown;
        UpsideDown.flip   = Mirror;
        UpsideDown.mirror = Flipped;
    }
}

@AllArgsConstructor
class Rect {
    final Orientation orientation;
    final int bottom, right, left;

    public Rect(final Orientation orientation) {
        this(orientation, -1, -1, -1);
    }

    public Rect hingeBottom(final int index) {
        invariantNegative(bottom, "bottom");
        return new Rect(orientation, index, right, left);
    }

    public Rect hingeRight(final int index) {
        invariantNegative(right, "right");
        return new Rect(orientation, bottom, index, left);
    }

    public Rect hingeLeft(final int index) {
        invariantNegative(left, "left");
        return new Rect(orientation, bottom, right, index);
    }

    private void invariantNegative(final int index, final String side) {
        if (index >= 0) {
            System.err.printf("Rect: Invariant violation! Hinging %s on already hinged %s%n", side, side);
        }
    }
}

/*
I claim this is the definitive answer
First column is what to write on the paper, second is the orientations of stack pieces when the puzzle is solved

 AHBCFGED / NNMUFFUM
 ADBCHEGF / NNMMUUFF
 AHBEFGCD / NNFUMFUM
 AHBGDECF / NNFFUUMM
 AHDCFGEB / NUMNFFUM
 AFDEHGCB / NUFNMMUF
 ABCDHGFE / NMNMUFUF
 ADCBHEFG / NMNMUFUF
 ABCFHGDE / NMNFUMUF
 ABCHFEDG / NMNFUFUM
 AHCBFGDE / NMNFUFUM
 ABFGHCED / NMUUFNMF
 ABEDHGFC / NMUMNFUF
 ABGDHCFE / NMUMUFNF
 ABEFHCDG / NMUFNMUF
 AFEBHGDC / NMUFNMUF
 ABGFHCDE / NMUFUMNF
 ABGHDCFE / NMUFUFNM
 ABHEDCGF / NMUFMUFN
 ABFGDCEH / NMUFFNMU
 ABHGDCEF / NMUFFUMN
 ABDCHGEF / NMMNFUUF
 AFCDHGBE / NFNMUMUF
 ADEFBCHG / NFUMNMUF
 AFEDHGBC / NFUMNMUF
 ADEHBCFG / NFUMNFUM
 ADGFBCHE / NFUMUMNF
 ADFEBCGH / NFUMMNFU
 AFGHBEDC / NFUFUMNM
 AFHGBECD / NFFUUMMN
 Total explicit: 30
 Total raw: 433

After fixing the orientation conditions but before fixing stack generation (was too restrictive) I knew I'd fixed the orientation 
conditions because for the first time I got distinct sheets irrespective of orientations:
 Total explicit: 17
 Total raw: 295

*/
