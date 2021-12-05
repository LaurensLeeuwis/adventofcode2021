package eu.leeuwis.adventofcode2021.day2;

class Position {
    private final long horizontal;
    private final long depth;

    private Position(long horizontal, long depth) {
        this.horizontal = horizontal;
        this.depth = depth;
    }

    static Position startPosition() {
        return new Position(0,0);
    }

    Position newPosition(Movement movement) {
        long units = movement.units();
        switch (movement.direction()){
            case UP -> {
                return new Position(horizontal, depth-units);
            }
            case DOWN -> {
                return new Position(horizontal, depth+units);
            }
            case FORWARD -> {
                return new Position(horizontal+units, depth);
            }
        }
        // switch above is exhaustive?
        return this;
    }

    long getMultiple() {
        return horizontal * depth;
    }
}
