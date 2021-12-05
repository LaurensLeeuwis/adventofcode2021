package eu.leeuwis.adventofcode2021.day5;

class Line {
    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;

    Line(String input) {
        String[] first = input.split(" -> ");
        String[] from = first[0].split(",");
        String[] to = first[1].split(",");

        fromX = Integer.parseInt(from[0]);
        fromY = Integer.parseInt(from[1]);
        toX = Integer.parseInt(to[0]);
        toY = Integer.parseInt(to[1]);
    }

    boolean isHorizontal() {
        return fromX == toX;
    }

    boolean isVertical() {
        return fromY == toY;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    @Override
    public String toString() {
        return "Line {" + fromX +
                "," + fromY +
                " -> " + toX +
                "," + toY +
                '}';
    }
}
