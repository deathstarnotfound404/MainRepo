package view;
import model.Rovar;

import java.awt.*;

/**
 * A rovarok vizuális megjelenítéséért felelős osztály.
 * Ez az osztály kapcsolatot teremt a modellben lévő Rovar objektum
 * és annak grafikus megjelenítése között a játékfelületen.
 */
public class RovarView {
    /** A megjelenített rovar modell objektuma */
    private final Rovar r;
    /** A rovar egyedi azonosítója */
    private final int id;
    /** A rovar pozíciója a képernyőn */
    private final Vec2 pos;
    /** A rovar által kifejtett hatás megjelenítője */
    private final SporaHatasView hatas;

    /**
     * Létrehoz egy új rovar nézetet a megadott pozícióval, rovar modellel és hatással.
     *
     * @param pos A tekton nézet, amihez a rovar tartozik
     * @param r A rovar modell objektuma
     * @param hatas A rovar által kifejtett hatás nézete
     */
    public RovarView(TektonView pos, Rovar r, SporaHatasView hatas) {
        this.hatas = hatas;

        this.r = r;
        this.id = r.getId();

        int centerX = pos.getPosition().getX() + pos.getWidth() / 2;
        int centerY = pos.getPosition().getY() + pos.getHeight() / 2;
        this.pos = new Vec2(centerX, centerY);
    }

    /**
     * Frissíti és kirajzolja a rovar nézetét a megadott grafikus kontextusra.
     *
     * @param g A grafikus kontextus, amire a rovar rajzolódik
     */
    public void updateView(Graphics g) {
        hatas.draw(g, pos);
    }

    /**
     * Visszaadja a rovar egyedi azonosítóját.
     *
     * @return A rovar azonosítója
     */
    public int getId() {
        return id;
    }

    /**
     * Visszaadja a rovar string reprezentációját.
     *
     * @return A rovar szöveges leírása
     */
    @Override
    public String toString() {
        return this.r.toString();
    }

    /**
     * Visszaadja a rovar modell objektumot.
     *
     * @return A rovar modell objektuma
     */
    public Rovar getRovar() {
        return this.r;
    }
}