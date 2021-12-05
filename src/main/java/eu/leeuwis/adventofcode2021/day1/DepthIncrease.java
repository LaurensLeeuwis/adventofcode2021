package eu.leeuwis.adventofcode2021.day1;

import java.util.ArrayList;
import java.util.List;

public class DepthIncrease {

    public long numberOfIncreases(List<String> measurements) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        measurements.stream()
                .map(Integer::parseInt)
                .reduce((first, second) -> {
                            pairs.add(new Pair<>(first, second));
                            return second;
                        }
                );

        return pairs.stream().filter(pair -> pair.getSecond() > pair.getFirst()).count();
    }

    public long numberOfSlidingWindowIncreases(List<String> measurements) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        measurements.stream()
                .map(Integer::parseInt)
                .reduce((first, second) -> {
                            pairs.add(new Pair<>(first, second));
                            return second;
                        }
                );

        List<Integer> sums = new ArrayList<>();
        pairs.stream().reduce((first, second) -> {
            sums.add(first.getFirst() + first.getSecond() + second.getSecond());
            return second;
        });

        List<Pair<Integer, Integer>> sumPairs = new ArrayList<>();
        sums.stream().reduce((first, second) -> {
            sumPairs.add(new Pair<>(first, second));
            return second;
        });


        return sumPairs.stream().filter(pair -> pair.getSecond() > pair.getFirst()).count();
    }
}
