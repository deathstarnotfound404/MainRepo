package view;

/**
 * Kétdimenziós vektor osztály, amely egész koordinátákat tárol.
 * Ez az osztály egyszerű vektor műveleteket támogat, és a játékelemek
 * pozíciójának tárolására és számítására szolgál a grafikus felületen.
 */
public class Vec2 {
    /** A vektor x (vízszintes) komponense */
    private int x;
    /** A vektor y (függőleges) komponense */
    private int y;

    /**
     * Létrehoz egy új kétdimenziós vektort a megadott koordinátákkal.
     *
     * @param x A vektor vízszintes (x) komponense
     * @param y A vektor függőleges (y) komponense
     */
    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Visszaadja a vektor vízszintes (x) komponensét.
     *
     * @return A vektor x koordinátája
     */
    public int getX() { return x; }

    /**
     * Visszaadja a vektor függőleges (y) komponensét.
     *
     * @return A vektor y koordinátája
     */
    public int getY() { return y; }

    /**
     * Létrehoz egy új vektort, amely az aktuális és a paraméterként kapott
     * vektorok összegeként jön létre.
     *
     * @param other A másik vektor, amivel összeadunk
     * @return Egy új Vec2 objektum, amely a két vektor összege
     */
    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    /**
     * Módosítja az aktuális vektort a megadott értékekkel.
     * A paraméterként kapott lebegőpontos értékeket egész számra kerekítve
     * adja hozzá az x és y komponensekhez.
     *
     * @param dx Az x komponenshez hozzáadandó érték
     * @param dy Az y komponenshez hozzáadandó érték
     */
    public void add(double dx, double dy) {
        this.x += (int)dx;
        this.y += (int)dy;
    }
}