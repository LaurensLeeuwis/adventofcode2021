package eu.leeuwis.adventofcode2021.day21;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day21Test {

    @Test
    void puzzle1() {
        assertEquals(739785, new Day21().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(720750, new Day21().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(444356092776315L, new Day21().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(275067741811212L, new Day21().puzzle2(readFile(this, "personalInput.txt")));
    }
}
