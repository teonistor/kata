package io.github.teonistor.interviewquestions;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class Wiquestion {
    public List<List<String>> solve(final List<String> input) {

        final List<List<String>> result = new ArrayList<>();

        input.forEach(i -> {
            result.stream()
                    .filter(r -> r.stream().anyMatch(s -> rotationEq(s, i)))
                    .findFirst()
                    .orElseGet(() -> {
                        final ArrayList<String> r = new ArrayList<>();
                        result.add(r)
                                ;
                        return r;
                    })
                    .add(i);
        });

        return result;
    }

    private boolean rotationEq(final String a, final String b) {
        return a.length() == b.length() &&
                range(0, a.length())
                .mapToObj(i -> a.substring(i) + a.substring(0, i))
                .anyMatch(b::equalsIgnoreCase);
    }
}
