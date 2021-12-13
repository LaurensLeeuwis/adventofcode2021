package eu.leeuwis.adventofcode2021.day13;

import java.util.ArrayList;
import java.util.List;

class Day13 {

    long puzzle1(List<String> readFile) {
        Input input = parseInput(readFile);
        Field field = createField(input);

        FoldInstruction instruction = input.foldInstructions().get(0);
        executeFoldInstruction(instruction, field);

        return field.nrOfDots();
    }

    String puzzle2(List<String> readFile) {
        Input input = parseInput(readFile);
        Field field = createField(input);

        for (FoldInstruction instruction : input.foldInstructions()) {
            executeFoldInstruction(instruction, field);
        }

        return field.draw();
    }

    private Input parseInput(List<String> readFile) {
        List<Dot> dots = new ArrayList<>();
        List<FoldInstruction> instructions = new ArrayList<>();

        for (String line : readFile) {
            if (line.contains(",")) {
                String[] dotI = line.split(",");
                Dot dot = new Dot(dotI[0], dotI[1]);

                dots.add(dot);
            } else if (line.contains("fold")) {
                String[] parts = line.split("=");
                int place = Integer.parseInt(parts[1]);
                String axisPart = parts[0];
                char axis = axisPart.charAt(axisPart.length() - 1);

                instructions.add(new FoldInstruction(axis, place));
            }
        }

        return new Input(dots, instructions);
    }

    private Field createField(Input input) {
        List<Dot> dots = input.dots();
        int maxX = dots.stream().map(Dot::x).mapToInt(it -> it).max().getAsInt();
        int maxY = dots.stream().map(Dot::y).mapToInt(it -> it).max().getAsInt();

        Field field = new Field(maxX, maxY);
        for (Dot dot : dots) {
            field.addDot(dot);
        }
        return field;
    }

    private void executeFoldInstruction(FoldInstruction instruction, Field field) {
        if (instruction.axis() == 'x'){
            field.foldX(instruction.place());
        } else if (instruction.axis() == 'y'){
            field.foldY(instruction.place());
        }
    }
}
