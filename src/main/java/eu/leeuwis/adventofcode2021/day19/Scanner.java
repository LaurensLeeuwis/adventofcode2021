package eu.leeuwis.adventofcode2021.day19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Scanner {
    private final Map<Distance, List<PointPair>> distances;
    private final String id;
    private final List<RelativeBeacon> relativeBeacons;

    Scanner(String id,
            List<RelativeBeacon> relativeBeacons) {
        this.id = id;
        this.relativeBeacons = Collections.unmodifiableList(relativeBeacons);
        this.distances = calculateDistances(relativeBeacons);
    }

    String id() {
        return id;
    }

    List<RelativeBeacon> relativeBeacons() {
        return relativeBeacons;
    }

    private Map<Distance, List<PointPair>> calculateDistances(
            List<RelativeBeacon> relativeBeacons) {
        Map<Distance, List<PointPair>> result = new HashMap<>();

        for (int i = 0; i < relativeBeacons.size(); i++) {
            for (int j = 0; j < i; j++) {
                RelativeBeacon left = relativeBeacons.get(i);
                RelativeBeacon right = relativeBeacons.get(j);
                PointPair pair = new PointPair(left, right);
                Distance distance = left.distance(right);

                List<PointPair> pairsAtDistance = result.getOrDefault(distance, new ArrayList<>());
                pairsAtDistance.add(pair);
                result.put(distance, pairsAtDistance);
            }
        }

        return Collections.unmodifiableMap(result);
    }

    boolean possiblyOverlaps(Scanner other) {
        // at least 12 beacons overlap
        int overlaps = 0;
        Iterator<Distance> distanceIterator = other.distances.keySet().iterator();
        while (overlaps < 12 && distanceIterator.hasNext()) {
            if (distances.containsKey(distanceIterator.next())) {
                overlaps++;
            }
        }
        return overlaps >= 12;
    }

    Map<RelativeBeacon, RelativeBeacon> translations(Scanner other) {
        List<Pair> equalling = new ArrayList<>();

        for (Distance otherDistance : other.distances.keySet()) {
            if (distances.containsKey(otherDistance)) {
                var otherPairs = other.distances.get(otherDistance);
                var pairs = distances.get(otherDistance);

                equalling.addAll(equalling(pairs, otherPairs));
            }
        }

        Map<RelativeBeacon, RelativeBeacon> translations = new HashMap<>();

        equalling.stream()
                .map(Pair::left)
                .flatMap(leftPair -> Stream.of(leftPair.left(), leftPair.right()))
                .collect(Collectors.toSet())
                .forEach(beacon -> {
                    RelativeBeacon toBeacon = findTranslation(beacon, equalling);
                    translations.put(beacon, toBeacon);
                });

        return translations;
    }

    private RelativeBeacon findTranslation(RelativeBeacon beacon, List<Pair> equalling) {
        var x = equalling.stream()
                .filter(pair -> pair.left().left().equals(beacon) || pair.left().right().equals(beacon))
                .map(Pair::right)
                .flatMap(rightPair -> Stream.of(rightPair.left(), rightPair.right()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        RelativeBeacon result = null;
        long occurences = 0;

        for (Map.Entry<RelativeBeacon, Long> entry : x.entrySet()) {
            if (entry.getValue() > occurences) {
                occurences = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }

    private List<Pair> equalling(List<PointPair> pairs, List<PointPair> otherPairs) {
        List<Pair> result = new ArrayList<>();

        for (PointPair pair : pairs) {
            for (PointPair otherPair : otherPairs) {
                if (pair.kindOfEquals(otherPair)) {
                    result.add(new Pair(pair, otherPair));
                }
            }
        }
        return result;
    }

    private record Pair(PointPair left, PointPair right) {
    }

}
