package eu.leeuwis.adventofcode2021.day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Day4 {

    public int puzzle1(List<String> inputs) {
        String draws = inputs.get(0);

        List<Board> boards = createBoards(inputs);

        String[] numbers = draws.split(",");
        for (String number : numbers) {
            int draw = Integer.parseInt(number);
            for (Board board : boards) {
                Optional<Integer> optionalBingo = board.callBingo(draw);
                if (optionalBingo.isPresent()){
                    return optionalBingo.get();
                }
            }
        }
        return 0;
    }

    public int puzzle2(List<String> inputs) {
        String draws = inputs.get(0);

        List<Board> boards = Collections.unmodifiableList(createBoards(inputs));

        int bingo = 0;

        String[] numbers = draws.split(",");
        for (String number : numbers) {
            int draw = Integer.parseInt(number);
            List<Board> nextBoards = new ArrayList<>();
            for (Board board : boards) {
                Optional<Integer> optionalBingo = board.callBingo(draw);
                if (optionalBingo.isEmpty()){
                    nextBoards.add(board);
                } else {
                    bingo =  optionalBingo.get();
                    System.out.println("bingo! " +bingo);
                }
            }
            if (nextBoards.size() != 0) {
                boards = Collections.unmodifiableList(nextBoards);
            } else {
                return bingo;
            }


        }
        return 0;
    }

    private List<Board> createBoards(List<String> inputs) {
        List<Board> boards = new ArrayList<>();

        for (int i = 1; i < inputs.size(); i+=6) {
            String boardInput = String.join("\n", inputs.get(i+1), inputs.get(i+2), inputs.get(i+3), inputs.get(i+4), inputs.get(i+5));
            Board board = new Board(boardInput);
            boards.add(board);
        }
        return boards;
    }

}
