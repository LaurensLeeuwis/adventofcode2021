package eu.leeuwis.adventofcode2021.day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class Day8 {

    public long puzzle1(List<String> input) {
        return input.stream()
                .map(line -> line.split(Pattern.quote(" | "))[1])
                .flatMap(output -> Arrays.stream(output.split(" ")))
                .map(String::length)
                .filter(length -> length == 2 || length == 3 || length == 4 || length == 7)
                .count();
    }

    // 0: A B C E F G     | D         | 6
    // 1: C F             | A B D E G | 2
    // 2: A C D E G       | B F       | 5
    // 3: A C D F G       | B E       | 5
    // 4: B C D F         | A E G     | 4
    // 5: A B D F G       | C E       | 5
    // 6: A B D E F G     | C         | 6
    // 7: A C F           | B D E G   | 3
    // 8: A B C D E F G   |           | 7
    // 9: A B C D F G     | E         | 6

    record PatternInput(String pattern, String input) {
        PatternInput(String[] splitLine) {
            this(splitLine[0], splitLine[1]);
        }
    }

    public long puzzle2(List<String> input) {
        return input.stream()
                .map(line -> new PatternInput(line.split(Pattern.quote(" | "))))
                .map(line -> readNumber(line.pattern.split(" "), line.input.split(" ")))
                .mapToInt(it -> it)
                .sum();
    }

    int readNumber(String[] pattern, String[] input) {
        String result = "";

        input = sortContent(input);
        pattern = sortContent(pattern);

        Map<String, Integer> parsedPattern = parse(pattern);
        for (String s : input) {
            result += parsedPattern.get(s);
        }

        return Integer.parseInt(result);
    }

    private Map<String, Integer> parse(String[] pattern) {
        Map<String, Integer> result = new HashMap<>();
        // fixed length
        for (String number : pattern) {
            if (number.length() == 2) {
                result.put(number, 1);
            } else if (number.length() == 3) {
                result.put(number, 7);
            } else if (number.length() == 4) {
                result.put(number, 4);
            } else if (number.length() == 7) {
                result.put(number, 8);
            }
        }

        Map<Integer, String> reverse = new HashMap<>();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            reverse.put(entry.getValue(), entry.getKey());
        }

        // needed to determine 5.
        String bd = subtract(reverse.get(4), reverse.get(1));

        for (String number : pattern) {
            if (number.length() == 5) {
                // 1: C F             | A B D E G | 2
                // 4: B C D F         | A E G     | 4
                // 7: A C F           | B D E G   | 3

                // 2: A C D E G       | B F       | 5
                // 3: A C D F G       | B E       | 5
                // 5: A B D F G       | C E       | 5
                if (subtract(number, reverse.get(1)).length() == 3) {
                    result.put(number, 3);
                } else if (subtract(number, bd).length() == 3) {
                    result.put(number, 5);
                } else {
                    result.put(number, 2);
                }
            } else if (number.length() == 6) {
                // 1: C F             | A B D E G | 2
                // 4: B C D F         | A E G     | 4
                // 7: A C F           | B D E G   | 3

                // 0: A B C E F G     | D         | 6
                // 6: A B D E F G     | C         | 6
                // 9: A B C D F G     | E         | 6
                if (subtract(number, reverse.get(1)).length() == 5) {
                    result.put(number, 6);
                } else if (subtract(number, reverse.get(4)).length() == 2) {
                    result.put(number, 9);
                } else {
                    result.put(number, 0);
                }
            }
        }
        return result;
    }

    // a - b. e.g: (4-1) BCDF - CF = BD
    String subtract(String a, String b) {
        String result = "";

        for (String aChar : a.split("")) {
            if (!b.contains(aChar)) {
                result += aChar;
            }
        }

        return result;
    }

    private String[] sortContent(String[] input) {
        String[] results = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            results[i] = sort(input[i]);
        }
        return results;
    }

    private String sort(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
