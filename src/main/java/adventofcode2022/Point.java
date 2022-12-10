package adventofcode2022;

public record Point(int x, int y) {
    Point above() {return new Point(x, y-1);}
    Point below() {return new Point(x, y+1);}
    Point left() {return new Point(x-1, y);}
    Point right() {return new Point(x+1, y);}
}
