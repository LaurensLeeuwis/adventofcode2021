package eu.leeuwis.adventofcode2021.day17;

record Position(int x, int y) {
    Position plus(Velocity v) {
        return new Position(x + v.x(), y + v.y());
    }

    boolean inBox(Box box) {
        return box.xEnd() >= x && x >= box.xStart() && box.yStart() >= y && y >= box.yEnd();
    }

    boolean overBox(Box box) {
        return x > box.xEnd() || y < box.yEnd();
    }
}
