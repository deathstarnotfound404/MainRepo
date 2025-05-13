package view;
import java.awt.*;

/**
 * A tektonok közötti szomszédossági kapcsolatot megjelenítő nézet osztály.
 * Ez az osztály a Line osztály leszármazottjaként felelős a játékmezőn
 * lévő tektonok közötti szomszédossági viszony vizuális megjelenítéséért
 * szaggatott vonalak formájában.
 */
public class SzomszedossagView extends Line {

    /**
     * Létrehoz egy új szomszédossági nézetet két tekton között.
     * A két tekton közötti kapcsolatot fekete szaggatott vonallal jelöli.
     *
     * @param t1 Az első tekton nézet
     * @param t2 A második tekton nézet
     */
    public SzomszedossagView(TektonView t1, TektonView t2) {
        super(t1, t2, Color.BLACK);
    }

    /**
     * Kirajzolja a szomszédossági kapcsolatot jelképező szaggatott vonalat
     * a két tekton középpontja között.
     *
     * @param g A grafikus kontextus, amire a vonal rajzolódik
     */
    @Override
    public void draw(Graphics2D g) {
        // Antialiasing bekapcsolása a simább vonalakért
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(super.color);

        // Finomabb, rövidebb szaggatott minta beállítása
        Stroke dashed = new BasicStroke(
                1f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1f,
                new float[]{4f, 4f},
                0f
        );
        g.setStroke(dashed);

        int centerX_t1 = tekton1.coord.getX() + tekton1.getWidth() / 2;
        int centerY_t1 = tekton1.coord.getY() + tekton1.getHeight() / 2;
        int centerX_t2 = tekton2.coord.getX() + tekton2.getWidth() / 2;
        int centerY_t2 = tekton2.coord.getY() + tekton2.getHeight() / 2;

        g.drawLine(centerX_t1, centerY_t1, centerX_t2, centerY_t2);
    }

    /**
     * Frissíti a szomszédossági nézet megjelenítését.
     * Jelenleg nincs implementálva.
     */
    @Override
    public void updateView() {}

    /**
     * Visszaadja a szomszédossági kapcsolat azonosítóját.
     * A szomszédossági kapcsolatoknak nincs egyedi azonosítója,
     * így alapértelmezetten -1-et ad vissza.
     *
     * @return Mindig -1, mivel a szomszédossági kapcsolatoknak nincs egyedi azonosítója
     */
    @Override
    public int getId() {
        return -1;
    }
}