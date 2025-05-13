package view;

/**
 * A gyorsító rovar vizuális megjelenítéséért felelős osztály.
 * Ez az osztály a SporaHatasView osztályból származik és
 * betölti a gyorsító rovar képét a megadott iránynak megfelelően.
 */
public class GyorsitoView extends SporaHatasView {
    /**
     * Létrehoz egy új GyorsitoView objektumot a megadott iránnyal.
     *
     * @param dir Az irány, amely felé a gyorsító rovar hatást fejt ki
     */
    public GyorsitoView(Direction dir) {
        loadImage("/gyorsito_rovar.png", dir);
    }
}