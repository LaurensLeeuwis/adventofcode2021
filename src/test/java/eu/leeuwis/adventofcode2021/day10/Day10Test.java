package eu.leeuwis.adventofcode2021.day10;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day10Test {

    @Test
    void puzzle1() {
        assertEquals(26397, new Day10().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(370407, new Day10().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(288957, new Day10().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(3249889609L, new Day10().puzzle2(readFile(this, "personalInput.txt")));
    }
}
