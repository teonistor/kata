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
import static io.github.teonistor.mpmp.Side.Bottom;
import static io.github.teonistor.mpmp.Side.Left;
import static io.github.teonistor.mpmp.Side.Right;
import static java.util.stream.Collectors.joining;

public class StackFold {

    public static void main(String[] arg) {

        ArrayList<List<Orientation>> allStacks = new ArrayList<>();
        final List<Orientation> odd = List.of(Normal, UpsideDown, UpsideDown);
        final List<Orientation> even = List.of(Mirror, Mirror, Flipped, Flipped);
        buildStacks(List.of(Normal), even, odd, allStacks::add);

        final Stream<List<Rect>> uniqueStacks = Stream.ofAll(allStacks).distinct().map(stack -> stack.map(Rect::new));

        final ArrayList<List<Rect>> solutions = new ArrayList<>();
        uniqueStacks.forEach(stack -> hingeBottom(stack, 0, 4, 4, 2, solutions::add));

        final List<String> solutionStrings = Stream.ofAll(solutions).map(StackFold::explicitate).filter(str -> !str.isEmpty()).toList();
        solutionStrings.forEach(System.out::println);
        System.out.println("Total explicit: " + solutionStrings.size());
        System.out.println("Total raw: " + solutions.size());

//        go(List.of(new FirstRect(), new Rect(), new Rect(), new Rect(), new Rect(), new Rect(), new Rect(), new Rect()),
//                0, 6, 4);
    }

    static void buildStacks(List<Orientation> currentStack, List<Orientation> nextPick, List<Orientation> otherPick, Consumer<List<Orientation>> consumer) {
        if (currentStack.size() == 8) {
            consumer.accept(currentStack);
            return;
        }

        for (int i = 0; i < nextPick.size(); i++) {
            buildStacks(currentStack.append(nextPick.get(i)), otherPick, nextPick.removeAt(i), consumer);
        }
    }

    static void hingeBottom(List<Rect> stack, int index, int bottomsLeft, int rightsLeft, int leftsLeft, Consumer<List<Rect>> consumer) {
        if (index == 8) {
            if (bottomsLeft + rightsLeft + leftsLeft == 0) {
                consumer.accept(stack);
            }
            return;
        }

        final String mnemo = stack.toJavaStream()
                .map(rect -> rect.orientation.toString().substring(0, 1))
                .collect(joining());
        final String ffs = mnemo;

        final Rect thisRect = stack.get(index);
        if (thisRect.bottom == null) {
            if (bottomsLeft > 0) {
                for (int thatIndex = index + 1; thatIndex < 8; thatIndex++) {
                    final Rect thatRect = stack.get(thatIndex);
                    if (thatRect.bottom == null) {
                        final Hinge hinge = new Hinge(Bottom, thisRect, thatRect);

                        hingeRight(stack.update(index, thisRect.hingeBottom(hinge)).update(thatIndex, thatRect.hingeBottom(hinge)),
                                index, bottomsLeft - 1, rightsLeft, leftsLeft, consumer);
                    } else {
                        break;
                    }
                }
            }

        } else {
            hingeRight(stack, index, bottomsLeft, rightsLeft, leftsLeft, consumer);
        }
    }

    static void hingeRight(List<Rect> stack, int index, int bottomsLeft, int rightsLeft, int leftsLeft, Consumer<List<Rect>> consumer) {
        final Rect thisRect = stack.get(index);
        if (thisRect.right == null) {
            if (rightsLeft > 0) {
                for (int thatIndex = index + 1; thatIndex < 8; thatIndex++) {
                    final Rect thatRect = stack.get(thatIndex);
                    if (thatRect.right == null) {
                        final Hinge hinge = new Hinge(Right, thisRect, thatRect);

                        // Left hinge is optional so we make one call going to the next item in the stack and one adding another hinge
                        final List<Rect> newStack = stack.update(index, thisRect.hingeRight(hinge)).update(thatIndex, thatRect.hingeRight(hinge));
                        hingeBottom(newStack, index + 1, bottomsLeft, rightsLeft - 1, leftsLeft, consumer);
                        hingeLeft(newStack, index, bottomsLeft, rightsLeft - 1, leftsLeft, consumer);
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

    static void hingeLeft(List<Rect> stack, int index, int bottomsLeft, int rightsLeft, int leftsLeft, Consumer<List<Rect>> consumer) {
        // "A" never has left hinge
        if (index == 0) {
            return;
        }

        final Rect thisRect = stack.get(index);
        if (thisRect.left == null) {
            if (rightsLeft > 0) {
                for (int thatIndex = index + 1; thatIndex < 8; thatIndex++) {
                    final Rect thatRect = stack.get(thatIndex);
                    if (thatRect.left == null) {
                        final Hinge hinge = new Hinge(Left, thisRect, thatRect);

                        hingeBottom(stack.update(index, thisRect.hingeLeft(hinge)).update(thatIndex, thatRect.hingeLeft(hinge)),
                                index + 1, bottomsLeft, rightsLeft, leftsLeft - 1, consumer);
                    } else {
                        break;
                    }
                }
            }

        } else {
            // Actually this would create duplicates because left hinge is optional
//            hingeBottom(stack, index+1, bottomsLeft, rightsLeft, leftsLeft, consumer);
        }
    }

    static String explicitate(List<Rect> stack) {
        final Rect m = stack.get(0);
        final Rect n = m.right.otherThan(m);
        if (n.left == null) {
            System.err.println("Explicitate bail because n had no left");
            return "";
        }
        final Rect o = n.left.otherThan(n);
        final Rect p = o.right.otherThan(o);

        final Rect q = m.bottom.otherThan(m);
        final Rect r = q.right.otherThan(q);
        if (r.left == null) {
            System.err.println("Explicitate bail because r had no left");
            return "";
        }
        final Rect s = r.left.otherThan(r);
        final Rect t = s.right.otherThan(s);

        if (n.bottom.otherThan(n) != r
         || o.bottom.otherThan(o) != s
         || p.bottom.otherThan(p) != t) {
            System.err.println("Explicitate bail because the small hinges did not match");
            return "";
        }

        final List<Rect> m1 = List.of(m, n, o, p, q, r, s, t);
        return m1.toJavaStream()
                 .mapToInt(stack::indexOf)
                 .mapToObj(i -> "" + ('A' + (char)(int)i))
                 .collect(joining())
                + " / " +
               m1.toJavaStream()
                 .map(rect -> rect.orientation.toString().substring(0, 1))
                 .collect(joining());
    }

//    static void go(List<Rect> stack, int index, int verticalsLeft, int horizontalsLeft) {
//        if (verticalsLeft + horizontalsLeft == 0) {
//            solution(stack);
//            return;
//
//        } else if (index == 8) {
//            // Dead end
//            return;
//        }
//
//        if (stack.get(index).canLeaveAlone()) {
//            go(stack, index+1, verticalsLeft, horizontalsLeft);
//        }
//
//        if (verticalsLeft > 0) {
//
//            Predicate<Orientation> affects = o -> o == Mirror || o == UpsideDown;
//
//            Orientation o;
//
//            UnaryOperator<Side> iff =
//
//            final Side sideToTry = Left;
//
//
//            if(stack.get(index).canHinge(sideToTry, o)) {
//                for (int i = index+1; i < 8; i++) {
//                    if (stack.get(i).canHinge(sideToTry.opposite(), o.mirror())) {
//                        new Hinge(, stack.get(index),stack.get(i));
//                    } else if () {
//                        break;
//                    }
//                }
//            }
//        }
//
//
//        for (final Side side : Side.values()) {
//
//        }
//    }
//
//    static void solution (List<Rect> stack) {
//        // TODO Hypervalidate
//        solutions.add(stack.toString());
//    }
}

enum Side {
    Left, Right, Top, Bottom;

    private Side opposite;

    public Side opposite() {
        return opposite;
    }

    static {
        Left.opposite = Right;
        Right.opposite = Left;
        Top.opposite = Bottom;
        Bottom.opposite = Top;
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
    final Hinge bottom, right, left;

    public Rect(Orientation orientation) {
        this(orientation, null, null, null);
    }

    public Rect hingeBottom(Hinge hinge) {
        invariantNull(bottom, "bottom");
        return new Rect(orientation, hinge, right, left);
    }

    public Rect hingeRight(Hinge hinge) {
        invariantNull(right, "right");
        return new Rect(orientation, bottom, hinge, left);
    }

    public Rect hingeLeft(Hinge hinge) {
        invariantNull(left, "left");
        return new Rect(orientation, bottom, right, hinge);
    }

    private void invariantNull(Hinge what, String side) {
        if (what != null) {
            System.err.printf("Rect: Invariant violation! Hinging %s on already hinged %s%n", side, side);
        }
    }
}

//class FirstRect extends Rect {
//
//    public FirstRect() {
//        super(Normal, HashMap.empty());
//    }
//
//    public FirstRect(Map<Side, Hinge> hinges) {
//        super(Normal, hinges);
//    }
//
//    @Override
//    boolean canHinge(Side side, Orientation orientation) {
//        return (side == Bottom || side == Right)
//                && orientation == Normal
//                && !hinges.containsKey(side);
//    }
//
//    @Override
//    Rect hinge(Side side, Hinge hinge, Orientation orientation) {
//        return new FirstRect(hinges.put(side,hinge));
//    }
//}

@AllArgsConstructor
class Hinge {
    final Side side; // Side of reality when we look at the stack
    final Rect from, to;

    public Rect otherThan(Rect thisOne) {
        if (thisOne == from) {
            return to;
        } else {
            if (thisOne != to) {
                System.err.println("Hinge:Invariant violation! Unknown rect passed to otherThan()");
            }
            return from;
        }
    }
}
