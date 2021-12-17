package eu.leeuwis.adventofcode2021.day17;

record Velocity(int x, int y) {
    Velocity next() {
        return new Velocity(Math.max(x - 1, 0), y - 1);
    }
}
