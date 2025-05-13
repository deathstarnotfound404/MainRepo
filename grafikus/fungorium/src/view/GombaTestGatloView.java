package view;

import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Speciális TektonView osztály a gombatestgátló tektonlemez megjelenítésére.
 * Ez a tekton típus megakadályozza, hogy gombatestek jöjjenek létre rajta,
 * ezáltal korlátozza a gombák szaporodását.
 */
public class GombaTestGatloView extends TektonView {

    /**
     * Létrehoz egy új gombatestgátló tekton nézetet.
     *
     * @param t a megjelenítendő tekton modell objektum
     * @param x a tekton nézet x koordinátája a játéktéren
     * @param y a tekton nézet y koordinátája a játéktéren
     * @param listener eseményfigyelő a tekton kattintás kezelésére
     */
    public GombaTestGatloView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    /**
     * Betölti a gombatestgátló tekton egyedi textúráját és beállítja a megjelenítési tulajdonságait.
     * A gombatestgátló tekton speciális grafikával rendelkezik, amely jelzi annak gátló funkcióját.
     */
    @Override
    public void loadTexture() {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/gombatest_gatlo.png"))).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}