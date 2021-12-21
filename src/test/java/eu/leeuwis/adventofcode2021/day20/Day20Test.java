package eu.leeuwis.adventofcode2021.day20;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day20Test {

    @Test
    void puzzle1() {
        assertEquals(35, new Day20().puzzle(readFile(this, "testInput.txt"), 2));
        assertEquals(5347, new Day20().puzzle(readFile(this, "personalInput.txt"), 2));
    }

    @Test
    void puzzle2() {
        assertEquals(3351, new Day20().puzzle(readFile(this, "testInput.txt"), 50));
        assertEquals(17172, new Day20().puzzle(readFile(this, "personalInput.txt"), 50));
    }
}
