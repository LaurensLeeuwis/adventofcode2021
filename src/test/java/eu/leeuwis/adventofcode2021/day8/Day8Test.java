package eu.leeuwis.adventofcode2021.day8;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day8Test {

    @Test
    void puzzle1() {
        assertEquals(26, new Day8().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(504, new Day8().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(61229, new Day8().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(1073431, new Day8().puzzle2(readFile(this, "personalInput.txt")));
    }
}
