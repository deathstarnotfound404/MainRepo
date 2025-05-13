package view;

/**
 * A lassító rovar vizuális megjelenítéséért felelős osztály.
 * Ez az osztály a SporaHatasView osztályból származik és
 * betölti a lassító rovar képét a megadott iránynak megfelelően.
 */
public class LassitoView extends SporaHatasView {
    /**
     * Létrehoz egy új LassitoView objektumot a megadott iránnyal.
     *
     * @param dir Az irány, amely felé a lassító rovar hatást fejt ki
     */
    public LassitoView(Direction dir) {
        loadImage("/lassito_rovar.png", dir);
    }
}