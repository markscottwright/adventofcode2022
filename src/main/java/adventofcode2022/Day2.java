package adventofcode2022;

import java.util.HashMap;

public class Day2 {
    enum Result {
        LOSE(0), DRAW(3), WIN(6);

        public final int score;

        Result(int score) {
            this.score = score;
        }
    }

    enum Move {
        ROCK(1, Result.DRAW, Result.LOSE, Result.WIN),
        PAPER(2, Result.WIN, Result.DRAW, Result.LOSE),
        SCISSORS(3, Result.LOSE, Result.WIN, Result.DRAW);

        public int score;
        public Result againstRock;
        public Result againstPaper;
        public Result againstSissors;

        Move(int score, Result againstRock, Result againstPaper, Result againstSissors) {
            this.score = score;
            this.againstRock = againstRock;
            this.againstPaper = againstPaper;
            this.againstSissors = againstSissors;
        }

        int scoreAgainst(Move opponent) {
            if (opponent == ROCK) {
                return score + againstRock.score;
            } else if (opponent == PAPER) {
                return score + againstPaper.score;
            } else {
                assert (opponent == SCISSORS);
                return score + againstSissors.score;
            }
        }
    }

    static HashMap<Character, Move> plaintextParser = new HashMap<>();
    static HashMap<Character, Move> encryptedParser = new HashMap<>();
    static HashMap<Character, Result> expectedResultParser = new HashMap<>();
    static {
        plaintextParser.put('A', Move.ROCK);
        plaintextParser.put('B', Move.PAPER);
        plaintextParser.put('C', Move.SCISSORS);
        encryptedParser.put('X', Move.ROCK);
        encryptedParser.put('Y', Move.PAPER);
        encryptedParser.put('Z', Move.SCISSORS);
        expectedResultParser.put('X', Result.LOSE);
        expectedResultParser.put('Y', Result.DRAW);
        expectedResultParser.put('Z', Result.WIN);
    }

    static class Round {
        @Override
        public String toString() {
            return String.format("them: %-8s me: %-8s expectedScore: %-4s", opponentMove, myMove2, expectedResult);
        }

        private Move opponentMove;
        private Move myMove;
        private Move myMove2;
        private Result expectedResult;

        static Round parse(String roundString) {
            Round round = new Round();
            String[] fields = roundString.split(" ");
            round.opponentMove = plaintextParser.get(fields[0].charAt(0));
            round.myMove = encryptedParser.get(fields[1].charAt(0));
            round.expectedResult = expectedResultParser.get(fields[1].charAt(0));

            for (Move myPossibleMove : new Move[] { Move.ROCK, Move.PAPER, Move.SCISSORS }) {
                if (round.opponentMove == Move.ROCK && myPossibleMove.againstRock == round.expectedResult) {
                    round.myMove2 = myPossibleMove;
                    break;
                } else if (round.opponentMove == Move.PAPER && myPossibleMove.againstPaper == round.expectedResult) {
                    round.myMove2 = myPossibleMove;
                    break;
                } else if (round.opponentMove == Move.SCISSORS
                        && myPossibleMove.againstSissors == round.expectedResult) {
                    round.myMove2 = myPossibleMove;
                    break;
                }
            }

            return round;
        }

        public Move getOpponentMove() {
            return opponentMove;
        }

        public Move getMyMove() {
            return myMove;
        }

        public int getMyScore() {
            return myMove.scoreAgainst(opponentMove);
        }

        public int getMyScore2() {
            return myMove2.scoreAgainst(opponentMove);
        }
    }

    public static void main(String[] args) {
        long part1Score = Util.inputAsLinesStream(Day2.class).map(Round::parse).mapToLong(Round::getMyScore).sum();
        System.out.println("Day 2 part 1: " + part1Score);
        long part2Score = Util.inputAsLinesStream(Day2.class).map(Round::parse).mapToLong(Round::getMyScore2).sum();
        System.out.println("Day 2 part 2: " + part2Score);
    }
}
