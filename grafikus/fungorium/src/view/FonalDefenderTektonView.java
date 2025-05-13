package view;

import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Speciális TektonView osztály a fonalvédő tektonlemez megjelenítésére.
 * Ez a tekton típus megvédi a rajta áthaladó gombafonalakat az elvágástól.
 */
public class FonalDefenderTektonView extends TektonView {

    /**
     * Létrehoz egy új fonalvédő tekton nézetet.
     *
     * @param t a megjelenítendő tekton modell objektum
     * @param x a tekton nézet x koordinátája a játéktéren
     * @param y a tekton nézet y koordinátája a játéktéren
     * @param listener eseményfigyelő a tekton kattintás kezelésére
     */
    public FonalDefenderTektonView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    /**
     * Betölti a fonalvédő tekton egyedi textúráját és beállítja a megjelenítési tulajdonságait.
     * A fonalvédő tekton speciális grafikával rendelkezik, amely jelzi annak védő funkcióját.
     */
    @Override
    public void loadTexture() {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/fonal_defender.png"))).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}