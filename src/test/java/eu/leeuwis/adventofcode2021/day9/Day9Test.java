package eu.leeuwis.adventofcode2021.day9;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day9Test {

    @Test
    void puzzle1() {
        assertEquals(15, new Day9().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(600, new Day9().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(1134, new Day9().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(987840, new Day9().puzzle2(readFile(this, "personalInput.txt")));
    }
}
