package view;

/**
 * A klónozó rovar vizuális megjelenítéséért felelős osztály.
 * Ez az osztály a SporaHatasView osztályból származik és
 * betölti a klónozó rovar képét a megadott iránynak megfelelően.
 */
public class KlonozoView extends SporaHatasView {
    /**
     * Létrehoz egy új KlonozoView objektumot a megadott iránnyal.
     *
     * @param dir Az irány, amely felé a klónozó rovar hatást fejt ki
     */
    public KlonozoView(Direction dir) {
        loadImage("/klonozo_rovar.png", dir);
    }
}