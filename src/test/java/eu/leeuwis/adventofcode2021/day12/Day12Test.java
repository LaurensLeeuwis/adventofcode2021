package eu.leeuwis.adventofcode2021.day12;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class Day12Test {

    @Test
    void puzzle1() {
        assertEquals(10, new Day12().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(19, new Day12().puzzle1(readFile(this, "testInput2.txt")));
        assertEquals(226, new Day12().puzzle1(readFile(this, "testInput3.txt")));
        assertEquals(4720, new Day12().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(36, new Day12().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(103, new Day12().puzzle2(readFile(this, "testInput2.txt")));
        assertEquals(3509, new Day12().puzzle2(readFile(this, "testInput3.txt")));
        assertEquals(147848, new Day12().puzzle2(readFile(this, "personalInput.txt")));
    }
}
