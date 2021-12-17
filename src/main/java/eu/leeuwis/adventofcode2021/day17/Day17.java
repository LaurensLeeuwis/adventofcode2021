package eu.leeuwis.adventofcode2021.day17;

import java.util.List;
import java.util.regex.Pattern;

class Day17 {
    long puzzle1(List<String> input) {
        Box target = parseInput(input);

        int globalMaxHeight = 0;

        // still suboptimal, but good enough:
        // x -> from 1 (must go forward) to target.xEnd (otherwise it overshoots right away)
        // y -> from target.yEnd (otherwise it overshoots right away) to target.xEnd (otherwise it won't even go down before x runs out)

        for (int x = 1; x <= target.xEnd(); x++) {
            for (int y = target.yEnd(); y < target.xEnd(); y++) {
                Position position = new Position(0, 0);
                Velocity velocity = new Velocity(x, y);
                int localMaxHeight = 0;

                while (!position.inBox(target) && !position.overBox(target)) {
                    position = position.plus(velocity);
                    localMaxHeight = Math.max(localMaxHeight, position.y());
                    velocity = velocity.next();
                }
                if (position.inBox(target)) {
                    globalMaxHeight = Math.max(globalMaxHeight, localMaxHeight);
                }
            }
        }

        return globalMaxHeight;
    }

    public int puzzle2(List<String> input) {
        Box target = parseInput(input);

        int validInitials = 0;
        for (int x = 1; x <= target.xEnd(); x++) {
            for (int y = target.yEnd(); y < target.xEnd(); y++) {
                Position p = new Position(0, 0);
                Velocity v = new Velocity(x, y);

                while (!p.inBox(target) && !p.overBox(target)) {
                    p = p.plus(v);
                    v = v.next();
                }

                if (p.inBox(target)) {
                    validInitials++;
                }
            }
        }

        return validInitials;
    }

    private Box parseInput(List<String> input) {
        String toParse = input.get(0);
        String[] a = toParse.split(",");
        String[] x = a[0].split("=")[1].split(Pattern.quote(".."));
        String[] y = a[1].split("=")[1].split(Pattern.quote(".."));
        int xStart = Integer.parseInt(x[0]);
        int yStart = Integer.parseInt(y[0]);
        int xEnd = Integer.parseInt(x[1]);
        int yEnd = Integer.parseInt(y[1]);

        return new Box(Math.min(xStart, xEnd), Math.max(yStart, yEnd), Math.max(xStart, xEnd), Math.min(yStart, yEnd));
    }
}
