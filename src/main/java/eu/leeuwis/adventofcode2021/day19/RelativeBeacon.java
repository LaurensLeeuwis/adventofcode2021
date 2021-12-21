package eu.leeuwis.adventofcode2021.day19;

record RelativeBeacon(int x, int y, int z) {
    Distance distance(RelativeBeacon other) {
        double a = Math.abs(other.x() - x);
        double b = Math.abs(other.y() - y);
        double c = Math.abs(other.z() - z);

        return new Distance(Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2)));
    }
}
