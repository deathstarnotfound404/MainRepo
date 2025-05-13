package view;

/**
 * A vágást gátló spóra hatásának megjelenítéséért felelős osztály.
 * A SporaHatasView leszármazottjaként megjeleníti a vágást gátló spórát
 * a megfelelő képpel és tájolással.
 */
public class VagastGatloView extends SporaHatasView {

    /**
     * Létrehoz egy új vágást gátló spóra nézetet a megadott iránnyal.
     * A konstruktor betölti a megfelelő képet és beállítja annak tájolását.
     *
     * @param dir Az irány, amerre a spóra hatást mutatni kell
     */
    public VagastGatloView(Direction dir) {
        loadImage("/vagast_gatlo_rovar.png", dir);
    }
}