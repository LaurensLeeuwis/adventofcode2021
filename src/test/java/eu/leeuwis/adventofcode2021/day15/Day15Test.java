package eu.leeuwis.adventofcode2021.day15;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day15Test {

    @Test
    void puzzle1() {
        assertEquals(40, new Day15().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(613, new Day15().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(315, new Day15().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(2899, new Day15().puzzle2(readFile(this, "personalInput.txt")));
    }
}
