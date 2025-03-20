package FungoriumClasses;

public abstract class Player {
    protected int score = 0;

    public Player() {
        System.out.println("<<<return Player()");
    }

    protected void setScore(int val) {
        System.out.println("<<<return setScore()");
    }

    protected int getScore() {
        System.out.println("<<<return getScore()");
        return 0;
    }

    protected void addScore() {
        System.out.println("<<<return addScore()");
    }
}
