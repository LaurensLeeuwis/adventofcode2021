package eu.leeuwis.adventofcode2021.day17;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class  Day17Test {

    @Test
    void puzzle1() {
        assertEquals(45, new Day17().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(9730, new Day17().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(112, new Day17().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(4110, new Day17().puzzle2(readFile(this, "personalInput.txt")));
    }

}
