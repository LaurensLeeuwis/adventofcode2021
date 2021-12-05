package eu.leeuwis.adventofcode2021.day2;

class Position2 {
    private final long horizontal;
    private final long depth;
    private final long aim;

    private Position2(long horizontal, long depth, long aim) {
        this.horizontal = horizontal;
        this.depth = depth;
        this.aim = aim;
    }

    static Position2 startPosition() {
        return new Position2(0,0, 0);
    }

    Position2 newPosition(Movement movement) {
        long units = movement.units();
        switch (movement.direction()){
            case UP -> {
                return new Position2(horizontal, depth, aim-units);
            }
            case DOWN -> {
                return new Position2(horizontal, depth, aim+units);
            }
            case FORWARD -> {
                return new Position2(horizontal+units, depth+(aim*units), aim);
            }
        }
        // switch above is exhaustive?
        return this;
    }

    long getMultiple() {
        return horizontal * depth;
    }
}
