package eu.leeuwis.adventofcode2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

class Edges {
    private final List<Edge> edges = new ArrayList<>();

    void add(String caveA, String caveB) {
        Cave a = new Cave(caveA);
        Cave b = new Cave(caveB);
        edges.add(new Edge(a, b));
    }

    List<List<Cave>> getAllPathsFromStartToFinish() {
        Cave from = new Cave("start");
        Cave to = new Cave("end");

        return getAllPathsBetween(from, to, new ArrayList<>(), (next, visited) -> !visited.contains(next) || next.isBig());
    }

    List<List<Cave>> getAllPathsFromStartToFinish2() {
        Cave from = new Cave("start");
        Cave to = new Cave("end");

        return getAllPathsBetween(from, to, new ArrayList<>(), (next, visited) -> !visited.contains(next) || next.isBig() || (!next.isStart() && hasNoSmallVisitedTwice(visited)));
    }

    private List<List<Cave>> getAllPathsBetween(Cave from, Cave to, List<Cave> visited, BiFunction<Cave, List<Cave>, Boolean> condition) {
        visited.add(from);

        if (from.equals(to)) {
            List<List<Cave>> result = new ArrayList<>();
            result.add(visited);
            return result;
        }

        List<List<Cave>> result = new ArrayList<>();

        for (Edge edge : edges) {
            if (edge.hasCave(from)) {
                Cave next = edge.getOther(from);
                if (condition.apply(next, visited)) {
                    result.addAll(getAllPathsBetween(next, to, new ArrayList<>(visited), condition));
                }
            }
        }

        return result;
    }

    private boolean hasNoSmallVisitedTwice(List<Cave> visited) {
        List<String> smallCaves = visited.stream()
                .filter(cave -> !cave.isBig())
                .map(Cave::id)
                .sorted()
                .toList();

        if (smallCaves.size() >= 2){
            for (int i = 1; i < smallCaves.size(); i++) {
                if (smallCaves.get(i).equals(smallCaves.get(i-1))){
                    return false;
                }
            }
        }
        return true;
    }
}
