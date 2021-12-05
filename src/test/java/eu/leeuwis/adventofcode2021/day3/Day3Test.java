package eu.leeuwis.adventofcode2021.day3;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day3Test {

    @Test
    void powerConsumption() {
        assertEquals(198, new Day3().powerConsumption(readFile(this, "testInput.txt")));
        assertEquals(1082324, new Day3().powerConsumption(readFile(this, "personalInput.txt")));
    }

    @Test
    void lifeSupportRating() {
        assertEquals(230, new Day3().lifeSupportRating(readFile(this, "testInput.txt")));
        assertEquals(1353024, new Day3().lifeSupportRating(readFile(this, "personalInput.txt")));
    }
}
