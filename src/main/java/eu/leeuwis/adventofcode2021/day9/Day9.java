package eu.leeuwis.adventofcode2021.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day9 {
    long puzzle1(List<String> input) {
        int[][] field = createField(input);
        var lowPoints = getAreaLowPoints(field);

        return lowPoints.stream()
                .map(point -> field[point.i][point.j])
                .map(height -> height + 1)
                .reduce(0, Integer::sum);
    }

    long puzzle2(List<String> input) {
        int[][] field = createField(input);
        var lowPoints = getAreaLowPoints(field);

        return lowPoints.stream()
                .map(point -> basinSize(point, field))
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }

    private record Point(int i, int j) { }

    private int[][] createField(List<String> input) {
        int lineLength = input.get(0).length();
        int[][] field = new int[input.size()][lineLength];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < lineLength; j++) {
                field[i][j] = Integer.parseInt(Character.toString(input.get(i).charAt(j)));
            }
        }
        return field;
    }

    private List<Point> getAreaLowPoints(int[][] field) {
        List<Point> result = new ArrayList<>();
        Point maxPoint = new Point(field.length - 1, field[0].length - 1);

        for (int i = 0; i <= maxPoint.i; i++) {
            for (int j = 0; j <= maxPoint.j; j++) {
                Point point = new Point(i, j);
                int height = field[i][j];
                List<Point> neighbors = getNeighbors(point, maxPoint);

                boolean lowest = neighbors.stream()
                        .map(neighbor -> field[neighbor.i][neighbor.j])
                        .allMatch(neighborHeight -> height < neighborHeight);

                if (lowest) {
                    result.add(new Point(i, j));
                }
            }
        }
        return result;
    }

    private int basinSize(Point deepestPoint, int[][] field) {
        Point maxPoint = new Point(field.length - 1, field[0].length - 1);

        Map<Point, Boolean> evaluated = new HashMap<>();
        evaluated.put(deepestPoint, false);
        Map<Point, Boolean> evaluatedCopy = new HashMap<>(evaluated);

        while (evaluatedCopy.containsValue(false)) {
            evaluatedCopy.entrySet().stream()
                    .filter(entry -> !entry.getValue())
                    .map(Map.Entry::getKey)
                    .forEach(point -> {
                        evaluated.put(point, true);
                        int height = field[point.i][point.j];

                        getNeighbors(point, maxPoint).stream()
                                .filter(neighbor -> !evaluated.containsKey(neighbor))
                                .filter(neighbor -> field[neighbor.i][neighbor.j] != 9)
                                .filter(neighbor -> field[neighbor.i][neighbor.j] >= height)
                                .forEach(neighbor -> evaluated.put(neighbor, false));
                    });

            evaluatedCopy.putAll(evaluated);
        }
        return evaluated.size();
    }

    private List<Point> getNeighbors(Point point, Point maxPoint) {
        List<Point> result = new ArrayList<>();

        if (point.i != maxPoint.i) {
            result.add(new Point(point.i + 1, point.j));
        }
        if (point.j != maxPoint.j) {
            result.add(new Point(point.i, point.j + 1));
        }
        if (point.i != 0) {
            result.add(new Point(point.i - 1, point.j));
        }
        if (point.j != 0) {
            result.add(new Point(point.i, point.j - 1));
        }
        return result;
    }
}
