package eu.leeuwis.adventofcode2021.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Day15 {
    int[][] field;
    long[][] minRisk;
    int[] table = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Coord end;

    long puzzle1(List<String> input) {
        end = new Coord(input.size() - 1, input.get(0).length() - 1);
        field = new int[end.x() + 1][end.y() + 1];
        minRisk = new long[end.x() + 1][end.y() + 1];

        for (int x = 0; x <= end.x(); x++) {
            for (int y = 0; y <= end.y(); y++) {
                String nr = Character.toString(input.get(x).charAt(y));
                field[x][y] = Integer.parseInt(nr);
                minRisk[x][y] = Long.MAX_VALUE;
            }
        }

        return getMinRisk();
    }

    public long puzzle2(List<String> input) {
        int xSize = input.size();
        int ySize = input.get(0).length();

        end = new Coord((xSize * 5) - 1, (ySize * 5) - 1);
        field = new int[end.x() + 1][end.y() + 1];
        minRisk = new long[end.x() + 1][end.y() + 1];

        for (int xR = 0; xR < 5; xR++) {
            for (int yR = 0; yR < 5; yR++) {
                for (int x = 0; x <= xSize - 1; x++) {
                    for (int y = 0; y <= ySize - 1; y++) {
                        String nr = Character.toString(input.get(x).charAt(y));
                        field[xR * xSize + x][yR * ySize + y] = table[Integer.parseInt(nr) + xR + yR];
                        minRisk[xR * xSize + x][yR * ySize + y] = Long.MAX_VALUE;
                    }
                }
            }
        }

        return getMinRisk();
    }

    private Long getMinRisk() {
        List<Coord> hit = new ArrayList<>();
        hit.add(new Coord(0, 0));
        minRisk[0][0] = 0L;

        while (!hit.isEmpty()) {
            List<Coord> newHit = new ArrayList<>();

            for (Coord coord : hit) {
                List<Coord> neighbors = getNeighbors(coord);
                for (Coord neighbor : neighbors) {
                    long currentRisk = minRisk[neighbor.x()][neighbor.y()];
                    long newRisk = minRisk[coord.x()][coord.y()] + field[neighbor.x()][neighbor.y()];

                    if (newRisk < currentRisk) {
                        minRisk[neighbor.x()][neighbor.y()] = newRisk;
                        newHit.add(neighbor);
                    }
                }
            }

            hit = newHit;
        }

        return minRisk[end.x()][end.y()];
    }

    private List<Coord> getNeighbors(Coord coord) {
        return Stream.of(new Coord(coord.x() + 1, coord.y()),
                new Coord(coord.x(), coord.y() + 1),
                new Coord(coord.x() - 1, coord.y()),
                new Coord(coord.x(), coord.y() - 1))
                .filter(c -> c.x() >= 0)
                .filter(c -> c.y() >= 0)
                .filter(c -> c.x() <= end.x())
                .filter(c -> c.y() <= end.y())
                .toList();
    }
}
