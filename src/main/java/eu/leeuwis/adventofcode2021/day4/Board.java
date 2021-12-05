package eu.leeuwis.adventofcode2021.day4;

import java.util.Optional;

class Board {
    private final int numbers[][] = new int[5][5];
    private final boolean drawn[][] = new boolean[5][5];

    public Board(String boardInput) {
        String[] lines = boardInput.split("\n");

        for (int i = 0; i < 5; i++) {
            String line = lines[i];
            String[] inputNumbers = line.trim().split(" +");

            for (int j = 0; j < 5; j++) {
                String inputNumber = inputNumbers[j];
                numbers[i][j] = Integer.parseInt(inputNumber);
            }
        }
    }

    public Optional<Integer> callBingo(int number) {
        markNumber(number);
        if (hasBingo()){
            int sum = getUnmarkedSum();
            return Optional.of(sum * number);
        }

        return Optional.empty();
    }

    private int getUnmarkedSum() {
        int result = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!drawn[i][j]){
                    result += numbers[i][j];
                }
            }
        }
        return result;
    }

    private boolean hasBingo() {
        for (int i = 0; i < 5; i++) {
            boolean hasBingo = true;

            for (int j = 0; j < 5; j++) {
                hasBingo = hasBingo && drawn[i][j];
            }

            if (hasBingo){
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            boolean hasBingo = true;

            for (int j = 0; j < 5; j++) {
                hasBingo = hasBingo && drawn[j][i];
            }

            if (hasBingo){
                return true;
            }
        }
        return false;
    }

    private void markNumber(int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numbers[i][j] == number){
                    drawn[i][j] = true;
                    return;
                }
            }
        }
    }
}
