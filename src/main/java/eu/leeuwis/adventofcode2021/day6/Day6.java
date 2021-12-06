package eu.leeuwis.adventofcode2021.day6;

import java.util.Arrays;

public class Day6 {

    long numberOfLanternfish(String initial, int day){
        long[] initialTimers = new long[9];
        Arrays.stream(initial.split(","))
                .map(Integer::parseInt)
                .forEach(fishTimer -> initialTimers[fishTimer]++);

        long[] timers = initialTimers;

        for (int i = 0; i < day; i++) {
            long[] newTimers = new long[9];
            System.arraycopy(timers, 1, newTimers, 0, 8);
            newTimers[8] = timers[0];
            newTimers[6] += timers[0];
            timers = newTimers;
        }

        return Arrays.stream(timers).sum();
    }
}
