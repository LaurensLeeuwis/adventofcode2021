package eu.leeuwis.adventofcode2021.day11;

import java.util.List;

class Field {

    private final int[][] field;
    private final int xSize;
    private final int ySize;
    private int flashes;

    Field(List<String> input){
        xSize = input.size();
        ySize = input.get(0).length();

        field = new int[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                field[x][y] = Integer.parseInt(Character.toString(input.get(x).charAt(y)));
            }
        }
    }

    long flashesAfterSteps(int nrOfSteps){
        for (int i = 1; i <= nrOfSteps; i++) {
            makeStep();
        }

        return flashes;
    }

    int allFlashAfter(){
        int steps = 0;
        while(fieldSum() != 0){
            makeStep();
            steps++;
        }
        return steps;
    }

    private long fieldSum() {
        long sum = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                sum += field[x][y];
            }
        }
        return sum;
    }


    void makeStep(){
        boolean[][] flashed = new boolean[xSize][ySize];

        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++) {
                field[x][y]++;
            }
        }

        boolean condition = true;
        while(condition){
            condition = false;
            for (int x = 0; x < xSize; x++){
                for (int y = 0; y < ySize; y++) {
                    if (field[x][y] > 9 && !flashed[x][y]){
                        flash(x,y);
                        flashed[x][y] = true;
                        flashes++;
                        condition = true;
                    }
                }
            }
        }

        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++) {
                if (field[x][y] > 9){
                    field[x][y] = 0;
                }
            }
        }

//        for (int x = 0; x < xSize; x++){
//            for (int y = 0; y < ySize; y++) {
//                System.out.print(field[x][y]);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private void flash(int x, int y) {
        for (int xNeigh = -1; xNeigh <= 1; xNeigh++) {
            for (int yNeigh = -1; yNeigh <= 1; yNeigh++) {
                up(x+xNeigh, y+yNeigh);
            }
        }
    }

    private void up(int x, int y) {
        if (x >= 0 && x < xSize && y >= 0 && y < ySize){
            field[x][y]++;
        }
    }


//    private static record Point(int x, int y){};
//
//    private List<Point> getNeighbors(Point point) {
//        List<Point> result = new ArrayList<>();
//
//        if (point.x != xSize) {
//            result.add(new Point(point.x + 1, point.y));
//        }
//        if (point.y != ySize) {
//            result.add(new Point(point.x, point.y + 1));
//        }
//        if (point.x != 0) {
//            result.add(new Point(point.x - 1, point.y));
//        }
//        if (point.y != 0) {
//            result.add(new Point(point.x, point.y - 1));
//        }
//        return result;
//    }





}
