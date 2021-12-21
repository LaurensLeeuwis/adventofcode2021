package eu.leeuwis.adventofcode2021.day19;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day19Test {

    @Test
    void puzzle1() {
        assertEquals(79, new Day19().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(459, new Day19().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(3621, new Day19().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(19130, new Day19().puzzle2(readFile(this, "personalInput.txt")));
    }
}
