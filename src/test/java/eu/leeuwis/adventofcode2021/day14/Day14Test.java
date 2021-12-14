package eu.leeuwis.adventofcode2021.day14;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day14Test {

    @Test
    void puzzle1() {
        assertEquals(1588, new Day14().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(3408, new Day14().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(2188189693529L, new Day14().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(3724343376942L, new Day14().puzzle2(readFile(this, "personalInput.txt")));
    }

}
