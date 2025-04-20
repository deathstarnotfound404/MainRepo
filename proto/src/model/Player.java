package model;

public abstract class Player {
    protected int score = 0;
    protected Player() {}
    protected void addScore() {}
    protected int getScore() { return score; }
    protected void setScore(int val) { this.score = val; }
}