package eu.leeuwis.adventofcode2021.day13;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Day13Test {

    @Test
    void puzzle1() {
        assertEquals(17, new Day13().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(775, new Day13().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        String testInputResult = """
                #####
                #...#
                #...#
                #...#
                #####
                .....
                .....
                """;

        String personalInput = """
                ###..####.#..#.###..#..#.###..#..#.###..
                #..#.#....#..#.#..#.#..#.#..#.#.#..#..#.
                #..#.###..#..#.#..#.#..#.#..#.##...#..#.
                ###..#....#..#.###..#..#.###..#.#..###..
                #.#..#....#..#.#....#..#.#....#.#..#.#..
                #..#.####..##..#.....##..#....#..#.#..#.
                """;

        assertEquals(testInputResult, new Day13().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(personalInput, new Day13().puzzle2(readFile(this, "personalInput.txt")));
    }
}
