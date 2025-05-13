package view;

/**
 * Nézet osztály, amely a Benito típusú spóra grafikus megjelenítéséért felelős.
 * Kezeli a Benito spóra képének betöltését és forgatását az irány függvényében.
 * Kiterjeszti a {@link SporaHatasView} osztályt.
 */
public class BenitoView extends SporaHatasView {
    /**
     * Létrehoz egy BenitoView objektumot és betölti a Benito spóra képét.
     *
     * @param dir az irány, amelybe a spóra néz
     */
    public BenitoView(Direction dir) {
        loadImage("/benito_rovar.png", dir);
    }
}