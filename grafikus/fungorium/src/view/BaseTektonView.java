package view;
import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Nézet osztály, amely a tektonlemez alaptípusának (BaseTekton) grafikus megjelenítéséért felelős.
 * Kezeli a tektonlemez képének betöltését és megjelenítését.
 * Kiterjeszti a {@link TektonView} osztályt.
 */
public class BaseTektonView extends TektonView {

    /**
     * Létrehoz egy BaseTektonView objektumot a megadott tektonlemezhez és pozícióhoz.
     *
     * @param t a megjelenítendő tektonlemez objektum
     * @param x a tektonlemez x koordinátája
     * @param y a tektonlemez y koordinátája
     * @param listener eseményfigyelő a tektonlemez interakcióihoz
     */
    public BaseTektonView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    /**
     * Betölti a tektonlemez képét az erőforrásokból és beállítja megjelenítési tulajdonságait.
     * Felülírja az ősosztály metódusát.
     */
    @Override
    public void loadTexture() {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/base_tekton.png"))).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}