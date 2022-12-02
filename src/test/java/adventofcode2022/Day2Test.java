package adventofcode2022;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import adventofcode2022.Day2.Round;

public class Day2Test {

    @Test
    public void testSample1() {
        String input = """
                A Y
                B X
                C Z
                """;

        Assertions.assertThat(Day2.Round.parse("A Y").getOpponentMove()).isEqualTo(Day2.Move.ROCK);
        Assertions.assertThat(Day2.Round.parse("A Y").getMyMove()).isEqualTo(Day2.Move.PAPER);

        long totalScore = Arrays.stream(input.split("\n")).map(Round::parse).mapToLong(Round::getMyScore).sum();
        Assertions.assertThat(totalScore).isEqualTo(15);
    }

    @Test
    public void testSample2() {
        String input = """
                A Y
                B X
                C Z
                """;
        long totalScore = Arrays.stream(input.split("\n")).map(Round::parse).mapToLong(Round::getMyScore2).sum();
        Assertions.assertThat(totalScore).isEqualTo(12);
    }
}
