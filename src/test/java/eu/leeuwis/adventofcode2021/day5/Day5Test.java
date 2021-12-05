package eu.leeuwis.adventofcode2021.day5;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day5Test {

    @Test
    void puzzle1() {
        assertEquals(5, new Day5().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(5084, new Day5().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(12, new Day5().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(17882, new Day5().puzzle2(readFile(this, "personalInput.txt")));
    }
}
