package eu.leeuwis.adventofcode2021.day4;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day4Test {

    @Test
    void puzzle1() {
        assertEquals(4512, new Day4().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(51034, new Day4().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(1924, new Day4().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(5434, new Day4().puzzle2(readFile(this, "personalInput.txt")));
    }
}
