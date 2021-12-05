package eu.leeuwis.adventofcode2021.day2;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day2Test {

    @Test
    void puzzle1() {
        assertEquals(150, new Day2().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(1459206, new Day2().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(900, new Day2().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(1320534480, new Day2().puzzle2(readFile(this, "personalInput.txt")));
    }
}
