package eu.leeuwis.adventofcode2021.day2;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    long puzzle1(List<String> allMovements) {
        Position position = Position.startPosition();

        List<Movement> movements = allMovements.stream()
                .map(this::fromString)
                .collect(Collectors.toUnmodifiableList());

        // I wanted to do this with a reduction, but doesn't seem to work.
        for (Movement movement : movements) {
            position = position.newPosition(movement);
        }

        return position.getMultiple();
    }

    long puzzle2(List<String> allMovements) {
        Position2 position = Position2.startPosition();

        List<Movement> movements = allMovements.stream()
                .map(this::fromString)
                .collect(Collectors.toUnmodifiableList());

        // I wanted to do this with a reduction, but doesn't seem to work.
        for (Movement movement : movements) {
            position = position.newPosition(movement);
        }

        return position.getMultiple();
    }


    private Movement fromString(String input) {
        String[] parts = input.split(" ");

        Direction direction = Direction.fromString(parts[0]);
        long units = Long.parseLong(parts[1]);

        return new Movement(direction, units);
    }
}
