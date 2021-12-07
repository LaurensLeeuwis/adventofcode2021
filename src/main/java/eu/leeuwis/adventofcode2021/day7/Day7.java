package eu.leeuwis.adventofcode2021.day7;

import java.util.Arrays;
import java.util.stream.IntStream;

class Day7 {

    public int fuelToCheapestLinearPosition(String puzzleInput) {
        int[] crabPositions = parseCrabPositions(puzzleInput);

        // I might've been lucky here ;)
        int meanPosition = crabPositions[crabPositions.length / 2];
        return Arrays.stream(crabPositions).map(it -> Math.abs(it - meanPosition)).sum();
    }

    public int fuelToCheapestTriangularPosition(String input) {
        int[] crabPositions = parseCrabPositions(input);

        return IntStream.rangeClosed(crabPositions[0], crabPositions[crabPositions.length - 1])
                .map(destination -> calculateFuelForAllCrabs(destination, crabPositions))
                .min()
                .getAsInt();
    }

    private int[] parseCrabPositions(String puzzleInput) {
        String[] inputs = puzzleInput.split(",");
        return Arrays.stream(inputs).mapToInt(Integer::parseInt).sorted().toArray();
    }

    private int calculateFuelForAllCrabs(int destination, int[] crabPositions) {
        return Arrays.stream(crabPositions)
                .map(position -> calculateFuelForCrab(destination, position))
                .sum();
    }

    private int calculateFuelForCrab(int destination, int crabPosition) {
        int n = Math.abs(destination - crabPosition);
        return (int) ((Math.pow(n, 2) + n)/2);
    }

}
