package model;

/**
 * Abstract base class representing a player in the game.
 * Provides functionality for tracking player scores and names.
 * Subclasses must implement the scoring mechanism.
 */
public abstract class Player {
    /** The player's current score */
    protected int score = 0;

    /** The player's name */
    protected String name;

    /**
     * Constructs a new Player with the specified name.
     *
     * @param name the name of the player
     */
    protected Player(String name) {
        this.name = name;
    }

    /**
     * Adds to the player's current score.
     *
     * @param val the value to add to the score
     */
    protected void addScore(int val) {
        this.score += val;
    }

    /**
     * Gets the current score from the implementing player class.
     * To be implemented by subclasses with their specific scoring logic.
     *
     * @return the current score of the player
     */
    public abstract int getScoreFromPlayer();

    /**
     * Updates and returns the current score by calling the subclass-specific implementation.
     *
     * @return the updated score of the player
     */
    protected int getScore() {
        return getScoreFromPlayer();    //Ez updateli a score-t apayer leszármazottól
    }

    /**
     * Sets the player's score to a specific value.
     *
     * @param val the new score value
     */
    protected void setScore(int val) {
        this.score = val;
    }

    /**
     * Gets the player's name.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
}