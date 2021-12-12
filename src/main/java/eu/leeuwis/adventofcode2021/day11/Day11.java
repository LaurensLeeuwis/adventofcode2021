package eu.leeuwis.adventofcode2021.day11;

import java.util.List;

class Day11 {

    long puzzle1(List<String> lines) {
        Field field = new Field(lines);
        return field.flashesAfterSteps(100);
    }

    public long puzzle2(List<String> lines) {
        Field field = new Field(lines);
        return field.allFlashAfter();
    }


}
