package io.github.teonistor.interviewquestions;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class WiquestionTest {

    @Test
    public void empty() {
        assertEquals(emptyList(), new Wiquestion().solve(emptyList()));
    }

    @Test
    public void allDifferent() {
        final List<String> input = ImmutableList.of("Amsterdam", "Paris");
        final List<List<String>> expected = ImmutableList.of(singletonList("Amsterdam"), singletonList("Paris"));
        assertEquals(expected, new Wiquestion().solve(input));
    }

    @Test
    public void allSame() {
        final List<String> input = ImmutableList.of("Tokyo", "Kyoto");
        final List<List<String>> expected = singletonList(ImmutableList.of("Tokyo", "Kyoto"));
        assertEquals(expected, new Wiquestion().solve(input));
    }

    @Test
    public void someSame() {
        final List<String> input = ImmutableList.of("Aaabb", "Aabab", "Babaa", "Aaaaa");
        final List<List<String>> expected = ImmutableList.of(
                singletonList("Aaabb"),
                ImmutableList.of("Aabab", "Babaa"),
                singletonList("Aaaaa")
        );
        assertEquals(expected, new Wiquestion().solve(input));
    }

    @Test
    public void theOneInTheQuestion() {
        final List<String> input = ImmutableList.of("Amsterdam", "Tokyo", "Kyoto", "Donlon", "Paris", "London");
        final List<List<String>> expected = ImmutableList.of(
                singletonList("Amsterdam"),
                ImmutableList.of("Tokyo", "Kyoto"),
                ImmutableList.of("Donlon", "London"),
                singletonList("Paris")
        );
        assertEquals(expected, new Wiquestion().solve(input));
    }
}