package adventofcode2022;

import java.util.HashSet;

public class Day6 {
    public static void main(String[] args) {
        System.out
                .println("Day 6 part 1: " + findEndPositionOfFirstStartOfPacketMarker(Util.inputAsString(Day6.class)));
        System.out
                .println("Day 6 part 2: " + findEndPositionOfFirstStartOfMessageMarker(Util.inputAsString(Day6.class)));
    }

    public static int findEndPositionOfFirstStartOfPacketMarker(String input) {
        for (int i = 3; i < input.length(); ++i) {
            //@formatter:off
            if (input.charAt(i - 3) == input.charAt(i - 2)
                    || input.charAt(i - 3) == input.charAt(i - 1)
                    || input.charAt(i - 3) == input.charAt(i)
                    || input.charAt(i - 2) == input.charAt(i - 1)
                    || input.charAt(i - 2) == input.charAt(i)
                    || input.charAt(i - 1) == input.charAt(i))
                continue;
            //@formatter:on

            return i + 1;
        }
        return -1;
    }

    public static int findEndPositionOfFirstStartOfMessageMarker(String input) {
        for (int i = 13; i < input.length(); ++i) {
            HashSet<Character> last14Characters = new HashSet<>();
            for (int j = i - 13; j <= i; ++j)
                last14Characters.add(input.charAt(j));
            if (last14Characters.size() == 14)
                return i + 1;
        }
        return -1;
    }
}
