package view;

import java.awt.*;

/**
 * Absztrakt osztály, amely a Tektonok közötti kapcsolatok vizuális megjelenítését reprezentálja.
 * Ez az osztály felelős a két Tekton között húzott vonalak alapvető tulajdonságainak kezeléséért.
 * Az osztályból származó konkrét implementációk határozzák meg a vonalak konkrét megjelenítését.
 */
public abstract class Line {
    /** Az első összekötött Tekton nézet */
    protected TektonView tekton1;
    /** A második összekötött Tekton nézet */
    protected TektonView tekton2;
    /** A vonal színe */
    protected Color color;

    /**
     * Létrehoz egy új vonalat a megadott Tekton nézetek és szín között.
     *
     * @param t1 Az első Tekton nézet
     * @param t2 A második Tekton nézet
     * @param color A vonal színe
     */
    public Line(TektonView t1, TektonView t2, Color color) {
        this.tekton1 = t1;
        this.tekton2 = t2;
        this.color = color;
    }

    /**
     * Kirajzolja a vonalat a megadott grafikus kontextusra.
     * Ezt a metódust az alosztályoknak kell megvalósítaniuk.
     *
     * @param g A grafikus kontextus, amire a vonalat rajzolni kell
     */
    public abstract void draw(Graphics2D g);

    /**
     * Frissíti a vonal megjelenítését.
     * Ezt a metódust az alosztályoknak kell megvalósítaniuk.
     */
    public abstract void updateView();

    /**
     * Visszaadja a vonal azonosítóját.
     * Ezt a metódust az alosztályoknak kell megvalósítaniuk.
     *
     * @return A vonal egyedi azonosítója
     */
    public abstract int getId();
}