package view;

import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Absztrakt osztály a tektonok grafikus megjelenítésére.
 * A TektonView komponens felelős a tekton modell objektumának megjelenítéséért,
 * és a felhasználói interakciók kezeléséért a játékfelületen.
 */
public abstract class TektonView extends JButton {
    /** A tekton egyedi azonosítója */
    protected int id;
    /** A megjelenített tekton modell objektuma */
    private final Tekton tekton;
    /** A tekton kiválasztásakor végrehajtandó eseménykezelő */
    protected ActionListener tektonSelectionAL;
    /** A tekton pozíciója a játékfelületen */
    protected Vec2 coord;
    /** A tekton megjelenítéséhez használt kép */
    protected Image image;

    /**
     * Létrehoz egy új tekton nézetet a megadott modell, pozíció és eseménykezelő alapján.
     *
     * @param t A megjelenítendő tekton modell
     * @param x A tekton x koordinátája a játékfelületen
     * @param y A tekton y koordinátája a játékfelületen
     * @param listener A tekton kiválasztására reagáló eseménykezelő
     */
    protected TektonView(Tekton t, int x, int y, ActionListener listener) {
        this.tekton = t;
        this.id = tekton.getId();
        coord = new Vec2(x, y);
        setBounds(x, y, 40, 40);
        this.tektonSelectionAL = listener;
        addActionListener(tektonSelectionAL);
    }

    /**
     * Visszaadja a tekton egyedi azonosítóját.
     *
     * @return A tekton azonosítója
     */
    public int getId() { return id; }

    /**
     * Frissíti a tekton nézetét, újrarajzolva a komponenst.
     */
    public void updateView() {
        repaint();
    }

    /**
     * Betölti a tekton megjelenítéséhez szükséges textúrát.
     * Az alosztályokban implementálandó metódus.
     */
    public abstract void loadTexture();

    /**
     * Kirajzolja a tekton komponenst a megadott grafikus kontextusra.
     * Ha a kép be van töltve, akkor azt megjeleníti.
     *
     * @param g A grafikus kontextus, amire a rajzolás történik
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) g.drawImage(image, 0, 0, this);
    }

    /**
     * Visszaadja a tekton pozícióját a játékfelületen.
     *
     * @return A tekton koordinátái
     */
    public Vec2 getPosition() {
        return coord;
    }

    /**
     * Visszaadja a tekton modell objektumot.
     *
     * @return A tekton modell
     */
    public Tekton getTekton() {
        return tekton;
    }
}