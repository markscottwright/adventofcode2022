package adventofcode2022;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day4Test {

    @Test
    public void testPartOne() {
        String input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;
        //@formatter:off
        long numContains = Arrays.stream(input.split("\n")).map(Day4::parseLine)
                .filter(p -> p.getLeft().contains(p.getRight()) || p.getRight().contains(p.getLeft()))
                .count();
        //@formatter:on
        Assertions.assertThat(numContains).isEqualTo(2);
    }

    @Test
    public void testPartTwo() {
        String input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;
        //@formatter:off
        long numOverlaps = Arrays.stream(input.split("\n")).map(Day4::parseLine)
                .filter(p -> p.getLeft().overlap(p.getRight()))
                .count();
        //@formatter:on
        Assertions.assertThat(numOverlaps).isEqualTo(4);
    }

}
