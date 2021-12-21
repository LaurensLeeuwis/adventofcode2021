package eu.leeuwis.adventofcode2021.day21;

class Player {
    private int position;
    private int score = 0;

    Player(int startPosition) {
        this.position = startPosition;
    }

    void roll(Die die, int times) {
        int totalRoll = 0;

        for (int i = 0; i < times; i++) {
            int roll = die.roll();
            totalRoll += roll;
        }

        position = (position + totalRoll) % 10;
        score += getPosition();
    }

    int getPosition() {
        return (position == 0 ? 10 : position);
    }

    int getScore() {
        return score;
    }

}
