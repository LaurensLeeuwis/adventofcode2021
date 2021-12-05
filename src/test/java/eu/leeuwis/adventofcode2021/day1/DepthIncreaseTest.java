package eu.leeuwis.adventofcode2021.day1;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepthIncreaseTest {

    @Test
    void numberOfIncreases() {
        assertEquals(7, new DepthIncrease().numberOfIncreases(readFile(this, "testInput.txt")));
        assertEquals(1752, new DepthIncrease().numberOfIncreases(readFile(this, "personalInput.txt")));
    }

    @Test
    void numberOfSlidingWindowIncreases() {
        assertEquals(5, new DepthIncrease().numberOfSlidingWindowIncreases(readFile(this, "testInput.txt")));
        assertEquals(1781, new DepthIncrease().numberOfSlidingWindowIncreases(readFile(this, "personalInput.txt")));
    }
}
