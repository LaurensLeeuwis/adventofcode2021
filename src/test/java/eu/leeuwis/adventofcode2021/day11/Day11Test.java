package eu.leeuwis.adventofcode2021.day11;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day11Test {

    @Test
    void puzzle1() {
        assertEquals(1656, new Day11().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(1615, new Day11().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(195, new Day11().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(249, new Day11().puzzle2(readFile(this, "personalInput.txt")));
    }
}
