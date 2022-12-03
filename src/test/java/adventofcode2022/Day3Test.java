package adventofcode2022;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import adventofcode2022.Day3.Rucksack;

public class Day3Test {

    @Test
    public void testPart1() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        int part1SampleSum = Arrays.stream(input.split("\n")).map(Rucksack::parse).mapToInt(Rucksack::getPriority)
                .sum();
        Assertions.assertThat(part1SampleSum).isEqualTo(157);
    }

    @Test
    public void testPart2() {
        var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        long prioritySum = 0;
        String[] inputLines = input.split("\n");
        for (int i = 0; i < inputLines.length; i += 3) {
            //@formatter:off
            prioritySum += Rucksack.getPriority(Rucksack.commonToThree(
                    Rucksack.parse(inputLines[i]),
                    Rucksack.parse(inputLines[i + 1]),
                    Rucksack.parse(inputLines[i + 2])));
            //@formatter:on
        }
        Assertions.assertThat(prioritySum).isEqualTo(70);
    }
}
