package adventofcode2022;

import java.util.HashMap;

public class Day8 {
    static public class ForestMap {

        private HashMap<Point, Integer> treeHeights;
        private HashMap<Point, Integer> maxHeightAbove = new HashMap<>();
        private HashMap<Point, Integer> maxHeightBelow = new HashMap<>();
        private HashMap<Point, Integer> maxHeightLeft = new HashMap<>();
        private HashMap<Point, Integer> maxHeightRight = new HashMap<>();
        private HashMap<Point, Integer> scenicScores = new HashMap<>();
        private int maxX;
        private int maxY;

        public ForestMap(HashMap<Point, Integer> pointHeights, int maxX, int maxY) {
            this.treeHeights = pointHeights;
            this.maxX = maxX;
            this.maxY = maxY;

            for (int x = 0; x < maxX; x++)
                for (int y = 0; y < maxY; y++) {
                    Point point = new Point(x, y);
                    scenicScores.put(point, scenicScore(point));
                }

            for (int x = 0; x < maxX; x++) {
                int maxAbove = -1;
                for (int y = 0; y < maxY; y++) {
                    Point point = new Point(x, y);
                    maxHeightAbove.put(point, maxAbove);
                    maxAbove = Math.max(pointHeights.get(point), maxAbove);

                }
                int maxBelow = -1;
                for (int y = maxY - 1; y >= 0; y--) {
                    Point point = new Point(x, y);
                    maxHeightBelow.put(point, maxBelow);
                    maxBelow = Math.max(pointHeights.get(point), maxBelow);
                }
            }

            for (int y = 0; y < maxY; y++) {
                int maxLeft = -1;
                for (int x = 0; x < maxX; x++) {
                    Point point = new Point(x, y);
                    maxHeightLeft.put(point, maxLeft);
                    maxLeft = Math.max(pointHeights.get(point), maxLeft);
                }
                int maxRight = -1;
                for (int x = maxX - 1; x >= 0; x--) {
                    Point point = new Point(x, y);
                    maxHeightRight.put(point, maxRight);
                    maxRight = Math.max(pointHeights.get(point), maxRight);
                }
            }
        }

        public static ForestMap parse(String input) {
            HashMap<Point, Integer> pointHeights = new HashMap<>();
            int y = 0;
            int maxX = 0;
            for (String line : input.split("\n")) {
                maxX = line.length();
                for (int x = 0; x < line.length(); x++) {
                    pointHeights.put(new Point(x, y), line.charAt(x) - '0');
                }
                y++;
            }

            return new ForestMap(pointHeights, maxX, y);
        }

        @Override
        public String toString() {
            return "ForestMap [pointHeights=" + treeHeights + ", maxX=" + maxX + ", maxY=" + maxY + "]";
        }

        int numTreesVisible() {
            return (int) treeHeights.keySet().stream().filter(this::isVisible).count();
        }

        int scenicScore(Point p) {
            int scoreRight = 0;
            Point right = p.right();
            while (this.treeHeights.containsKey(right)) {
                scoreRight++;
                if (treeHeights.get(right) >= treeHeights.get(p))
                    break;
                right = right.right();
            }

            int scoreLeft = 0;
            Point left = p.left();
            while (this.treeHeights.containsKey(left)) {
                scoreLeft++;
                if (treeHeights.get(left) >= treeHeights.get(p))
                    break;
                left = left.left();
            }
            int scoreAbove = 0;
            Point above = p.above();
            while (this.treeHeights.containsKey(above)) {
                scoreAbove++;
                if (treeHeights.get(above) >= treeHeights.get(p))
                    break;
                above = above.above();
            }
            int scoreBelow = 0;
            Point below = p.below();
            while (this.treeHeights.containsKey(below)) {
                scoreBelow++;
                if (treeHeights.get(below) >= treeHeights.get(p))
                    break;
                below = below.below();
            }

            return scoreBelow * scoreAbove * scoreLeft * scoreRight;
        }

        void printVisible() {
            for (int y = 0; y < maxY; ++y) {
                for (int x = 0; x < maxX; ++x) {
                    System.out.print(isVisible(new Point(x, y)) ? 'X' : '.');
                }
                System.out.println();
            }
        }

        boolean isVisible(Point p) {
            int height = treeHeights.get(p);
            return height > maxHeightAbove.get(p) || height > maxHeightBelow.get(p) || height > maxHeightLeft.get(p)
                    || height > maxHeightRight.get(p);
        }

        public int maxScenicScore() {
            return scenicScores.values().stream().mapToInt(i -> i).max().getAsInt();
        }
    }

    public static void main(String[] args) {
        ForestMap forestMap = ForestMap.parse(Util.inputAsString(Day8.class));
        System.out.println("Day 8 part 1: " + forestMap.numTreesVisible());
        System.out.println("Day 8 part 2: " + forestMap.maxScenicScore());
    }
}
