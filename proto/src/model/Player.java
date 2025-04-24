package model;

public abstract class Player {
    protected int score = 0;
    protected String name;

    protected Player(String name) {
        this.name = name;
    }

    protected void addScore(int val) {
        this.score += val;
    }

    public abstract int getScoreFromPlayer();

    protected int getScore() {
        return getScoreFromPlayer();    //Ez updateli a score-t apayer leszármazottól
    }

    protected void setScore(int val) {
        this.score = val;
    }

    public String getName() {
        return name;
    }
}
