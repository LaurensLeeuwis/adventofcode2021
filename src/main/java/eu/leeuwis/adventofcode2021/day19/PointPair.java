package eu.leeuwis.adventofcode2021.day19;

record PointPair(RelativeBeacon left, RelativeBeacon right) {
    boolean kindOfEquals(PointPair other) {
        int d = Math.abs(left.x() - right.x());
        int e = Math.abs(left.y() - right.y());
        int f = Math.abs(left.z() - right.z());

        int k = Math.abs(other.left.x() - other.right.x());
        int l = Math.abs(other.left.y() - other.right.y());
        int m = Math.abs(other.left.z() - other.right.z());

        if (d == k) {
            if (e == l && f == m) {
                return true;
            } else if (e == m && f == l) {
                return true;
            }
        } else if (d == l) {
            if (e == k && f == m) {
                return true;
            } else if (e == m && f == k) {
                return true;
            }
        } else if (d == m) {
            if (e == k && f == l) {
                return true;
            } else if (e == l && f == k) {
                return true;
            }
        }
        return false;
    }
}
