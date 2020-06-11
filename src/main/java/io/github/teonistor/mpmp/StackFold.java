package io.github.teonistor.mpmp;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static io.github.teonistor.mpmp.Orientation.Mirror;
import static io.github.teonistor.mpmp.Orientation.Normal;
import static io.github.teonistor.mpmp.Orientation.Undetermined;
import static io.github.teonistor.mpmp.Orientation.UpsideDown;
import static io.github.teonistor.mpmp.Side.Bottom;
import static io.github.teonistor.mpmp.Side.Left;
import static io.github.teonistor.mpmp.Side.Right;

public class StackFold {

    private static final ArrayList<String> solutions = new ArrayList<>();

    public static void main(String[] arg) {
        go(List.of(new FirstRect(), new Rect(), new Rect(), new Rect(), new Rect(), new Rect(), new Rect(), new Rect()),
                0, 6, 4);
    }

    static void go(List<Rect> stack, int index, int verticalsLeft, int horizontalsLeft) {
        if (verticalsLeft + horizontalsLeft == 0) {
            solution(stack);
            return;

        } else if (index == 8) {
            // Dead end
            return;
        }

        if (stack.get(index).canLeaveAlone()) {
            go(stack, index+1, verticalsLeft, horizontalsLeft);
        }

        if (verticalsLeft > 0) {

            Predicate<Orientation> affects = o -> o == Mirror || o == UpsideDown;

            Orientation o;

            UnaryOperator<Side> iff =

            final Side sideToTry = Left;


            if(stack.get(index).canHinge(sideToTry, o)) {
                for (int i = index+1; i < 8; i++) {
                    if (stack.get(i).canHinge(sideToTry.opposite(), o.mirror())) {
                        new Hinge(, stack.get(index),stack.get(i));
                    } else if () {
                        break;
                    }
                }
            }
        }


        for (final Side side : Side.values()) {

        }
    }

    static void solution (List<Rect> stack) {
        // TODO Hypervalidate
        solutions.add(stack.toString());
    }
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
    Normal, Mirror, Flipped, UpsideDown, Undetermined;

    private Orientation flip, mirror;

    public Orientation flip() {
        return flip;
    }

    public Orientation mirror() {
        return mirror;
    }

    static {
        Normal       .flip   = Flipped;
        Normal       .mirror = Mirror;
        Mirror       .flip   = UpsideDown;
        Mirror       .mirror = Normal;
        Flipped      .flip   = Normal;
        Flipped      .mirror = UpsideDown;
        UpsideDown   .flip   = Mirror;
        UpsideDown   .mirror = Flipped;
        Undetermined .flip   = Undetermined;
        Undetermined .mirror = Undetermined;
    }
}

@AllArgsConstructor
class Rect {
    final Orientation orientation;
    final Map<Side,Hinge> hinges;

    public Rect() {
        this(Undetermined, HashMap.empty());
    }

    boolean canHinge(Side side, Orientation orientation) {
        return hinges.size() < 3
                && !hinges.containsKey(side)
                && (this.orientation == Undetermined || this.orientation == orientation);
    }

    boolean canLeaveAlone() {
        return hinges.size() > 1;
    }

    Rect hinge(Side side, Hinge hinge, Orientation orientation) {
        return new Rect(orientation, hinges.put(side, hinge));
    }
}

class FirstRect extends Rect {

    public FirstRect() {
        super(Normal, HashMap.empty());
    }

    public FirstRect(Map<Side, Hinge> hinges) {
        super(Normal, hinges);
    }

    @Override
    boolean canHinge(Side side, Orientation orientation) {
        return (side == Bottom || side == Right)
                && orientation == Normal
                && !hinges.containsKey(side);
    }

    @Override
    Rect hinge(Side side, Hinge hinge, Orientation orientation) {
        return new FirstRect(hinges.put(side,hinge));
    }
}

@AllArgsConstructor
class Hinge {
    final Side side; // Side of reality when we look at the stack
    final Rect from, to;
}
