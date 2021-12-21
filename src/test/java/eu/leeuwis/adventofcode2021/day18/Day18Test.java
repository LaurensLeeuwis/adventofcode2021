package eu.leeuwis.adventofcode2021.day18;

import static eu.leeuwis.adventofcode2021.general.InputReader.readFile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Day18Test {

    @Test
    void mustSplit() {
        var code = new Day18();
        assertTrue(code.mustSplit(new Number(10)));
        assertTrue(code.mustSplit(new Number(11)));
        assertTrue(code.mustSplit(new Number(12)));
        assertFalse(code.mustSplit(new Number(9)));
        assertFalse(code.mustSplit(code.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")));
        assertTrue(code.mustSplit(code.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[10,7],[6,6]],[8,7]]]")));
        assertTrue(code.mustSplit(new Pair(new Pair(new Number(0), new Number(10)), new Number(3))));
        assertFalse(code.mustSplit(new Pair(new Pair(new Number(9), new Number(9)), new Number(3))));
    }

    @Test
    void split() {
        assertEquals(new Pair(new Number(5), new Number(5)), new Day18().split(new Number(10)));
        assertEquals(new Pair(new Number(5), new Number(6)), new Day18().split(new Number(11)));
        assertEquals(new Pair(new Number(6), new Number(6)), new Day18().split(new Number(12)));
        assertEquals(new Number(9), new Day18().split(new Number(9)));
        assertEquals(new Day18().parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[[5,5],7],[6,6]],[8,7]]]"), new Day18().split(new Day18().parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[10,7],[6,6]],[8,7]]]")));
        assertEquals(new Pair(new Pair(new Number(0), new Pair(new Number(5), new Number(5))), new Number(3)), new Day18().split(new Pair(new Pair(new Number(0), new Number(10)), new Number(3))));
        assertEquals(new Pair(new Pair(new Number(9), new Number(9)), new Number(3)), new Day18().split(new Pair(new Pair(new Number(9), new Number(9)), new Number(3))));
    }

    @Test
    void mustExplode() {
        var code = new Day18();
        assertTrue(code.mustExplode(code.parse("[[[[[9,8],1],2],3],4]")));
        assertTrue(code.mustExplode(code.parse("[7,[6,[5,[4,[3,2]]]]]")));
        assertTrue(code.mustExplode(code.parse("[[6,[5,[4,[3,2]]]],1]")));
        assertTrue(code.mustExplode(code.parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")));
        assertTrue(code.mustExplode(code.parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")));
        assertFalse(code.mustExplode(code.parse("[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]")));
    }

    @Test
    void explode() {
        var code = new Day18();
        assertEquals(code.parse("[[[[0,9],2],3],4]"), code.explode(code.parse("[[[[[9,8],1],2],3],4]")));
        assertEquals(code.parse("[7,[6,[5,[7,0]]]]"), code.explode(code.parse("[7,[6,[5,[4,[3,2]]]]]")));
        assertEquals(code.parse("[[6,[5,[7,0]]],3]"), code.explode(code.parse("[[6,[5,[4,[3,2]]]],1]")));
        assertEquals(code.parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"), code.explode(code.parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")));
        assertEquals(code.parse("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"), code.explode(code.parse("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")));
    }

    @Test
    void magnitude() {
        var code = new Day18();
        assertEquals(143, code.magnitude(code.parse("[[1,2],[[3,4],5]]")));
        assertEquals(1384, code.magnitude(code.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")));
        assertEquals(445, code.magnitude(code.parse("[[[[1,1],[2,2]],[3,3]],[4,4]]")));
        assertEquals(791, code.magnitude(code.parse("[[[[3,0],[5,3]],[4,4]],[5,5]]")));
        assertEquals(1137, code.magnitude(code.parse("[[[[5,0],[7,4]],[5,5]],[6,6]]")));
        assertEquals(3488, code.magnitude(code.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")));
    }

    @Test
    void puzzle1() {
        assertEquals(4140, new Day18().puzzle1(readFile(this, "testInput.txt")));
        assertEquals(3305, new Day18().puzzle1(readFile(this, "personalInput.txt")));
    }

    @Test
    void puzzle2() {
        assertEquals(3993, new Day18().puzzle2(readFile(this, "testInput.txt")));
        assertEquals(4563, new Day18().puzzle2(readFile(this, "personalInput.txt")));
    }

    @Test
    void add() {
        var code = new Day18();

        assertEquals(code.parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"), code.add(code.parse("[[[[4,3],4],4],[7,[[8,4],9]]]"), code.parse("[1,1]")));

        assertEquals(code.parse("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"), code.add(code.parse("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]"), code.parse("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")));
        assertEquals(code.parse("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]"), code.add(code.parse("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"), code.parse("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]")));
        assertEquals(code.parse("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]"), code.add(code.parse("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]"), code.parse("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]")));


        assertEquals(code.magnitude(code.parse("[[[[1,1],[2,2]],[3,3]],[4,4]]")), code.puzzle1(readFile(this, "small1.txt")));
        assertEquals(code.magnitude(code.parse("[[[[3,0],[5,3]],[4,4]],[5,5]]")), code.puzzle1(readFile(this, "small2.txt")));
        assertEquals(code.magnitude(code.parse("[[[[5,0],[7,4]],[5,5]],[6,6]]")), code.puzzle1(readFile(this, "small3.txt")));
        assertEquals(code.magnitude(code.parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")), code.puzzle1(readFile(this, "small4.txt")));


//  [[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]
//        + [7,[5,[[3,8],[1,4]]]]
//= [[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]
//
//  [[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]
//        + [[2,[2,2]],[8,[8,1]]]
//= [[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]
//
//  [[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]
//        + [2,9]
//= [[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]
//
//  [[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]
//        + [1,[[[9,3],9],[[9,0],[0,7]]]]
//= [[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]
//
//  [[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]
//        + [[[5,[7,4]],7],1]
//= [[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]
//
//  [[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]
//        + [[[[4,2],2],6],[8,7]]
//= [[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]





    }
}
