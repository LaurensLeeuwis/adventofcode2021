package eu.leeuwis.adventofcode2021.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day14 {

    Map<String, List<String>> fromTo = new HashMap<>();
    String startString;
    Map<String, Long> pairCount = new HashMap<>();

    public long puzzle1(List<String> input) {
        parseInput(input);
        return calcNr(10);
    }

    public long puzzle2(List<String> input) {
        parseInput(input);
        return calcNr(40);
    }

    private void parseInput(List<String> input) {
        startString = input.get(0);
        for (int i = 2; i < input.size(); i++) {
            String line = input.get(i);
            String[] parts = line.split(" -> ");
            String from = parts[0];
            String to1 = new String(new char[]{from.charAt(0), parts[1].charAt(0)});
            String to2 = new String(new char[]{parts[1].charAt(0), from.charAt(1)});
            List<String> to = new ArrayList<>();
            to.add(to1);
            to.add(to2);
            fromTo.put(from, Collections.unmodifiableList(to));
        }
    }

    private long calcNr(int times) {
        for (int i = 0; i < startString.length() - 1; i++) {
            String part = startString.substring(i, i+2);
            long newVal = pairCount.getOrDefault(part, 0L) + 1;
            pairCount.put(part, newVal);
        }

        for (int i = 0; i < times; i++) {
            Map<String, Long> nextCount = new HashMap<>();

            for (Map.Entry<String, Long> entry : pairCount.entrySet()) {
                String key = entry.getKey();
                for (String next : fromTo.get(key)) {
                    long count = nextCount.getOrDefault(next, 0L);
                    nextCount.put(next, count + entry.getValue());
                }
            }
            pairCount = nextCount;
        }

        Map<Character, Long> nrs = new HashMap<>();
        for (Map.Entry<String, Long> entry : pairCount.entrySet()) {
            String key = entry.getKey();
            long count = entry.getValue();

            char aChar = key.charAt(1);
            long current = nrs.getOrDefault(aChar, 0L);
            nrs.put(aChar, current+count);
        }

        char aChar = startString.charAt(0);
        long current = nrs.getOrDefault(aChar, 0L);
        nrs.put(aChar, current+1);


        long maxNr = 0;
        long minNr = Long.MAX_VALUE;

        for (Map.Entry<Character, Long> entry : nrs.entrySet()) {
            if (entry.getValue() > maxNr){
                maxNr = entry.getValue();
            }
            if (entry.getValue() < minNr){
                minNr = entry.getValue();
            }
        }

        return maxNr - minNr;
    }


}
