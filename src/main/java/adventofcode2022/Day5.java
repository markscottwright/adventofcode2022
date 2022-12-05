package adventofcode2022;

import java.util.ArrayList;
import java.util.HashMap;

import adventofcode2022.Day5.CrateStacks;

public class Day5 {
    static class Instruction {
        @Override
        public String toString() {
            return "Instruction [count=" + count + ", from=" + from + ", to=" + to + "]";
        }

        private int count;
        private int from;
        private int to;

        public Instruction(int count, int from, int to) {
            this.count = count;
            this.from = from;
            this.to = to;
        }

        static Instruction parse(String line) {
            String[] instructionParts = line.split(" ");
            return new Instruction(Integer.parseInt(instructionParts[1]), Integer.parseInt(instructionParts[3]),
                    Integer.parseInt(instructionParts[5]));
        }
    }

    static class CrateStacks {
        HashMap<Integer, ArrayList<Character>> stacks = new HashMap<>();
        ArrayList<Instruction> instructions = new ArrayList<>();

        static CrateStacks parse(String input) {
            CrateStacks crateStack = new CrateStacks();
            String[] inputParts = input.split("\n\n");

            // parse diagram
            String[] stacksDiagramLines = inputParts[0].split("\n");
            // skip the labels line
            for (int i = stacksDiagramLines.length - 2; i >= 0; i--) {
                for (int j = 1; j < stacksDiagramLines[i].length(); j += 4) {
                    char crateLabel = stacksDiagramLines[i].charAt(j);
                    if (!Character.isWhitespace(crateLabel)) {
                        int stackNumber = (j - 1) / 4 + 1;
                        if (!crateStack.stacks.containsKey(stackNumber))
                            crateStack.stacks.put(stackNumber, new ArrayList<>());
                        crateStack.stacks.get(stackNumber).add(crateLabel);
                    }
                }
            }

            // parse instructions
            for (String instructionString : inputParts[1].split("\n")) {
                crateStack.instructions.add(Instruction.parse(instructionString));
            }

            return crateStack;
        }

        @Override
        public String toString() {
            return "CrateStacks [stacks=" + stacks + ", instructions=" + instructions + "]";
        }

        public void run(int modelNumber) {
            for (Instruction instruction : instructions) {
                ArrayList<Character> moving = new ArrayList<>();
                for (int i = 0; i < instruction.count; ++i) {
                    var fromStack = stacks.get(instruction.from);
                    if (modelNumber == 9000)
                        moving.add(fromStack.remove(fromStack.size() - 1));
                    else {
                        assert modelNumber == 9001;
                        moving.add(0, fromStack.remove(fromStack.size() - 1));
                    }
                }
                stacks.get(instruction.to).addAll(moving);
            }
        }

        public String getTopCratesString() {
            String out = "";
            for (int i = 1; i <= stacks.size(); ++i) {
                out += stacks.get(i).get(stacks.get(i).size() - 1);
            }
            return out;
        }

    }

    public static void main(String[] args) {
        CrateStacks crateStacks = CrateStacks.parse(Util.inputAsString(Day5.class));
        crateStacks.run(9000);
        System.out.println("Day 5 part 1:" + crateStacks.getTopCratesString());

        crateStacks = CrateStacks.parse(Util.inputAsString(Day5.class));
        crateStacks.run(9001);
        System.out.println("Day 5 part 2:" + crateStacks.getTopCratesString());
    }
}
