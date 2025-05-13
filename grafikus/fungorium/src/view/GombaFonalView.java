package view;
import model.GombaFonal;

import java.awt.*;

/**
 * Grafikus megjelenítő osztály a gombafonalak vizuális reprezentálására.
 * A gombafonalak két tekton között húzódnak és a gombák terjedési útját jelzik.
 * A Line absztrakt osztályt kiterjesztve egyedi megjelenítést biztosít.
 */
public class GombaFonalView extends Line {
    /** A megjelenített gomba fonal modell objektum. */
    private final GombaFonal gf;

    /**
     * Létrehoz egy új gomba fonal nézetet a megadott paraméterekkel.
     *
     * @param t1 a kiindulási tekton nézet objektum
     * @param t2 a cél tekton nézet objektum
     * @param gf a megjelenítendő gomba fonal modell objektum
     * @param c a gomba fonal megjelenítési színe (a gombász színe alapján)
     */
    public GombaFonalView(TektonView t1, TektonView t2, GombaFonal gf, Color c) {
        super(t1, t2, c);
        this.gf = gf;
    }

    /**
     * Kirajzolja a gombafonalat a grafikus kontextusra.
     * A vonal a két tekton középpontját köti össze a megfelelő színnel.
     *
     * @param g a grafikus kontextus
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(super.color);
        g.setStroke(new BasicStroke(2));

        int centerX_t1 = tekton1.coord.getX() + tekton1.getWidth() / 2;
        int centerY_t1 = tekton1.coord.getY() + tekton1.getHeight() / 2;
        int centerX_t2 = tekton2.coord.getX() + tekton2.getWidth() / 2;
        int centerY_t2 = tekton2.coord.getY() + tekton2.getHeight() / 2;
        g.drawLine(centerX_t1, centerY_t1, centerX_t2, centerY_t2);
    }

    /**
     * Frissíti a gomba fonal nézet megjelenítését.
     * Jelenlegi implementációban nem végez műveletet.
     */
    @Override
    public void updateView() {}

    /**
     * Visszaadja a gomba fonal egyedi azonosítóját.
     *
     * @return a gomba fonal egyedi azonosítója
     */
    @Override
    public int getId() {
        return gf.getID();
    }
}