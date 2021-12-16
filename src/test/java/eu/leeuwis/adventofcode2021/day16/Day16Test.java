package eu.leeuwis.adventofcode2021.day16;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day16Test {

    @Test
    void puzzle1() {
        assertEquals(16, new Day16().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(12, new Day16().puzzle1(readFile(this, "testInput2.txt")));
        assertEquals(23, new Day16().puzzle1(readFile(this, "testInput3.txt")));
        assertEquals(31, new Day16().puzzle1(readFile(this, "testInput4.txt")));
        assertEquals(1012, new Day16().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(3, new Day16().puzzle2(readFile(this, "testInput2_1.txt")));
        assertEquals(54, new Day16().puzzle2(readFile(this, "testInput2_2.txt")));
        assertEquals(7, new Day16().puzzle2(readFile(this, "testInput2_3.txt")));
        assertEquals(9, new Day16().puzzle2(readFile(this, "testInput2_4.txt")));
        assertEquals(1, new Day16().puzzle2(readFile(this, "testInput2_5.txt")));
        assertEquals(0, new Day16().puzzle2(readFile(this, "testInput2_6.txt")));
        assertEquals(0, new Day16().puzzle2(readFile(this, "testInput2_7.txt")));
        assertEquals(1, new Day16().puzzle2(readFile(this, "testInput2_8.txt")));
        assertEquals(2223947372407L, new Day16().puzzle2(readFile(this, "personalInput.txt")));
    }

}
