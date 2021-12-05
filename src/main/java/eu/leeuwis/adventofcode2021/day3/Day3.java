package eu.leeuwis.adventofcode2021.day3;

import java.util.List;
import java.util.function.BiFunction;

public class Day3 {

    long powerConsumption(List<String> inputs) {
        int inputLength = inputs.get(0).length();

        long[] zeroes = new long[inputLength];
        long[] ones = new long[inputLength];

        for (String s : inputs) {
            for (int i = 0; i < inputLength; i++) {
                char c = s.charAt(i);
                if (c == '0'){
                    zeroes[i]++;
                } else if (c == '1'){
                    ones[i]++;
                }
            }
        }

        String gamma = "";
        String epsilon = "";
        for (int i = 0; i < inputLength; i++) {
            if (zeroes[i] > ones[i]){
                gamma += "0";
                epsilon += "1";
            } else {
                gamma += "1";
                epsilon += "0";
            }
        }

        return Long.parseLong(gamma, 2) * Long.parseLong(epsilon, 2);
    }

    private long rating(List<String> inputs, BiFunction<Long, Long, Boolean> bitCriterium) {
        int inputLength = inputs.get(0).length();

        String found = "";
        for (int i = 0; i < inputLength; i++) {
            long zeros = 0;
            long ones = 0;

            for (String s : inputs) {
                boolean skip = false;
                for (int j = 0; j < i; j++) {
                    if (s.charAt(j) != found.charAt(j)) {
                        skip = true;
                        break;
                    }
                }

                if (!skip && s.charAt(i) == '1'){
                    ones++;
                } else if (!skip && s.charAt(i) == '0'){
                    zeros++;
                }
            }

            // All are skipped. Retrace and find solution. Single one left.
            if (ones == 0 && zeros == 0) {
                String stripped = found.substring(0, found.length() - 1);
                for (String s : inputs) {
                    if (s.startsWith(stripped)){
                        found = s;
                    }
                }
                break;
            }

            if (bitCriterium.apply(ones, zeros)){
                found += "1";
            } else {
                found += "0";
            }
        }
        return Integer.parseInt(found, 2);
    }

    long lifeSupportRating(List<String> inputs) {
        long oxy = rating(inputs, (ones, zeroes) -> ones >= zeroes);
        long co2 = rating(inputs, (ones, zeroes) -> ones < zeroes);
        return oxy * co2;
    }
}
