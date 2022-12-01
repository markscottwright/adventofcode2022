package adventofcode2022;

import java.util.Arrays;
import java.util.stream.LongStream;

public class Day1 {
    public static void main(String[] args) {
        String input = Util.inputAsString(Day1.class);
        System.out.println("Day 1 part 1: " + getBiggestIndividualTotal(input));
        System.out.println("Day 1 part 2: " + getBiggestThreeTotal(input));
    }

    public static long getBiggestIndividualTotal(String input) {
        return elfCaloriesStream(input).max().getAsLong();
    }

    public static LongStream elfCaloriesStream(String input) {
        return Arrays.stream(input.split("\n\n")).mapToLong(individualElfLines -> {
            return Arrays.stream(individualElfLines.split("\n")).mapToLong(Integer::parseInt).sum();
        });
    }

    public static long getBiggestThreeTotal(String input) {
        long[] elfCalories = elfCaloriesStream(input).toArray();
        Arrays.sort(elfCalories);
        long biggestThreeTotal = elfCalories[elfCalories.length - 1] + elfCalories[elfCalories.length - 2]
                + elfCalories[elfCalories.length - 3];
        return biggestThreeTotal;
    }
}
