package eu.leeuwis.adventofcode2021.day12;

import java.util.List;

class Day12{

    long puzzle1(List<String> input) {
        Edges edges = readEdges(input);
        List<List<Cave>> paths = edges.getAllPathsFromStartToFinish();

        return paths.size();
    }

    long puzzle2(List<String> input) {
        Edges edges = readEdges(input);
        List<List<Cave>> paths = edges.getAllPathsFromStartToFinish2();

        return paths.size();
    }

    private Edges readEdges(List<String> lines) {
        Edges edges = new Edges();
        for (String line : lines) {
            String[] caves = line.split("-");
            edges.add(caves[0], caves[1]);
        }
        return edges;
    }
}
