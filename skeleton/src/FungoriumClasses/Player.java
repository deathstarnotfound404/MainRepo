package FungoriumClasses;

public abstract class Player {
    protected int score = 0;

    public Player() {

    }

    protected void setScore(int val) {
        this.score = val;
    }

    protected int getScore() {
        return this.score;
    }

    protected void addScore() {
        this.score += 1;
    }
}
