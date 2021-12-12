package eu.leeuwis.adventofcode2021.day12;

record Cave(String id) {
    boolean isBig() {
        return id.toUpperCase().equals(id);
    }

    boolean isStart() {
        return id.equals("start");
    }
}
