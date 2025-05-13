package view;

/**
 * Nézet osztály, amely a spóra alaptípusának (BaseSpora) grafikus megjelenítéséért felelős.
 * Kezeli a spóra képének betöltését és forgatását az irány függvényében.
 * Kiterjeszti a {@link SporaHatasView} osztályt.
 */
public class BaseSporaView extends SporaHatasView {
    /**
     * Létrehoz egy BaseSporaView objektumot és betölti az alapértelmezett spóra képet.
     *
     * @param dir az irány, amelybe a spóra néz
     */
    public BaseSporaView(Direction dir) {
        loadImage("/rovar.png", dir);
    }
}