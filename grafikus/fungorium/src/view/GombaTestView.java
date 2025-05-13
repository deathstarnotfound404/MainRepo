package view;
import model.GombaTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * A gombatest grafikus megjelenítéséért felelős osztály.
 * A gombatestek a gomba szaporodási ciklusának eredményei,
 * amelyek a tektonon jönnek létre és pontokat jelentenek a játékosnak.
 */
public class GombaTestView {
    /** A megjelenített gombatest modell objektum. */
    private final GombaTest gombaTest;
    /** A gombatest egyedi azonosítója. */
    private final int id;
    /** A gombatest megjelenítéséhez használt képi erőforrás. */
    private final BufferedImage image;
    /** A gombatest pozíciója a játéktéren. */
    private final Vec2 pos;

    /**
     * Létrehoz egy új gombatest nézetet a megadott paraméterekkel.
     *
     * @param tPos a tekton nézet, amelyhez a gombatest tartozik
     * @param test a megjelenítendő gombatest modell objektum
     * @param c a gombatest megjelenítési színe (a gombász színe alapján)
     * @throws IOException ha a kép betöltése sikertelen
     */
    public GombaTestView(TektonView tPos, GombaTest test, Color c) throws IOException {
        this.gombaTest = test;
        this.id = gombaTest.getId();
        this.pos = tPos.getPosition();

        // Kép betöltése a gombatest színe alapján
        if(c == Color.CYAN) {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/cyan_gt.png")));
        } else {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/pink_gt.png")));
        }
    }

    /**
     * Frissíti a gombatest nézet megjelenítését a grafikus kontextuson.
     * A gombatest képét a megfelelő pozícióra rajzolja a játéktéren.
     *
     * @param g a grafikus kontextus
     */
    public void updateView(Graphics g) {
        int drawWidth = 20;
        int drawHeight = 20;
        int drawX = pos.getX() + 10;  // középre helyezés
        int drawY = pos.getY() + 10;

        g.drawImage(image, drawX, drawY, drawWidth, drawHeight, null);
    }

    /**
     * Visszaadja a gombatest egyedi azonosítóját.
     *
     * @return a gombatest azonosítója
     */
    public int getId() {
        return id;
    }

    /**
     * Visszaadja a megjelenített gombatest modell objektumot.
     *
     * @return a gombatest modell objektum
     */
    public GombaTest getGombaTest() {
        return this.gombaTest;
    }

    /**
     * Szöveges reprezentációt ad a gombatest nézetről.
     *
     * @return a gombatest szöveges reprezentációja
     */
    @Override
    public String toString() {
        return this.gombaTest.toString();
    }
}