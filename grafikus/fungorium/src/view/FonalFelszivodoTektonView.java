package view;

import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Speciális TektonView osztály a fonalfelszívódó tektonlemez megjelenítésére.
 * Ez a tekton típus felszívja/elnyeli a rajta áthaladó gombafonalakat,
 * ezáltal megakadályozza azok továbbterjedését.
 */
public class FonalFelszivodoTektonView extends TektonView {

    /**
     * Létrehoz egy új fonalfelszívódó tekton nézetet.
     *
     * @param t a megjelenítendő tekton modell objektum
     * @param x a tekton nézet x koordinátája a játéktéren
     * @param y a tekton nézet y koordinátája a játéktéren
     * @param listener eseményfigyelő a tekton kattintás kezelésére
     */
    public FonalFelszivodoTektonView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    /**
     * Betölti a fonalfelszívódó tekton egyedi textúráját és beállítja a megjelenítési tulajdonságait.
     * A fonalfelszívódó tekton speciális grafikával rendelkezik, amely jelzi annak elnyelő funkcióját.
     */
    @Override
    public void loadTexture() {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/fonal_felszivodo.png"))).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}