package eu.leeuwis.adventofcode2021.day13;

record Dot(int x, int y) {
    Dot(String x, String y) {
        this(Integer.parseInt(x), Integer.parseInt(y));
    }
}
