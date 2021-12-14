package eu.leeuwis.adventofcode2021.day14;

record Pair(char[] input, char[] output) {
    boolean matches(char[] pair) {
        return input[0] == pair[0] && input[1] == pair[1];
    }
}
