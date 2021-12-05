package eu.leeuwis.adventofcode2021.day5;

import java.util.List;

class Day5 {

    long puzzle1(List<String> lines) {
        Field field = new Field();

        lines.stream()
                .map(Line::new)
                .filter(line -> line.isHorizontal() || line.isVertical())
                .forEach(field::apply);

        return field.numberOfPointsWithAtLeast2Overlaps();
    }

    long puzzle2(List<String> lines) {
        Field field = new Field();

        lines.stream()
                .map(Line::new)
                .forEach(field::apply);

        return field.numberOfPointsWithAtLeast2Overlaps();
    }


}
