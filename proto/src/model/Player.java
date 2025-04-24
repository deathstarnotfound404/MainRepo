package model;

public abstract class Player {
    protected int score = 0;

    protected Player() {}

    protected void addScore(int val) {
        this.score += val;
    }

    //TODO IMPLEMENT IN PLAYERS
    public abstract int getScoreFromPlayer();

    protected int getScore() {
        return getScoreFromPlayer();    //Ez updateli a score-t apayer leszármazottól
    }

    protected void setScore(int val) {
        this.score = val;
    }
}
