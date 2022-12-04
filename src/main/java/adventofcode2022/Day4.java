package adventofcode2022;

import org.apache.commons.lang3.tuple.Pair;

public class Day4 {
    static class Range {
        final long start;
        final long end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        static Range parse(String rangeString) {
            String[] fields = rangeString.split("-");
            return new Range(Long.parseLong(fields[0]), Long.parseLong(fields[1]));
        }

        boolean contains(Range other) {
            return start <= other.start && end >= other.end;
        }

        boolean overlap(Range other) {
            return start <= other.end && end >= other.start;
        }
    }

    static Pair<Range, Range> parseLine(String line) {
        String[] fields = line.split(",");
        return Pair.of(Range.parse(fields[0]), Range.parse(fields[1]));
    }

    public static void main(String[] args) {
        //@formatter:off
        long numFullyContained = Util.inputAsLinesStream(Day4.class)
                .map(Day4::parseLine)
                .filter(p -> p.getLeft().contains(p.getRight()) || p.getRight().contains(p.getLeft()))
                .count();
        //@formatter:on
        System.out.println("Day 4 part 1: " + numFullyContained);

        //@formatter:off
        long numOverlaps = Util.inputAsLinesStream(Day4.class)
                .map(Day4::parseLine)
                .filter(p -> p.getLeft().overlap(p.getRight()))
                .count();
        //@formatter:on
        System.out.println("Day 4 part 2: " + numOverlaps);
    }
}
