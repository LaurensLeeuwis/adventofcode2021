package eu.leeuwis.adventofcode2021.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day21 {

    long puzzle1(List<String> input) {
        List<Player> players = parse(input);
        Die die = new Die();

        int highscore = 0;
        int playerNr = 0;
        while (highscore < 1000) {
            Player player = players.get(playerNr);
            player.roll(die, 3);
            int score = player.getScore();
            if (score > highscore) {
                highscore = score;
            }

            playerNr = (playerNr + 1) % players.size();
        }

        return players.get(playerNr).getScore() * die.total();
    }

    public long puzzle2(List<String> input) {
        List<Player> players = parse(input);

        Map<State, Long> stateOccurences = new HashMap<>();
        var startState = new State(0, players.get(0).getPosition(), 0, players.get(1).getPosition(), true);
        stateOccurences.put(startState, 1L);

        long player1Wins = 0;
        long player2Wins = 0;

        // 3, 1x = 1+1+1
        // 4, 3x = 1+1+2, 1+2+1, 2+1+1
        // 5, 6x = 1+1+3, 1+2+2, 1+3+1, 2+1+2, 2+2+1, 3+1+1
        // 6, 7x = 1+2+3, 1+3+2, 2+1+3, 2+2+2, 2+3+1, 3+1+2, 3+2+1
        // 7, 6x = 1+3+3, 2+2+3, 2+3+2, 3+1+3, 3+2+2, 3+3+1
        // 8, 3x = 2+3+3, 3+2+3, 3+3+2
        // 9, 1x = 3+3+3
        int[] multiplierTable = new int[]{0, 0, 0, 1, 3, 6, 7, 6, 3, 1};

        while (!stateOccurences.isEmpty()) {
            Map<State, Long> nextState = new HashMap<>();

            for (Map.Entry<State, Long> entry : stateOccurences.entrySet()) {
                var state = entry.getKey();
                var times = entry.getValue();

                if (state.player1Turn()) {
                    for (int i = 3; i < multiplierTable.length; i++) {
                        int multiplier = multiplierTable[i];
                        int newPos = calcPos(state.player1Pos() + i);
                        int newScore = state.player1Score() + newPos;
                        if (newScore >= 21) {
                            player1Wins = player1Wins + (multiplier * times);
                        } else {
                            State newState = new State(newScore, newPos, state.player2Score(), state.player2Pos(), false);
                            var current = nextState.getOrDefault(newState, 0L);
                            nextState.put(newState, current + (multiplier * times));
                        }
                    }
                } else {
                    for (int i = 3; i < multiplierTable.length; i++) {
                        int multiplier = multiplierTable[i];
                        int newPos = calcPos(state.player2Pos() + i);
                        int newScore = state.player2Score() + newPos;
                        if (newScore >= 21) {
                            player2Wins = player2Wins + (multiplier * times);
                        } else {
                            State newState = new State(state.player1Score(), state.player1Pos(), newScore, newPos, true);
                            var current = nextState.getOrDefault(newState, 0L);
                            nextState.put(newState, current + (multiplier * times));
                        }
                    }
                }
            }
            stateOccurences = nextState;
        }

        return Math.max(player1Wins, player2Wins);
    }

    private List<Player> parse(List<String> input) {
        List<Player> result = new ArrayList<>();

        for (String line : input) {
            int pos = Integer.parseInt(line.split("starting position: ")[1]);
            result.add(new Player(pos));
        }
        return result;
    }

    private int calcPos(int i) {
        int init = i % 10;
        if (init == 0) {
            return 10;
        }
        return init;
    }


}
