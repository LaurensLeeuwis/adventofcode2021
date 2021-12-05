package eu.leeuwis.adventofcode2021.day2;

enum Direction {
    UP, DOWN, FORWARD;

    static Direction fromString(String input) {
        if ("forward".equals(input)){
            return FORWARD;
        } else if ("down".equals(input)){
            return DOWN;
        } else if ("up".equals(input)){
            return UP;
        }
        return null;
    }
}
