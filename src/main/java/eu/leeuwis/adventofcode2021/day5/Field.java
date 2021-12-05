package eu.leeuwis.adventofcode2021.day5;

import java.util.HashMap;
import java.util.Map;

class Field {

    private final Map<Coordinate, Integer> vents = new HashMap<>();

    void apply(Line line) {
        if (line.isVertical() || line.isHorizontal()){
            int minX = Math.min(line.getFromX(), line.getToX());
            int maxX = Math.max(line.getFromX(), line.getToX());
            int minY = Math.min(line.getFromY(), line.getToY());
            int maxY = Math.max(line.getFromY(), line.getToY());

            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    Coordinate coordinate = new Coordinate(x,y);
                    int current = vents.getOrDefault(coordinate, 0);
                    vents.put(coordinate, current+1);
                }
            }
        } else {
            int x = line.getFromX();
            int y = line.getFromY();
            int xDirection = line.getFromX() > line.getToX() ? -1 : 1;
            int yDirection = line.getFromY() > line.getToY() ? -1 : 1;

//            for (int x = line.getFromX(); x <= line.getToX(); x+= xDirection) {
//                for (int y = line.getFromY(); x <= line.getToY(); y+= yDirection) {
//                    Coordinate coordinate = new Coordinate(x,y);
//                    int current = vents.getOrDefault(coordinate, 0);
//                    vents.put(coordinate, current+1);
//                }
//            }


            while (x != line.getToX() && y != line.getToY()){
                Coordinate coordinate = new Coordinate(x,y);
                int current = vents.getOrDefault(coordinate, 0);
                vents.put(coordinate, current+1);
                x+= xDirection;
                y+= yDirection;
            }
            Coordinate coordinate = new Coordinate(line.getToX(),line.getToY());
            int current = vents.getOrDefault(coordinate, 0);
            vents.put(coordinate, current+1);


        }
//        System.out.println(line);
//        drawMap();
    }

    private record Coordinate(int x, int y){}

    long numberOfPointsWithAtLeast2Overlaps() {
        return vents.values().stream().filter(value -> value >= 2).count();
    }

    void drawMap(){
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coordinate = new Coordinate(y,x);
                int value = vents.getOrDefault(coordinate, 0);
                System.out.print(value == 0? "." : value);
            }
            System.out.println();
        }
        System.out.println();

    }
}
