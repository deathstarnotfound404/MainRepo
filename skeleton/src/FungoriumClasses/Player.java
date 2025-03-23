package FungoriumClasses;

/**
 * A {@code Player} absztrakt osztály egy játékost reprezentál a játékban.
 * Minden játékos rendelkezik egy pontszámmal, amelyet növelhet vagy beállíthat.
 */
public abstract class Player {
    /**
     * A játékos aktuális pontszáma.
     */
    protected int score = 0;

    /**
     * Létrehoz egy új {@code Player} objektumot alapértelmezett pontszámmal.
     */
    public Player() {
    }

    /**
     * Beállítja a játékos pontszámát egy adott értékre.
     *
     * @param val Az új pontszám.
     */
    protected void setScore(int val) {
        this.score = val;
    }

    /**
     * Visszaadja a játékos aktuális pontszámát.
     *
     * @return A játékos pontszáma.
     */
    protected int getScore() {
        return this.score;
    }

    /**
     * Növeli a játékos pontszámát eggyel.
     */
    protected void addScore() {
        this.score += 1;
    }
}
