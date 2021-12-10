package eu.leeuwis.adventofcode2021.day10;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class Day10 {

    long puzzle1(List<String> lines) {
        long score = 0;

        for (String line : lines) {
            Character c = getFirstFaultyChar(line);
            // correct lines return null
            if (c != null) {
                if (c == ')') {
                    score += 3;
                } else if (c == ']') {
                    score += 57;
                } else if (c == '}') {
                    score += 1197;
                } else if (c == '>') {
                    score += 25137;
                }
            }
        }
        return score;
    }

    public long puzzle2(List<String> lines) {
        List<Long> sortedLineScores = lines.stream()
                .filter(line -> getFirstFaultyChar(line) == null)
                .map(this::getScore)
                .sorted()
                .collect(Collectors.toUnmodifiableList());

        int middle = (int) Math.ceil(sortedLineScores.size() / 2);

        return sortedLineScores.get(middle);
    }

    private Long getScore(String line) {
        long score = 0;

        // invalid lines are already filtered
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (isOpen(c)) {
                stack.push(c);
            } else if (isClose(c)) {
                stack.pop();
            }
        }

        while (!stack.empty()) {
            char open = stack.pop();

            if (open == '(') {
                score = score * 5 + 1;
            } else if (open == '[') {
                score = score * 5 + 2;
            } else if (open == '{') {
                score = score * 5 + 3;
            } else if (open == '<') {
                score = score * 5 + 4;
            }
        }

        return score;
    }

    private Character getFirstFaultyChar(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (isOpen(c)) {
                stack.push(c);
            } else if (isClose(c)) {
                char open = stack.pop();
                if (!isCorrectClose(open, c)) {
                    return c;
                }
            }
        }
        return null;
    }

    private boolean isClose(char c) {
        return c == ')' || c == '>' || c == ']' || c == '}';
    }

    private boolean isOpen(char c) {
        return c == '(' || c == '<' || c == '[' || c == '{';
    }

    private boolean isCorrectClose(char open, char close) {
        return ((open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '<' && close == '>'));

    }
}
