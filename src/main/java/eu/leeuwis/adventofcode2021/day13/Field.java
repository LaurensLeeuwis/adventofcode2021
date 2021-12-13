package eu.leeuwis.adventofcode2021.day13;

class Field {
    private boolean[][] field;
    int maxX;
    int maxY;

    Field(int maxX, int maxY) {
        this.field = new boolean[maxX + 1][maxY + 1];
        this.maxX = maxX + 1;
        this.maxY = maxY + 1;
    }

    void addDot(Dot dot) {
        field[dot.x()][dot.y()] = true;
    }

    String draw() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < maxY; y++) {

            for (int x = 0; x < maxX; x++) {
                result.append(field[x][y] ? "#" : ".");
            }
            result.append("\n");
        }
        return result.toString();
    }

    void foldX(int x) {
        boolean[][] newField = new boolean[x][maxY];

        for (int x1 = 0; x1 < x; x1++) {
            System.arraycopy(field[x1], 0, newField[x1], 0, maxY);
        }

        for (int x1 = 1; x1 <= x; x1++) {
            for (int y1 = 0; y1 < maxY; y1++) {
                if (x - x1 >= 0 && x1 + x < maxX) {
                    newField[x - x1][y1] = newField[x - x1][y1] || field[x1 + x][y1];
                }
            }
        }

        this.field = newField;
        this.maxX = x;
    }

    void foldY(int y) {
        boolean[][] newField = new boolean[maxX][y];

        for (int x1 = 0; x1 < maxX; x1++) {
            System.arraycopy(field[x1], 0, newField[x1], 0, y);
        }

        for (int x1 = 0; x1 < maxX; x1++) {
            for (int y1 = 1; y1 <= y; y1++) {
                if (y - y1 >= 0 && y1 + y < maxY) {
                    newField[x1][y - y1] = newField[x1][y - y1] || field[x1][y1 + y];
                }
            }
        }

        this.field = newField;
        this.maxY = y;
    }

    long nrOfDots() {
        long result = 0;

        for (boolean[] row : field) {
            for (boolean cell : row) {
                if (cell) {
                    result++;
                }
            }
        }
        return result;
    }
}
