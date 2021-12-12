package eu.leeuwis.adventofcode2021.day12;

record Edge(Cave a, Cave b) {
    boolean hasCave(Cave cave) {
        return a.equals(cave) || b.equals(cave);
    }

    Cave getOther(Cave cave) {
        if (a.equals(cave)) {
            return b;
        }
        return a;
    }
}
