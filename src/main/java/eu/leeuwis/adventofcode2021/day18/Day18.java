package eu.leeuwis.adventofcode2021.day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Day18 {

    long puzzle1(List<String> input) {
        List<Pair> parsed = parse(input);
        Pair result = parsed.get(0);

        for (int i = 1; i < parsed.size(); i++) {
            result = add(result, parsed.get(i));
        }


        return magnitude(result);
    }

    long puzzle2(List<String> input) {
        List<Pair> parsed = parse(input);
        long max = 0;

        for (int i = 0; i < parsed.size(); i++) {
            for (int j = 0; j < parsed.size(); j++) {
                if (i != j){
                    Pair a = parsed.get(i);
                    Pair b = parsed.get(j);
                    long ab = magnitude(add(a, b));
                    long ba = magnitude(add(b,a));

                    if (ab > max){
                        max = ab;
                    }
                    if (ba > max){
                        max = ba;
                    }

                }
            }
        }


        return max;
    }



    long magnitude(Pair pair) {
        return 3 * magnitude(pair.left()) + 2 * magnitude(pair.right());
    }

    private long magnitude(PairElement element) {
        if (element instanceof Pair) {
            return magnitude((Pair) element);
        } else if (element instanceof Number) {
            return magnitude((Number) element);
        }

        return 0;
    }

    boolean mustSplit(PairElement element) {
        if (element instanceof Pair) {
            return mustSplit(((Pair) element).left()) || mustSplit(((Pair) element).right());
        } else if (element instanceof Number) {
            return ((Number) element).number() > 9;
        }
        return false;
    }

    boolean isSplitState = false;

    PairElement split(PairElement element) {
        if (element instanceof Pair) {
            return split((Pair) element);
        } else if (element instanceof Number) {
            return split((Number) element);
        }

        return null;
    }

    Pair split(Pair pair) {
        if (isSplitState){
            return pair;
        }

        return new Pair(split(pair.left()), split(pair.right()));
    }

    PairElement split(Number number) {
        if (number.number() <= 9 || isSplitState) {
            return number;
        }
        isSplitState = true;
        int left = Math.floorDiv(number.number(), 2);
        int right = number.number() - left;
        return new Pair(new Number(left), new Number(right));
    }


    long magnitude(Number number) {
        return number.number();
    }

     Pair add(Pair left, Pair right) {
        Pair result = new Pair(left, right);

        while (mustExplode(result) || mustSplit(result)){
            while(mustExplode(result)){
                result = explode(result);
            }
            if(mustSplit(result)){
                isSplitState = false;
                result = split(result);
            }
        }
        return result;
    }

    private List<Pair> parse(List<String> input) {
        List<Pair> result = new ArrayList<>();
        for (String line : input) {
            result.add(parse(line));
        }
        return Collections.unmodifiableList(result);
    }

    Pair parse(String line) {
        Stack<PairElement> stack = new Stack<>();
        String numberString = "";
        boolean isNumber = false;

        for (char aChar : line.toCharArray()) {
            if (aChar != ']' && aChar != '[' && aChar != ',') {
                isNumber = true;
            }

            if (aChar == ']' || aChar == '[' || aChar == ',') {
                if (isNumber) {
                    Number number = new Number(Integer.parseInt(numberString));
                    stack.push(number);
                }

                isNumber = false;
                numberString = "";
            }

            if (aChar == ']') {
                PairElement a = stack.pop();
                PairElement b = stack.pop();
                Pair pair = new Pair(b, a);
                stack.push(pair);
            } else if (aChar != ',' && aChar != '[') {
                numberString = numberString + aChar;
            }
        }
        return (Pair) stack.pop();
    }

    String toString(PairElement element) {
        if (element instanceof Number) {
            return "" + ((Number) element).number();
        } else if (element instanceof Pair) {
            return "[" + toString(((Pair) element).left()) + "," + toString(((Pair) element).right()) + "]";
        }
        return null;
    }

    // [[[[[9,8],1],2],3],4] becomes [[[[0,9],2],3],4] (the 9 has no regular number to its left, so it is not added to any regular number).
    // [7,[6,[5,[4,[3,2]]]]] becomes [7,[6,[5,[7,0]]]] (the 2 has no regular number to its right, and so it is not added to any regular number).
    // [[6,[5,[4,[3,2]]]],1] becomes [[6,[5,[7,0]]],3].
    // [[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]] becomes [[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]] (the pair [3,2] is unaffected because the pair [7,3] is further to the left; [3,2] would explode on the next action).
    // [[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]] becomes [[3,[2,[8,0]]],[9,[5,[7,0]]]].

    Pair explode(Pair x) {
        String line = toString(x);

        Stack<PairElement> stack = new Stack<>();
        int level = 0;
        String numberString = "";
        boolean isNumber = false;
        int left = -1;
        int right = -1;
        int pos = -1;

        char[] chars = line.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            pos = i;
            char aChar = chars[i];

            if (aChar != ']' && aChar != '[' && aChar != ',') {
                isNumber = true;
            }

            if (aChar == ']' || aChar == '[' || aChar == ',') {
                if (isNumber) {
                    if (level > 4) {
                        if (left == -1) {
                            left = Integer.parseInt(numberString);
                        } else if (right == -1) {
                            right = Integer.parseInt(numberString);
                            break;
                        }
                    }

                    Number number = new Number(Integer.parseInt(numberString));
                    stack.push(number);
                }

                isNumber = false;
                numberString = "";
            }

            if (aChar == '[') {
                level++;
                left = -1;
            } else if (aChar == ']') {
                level--;
            }

            if (aChar == ']') {
                PairElement a = stack.pop();
                PairElement b = stack.pop();
                Pair pair = new Pair(b, a);
                stack.push(pair);
            } else if (aChar != ',' && aChar != '[') {
                numberString = numberString + aChar;
            }
        }

        String leftString = line.substring(0, pos);
        String leftStringReverse = reverse(leftString);
        int leftX = leftStringReverse.indexOf('[');
        leftString = line.substring(0, pos - leftX - 1);
        String rightString = line.substring(pos + 1);

        if (hasNumber(leftString)) {
            leftString = leftAddNumber(leftString, left);
        }

        if (hasNumber(rightString)) {
            rightString = rightAddNumber(rightString, right);
        }

        if (leftString.endsWith(",")) {
            leftString += "0";
        }

        if (rightString.startsWith(",")) {
            rightString = "0" + rightString;
        }

        return parse(leftString + rightString);
    }

    private String rightAddNumber(String rightString, int right) {
        int i = 0;
        char[] rightChars = rightString.toCharArray();
        String number = "";

        while (i < rightChars.length) {
            char aChar = rightChars[i];

            if (aChar != '[' && aChar != ']' && aChar != ',') {
                number = number + aChar;
            } else {
                if (!number.isEmpty()) {
                    int result = Integer.parseInt(number) + right;

                    return rightString.substring(0, i - number.length()) + result + rightString.substring(i);
                }
            }
            i++;
        }

        return rightString;
    }

    private String leftAddNumber(String leftString, int left) {
        char[] leftChars = leftString.toCharArray();
        int i = leftChars.length-1;
        String number = "";

        while (i >= 0) {
            char aChar = leftChars[i];

            if (aChar != '[' && aChar != ']' && aChar != ',') {
                number = aChar + number;
            } else {
                if (!number.isEmpty()) {
                    int result = Integer.parseInt(number) + left;

                    return leftString.substring(0, i + 1) + result + leftString.substring(i + number.length()+1);
                }
            }
            i--;
        }

        return leftString;
    }

    private boolean hasNumber(String leftString) {
        for (char c : leftString.toCharArray()) {
            if (c != ']' && c != '[' && c != ',') {
                return true;
            }
        }
        return false;
    }

    String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }


    public boolean mustExplode(Pair pair) {
        return mustExplode(pair, 0);
    }

    boolean mustExplode(PairElement element, int level) {
        if (element instanceof Pair) {
            return mustExplode(((Pair) element).left(), level + 1) || mustExplode(((Pair) element).right(), level + 1);
        } else if (element instanceof Number) {
            return level > 4;
        }
        return false;
    }

}
