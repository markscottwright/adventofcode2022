package adventofcode2022;

import java.util.HashSet;

public class Day3 {
    public static class Rucksack {

        private HashSet<Character> compartment1;
        private HashSet<Character> compartment2;
        private Character inBothCompartments;
        private HashSet<Character> allItems;

        public static Rucksack parse(String line) {
            var rucksack = new Rucksack();
            rucksack.compartment1 = new HashSet<Character>();
            line.substring(0, line.length() / 2).chars().forEach(c -> rucksack.compartment1.add((char) c));
            rucksack.compartment2 = new HashSet<Character>();
            line.substring(line.length() / 2, line.length()).chars().forEach(c -> rucksack.compartment2.add((char) c));
            rucksack.inBothCompartments = rucksack.compartment1.stream().filter(i -> rucksack.compartment2.contains(i))
                    .findFirst().get();

            rucksack.allItems = new HashSet<Character>();
            rucksack.allItems.addAll(rucksack.compartment1);
            rucksack.allItems.addAll(rucksack.compartment2);

            return rucksack;
        }

        public int getPriority() {
            return getPriority(inBothCompartments);
        }

        public static int getPriority(Character c) {
            if (Character.isUpperCase(c))
                return 27 + (c - 'A');
            else
                return 1 + (c - 'a');
        }

        static Character commonToThree(Rucksack r1, Rucksack r2, Rucksack r3) {
            return r1.allItems.stream().filter(i -> r2.allItems.contains(i) && r3.allItems.contains(i)).findFirst()
                    .get();
        }
    }

    public static void main(String[] args) {
        long prioritySum = Util.inputAsLinesStream(Day3.class).map(Rucksack::parse).mapToLong(Rucksack::getPriority)
                .sum();
        System.out.println("Day 3 part 1: " + prioritySum);

        long prioritySum2 = 0;
        String[] inputLines = Util.inputAsString(Day3.class).split("\n");
        for (int i = 0; i < inputLines.length; i += 3) {
            prioritySum2 += Rucksack.getPriority(Rucksack.commonToThree(Rucksack.parse(inputLines[i]),
                    Rucksack.parse(inputLines[i + 1]), Rucksack.parse(inputLines[i + 2])));
        }
        System.out.println("Day 3 part 2: " + prioritySum2);

    }
}
