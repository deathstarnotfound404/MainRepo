package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Absztrakt osztály, amely a spórák hatásának vizuális megjelenítéséért felelős.
 * Az osztály alapvető képkezelési műveleteket biztosít a különböző spóra hatások
 * megjelenítéséhez a játékfelületen.
 */
public abstract class SporaHatasView {
    /** A hatás megjelenítéséhez használt kép */
    protected BufferedImage img;

    /**
     * Betölti a lassító rovar képét a megadott elérési útról,
     * és beállítja az irányt, amerre a rovar hatást fejt ki.
     * Ha az irány lefelé mutat, akkor elforgatja a képet.
     *
     * @param path A kép erőforrás elérési útja
     * @param dir A megjelenítés iránya
     */
    public void loadImage(String path, Direction dir) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            assert is != null;
            img = ImageIO.read(is);
            if(dir == Direction.DOWN) {
                imageForgatas();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 180 fokkal elforgatja a betöltött képet.
     * Ez a metódus a betöltött képet a középpontja körül forgatja el.
     */
    public void imageForgatas() {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage rotated = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = rotated.createGraphics();

        // Kép közepére forgatás és elforgatás 180 fokkal
        g2d.rotate(Math.toRadians(180), width / 2.0, height / 2.0);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        img = rotated;
    }

    /**
     * Kirajzolja a hatás képét a megadott grafikus kontextusra és pozícióra.
     * A rajzolás során a képet a megadott pozíció központja körül helyezi el.
     *
     * @param g A grafikus kontextus, amire a kép rajzolódik
     * @param pos A pozíció, ahová a kép központját helyezni kell
     */
    public void draw(Graphics g, Vec2 pos) {
        if (img == null) return;

        // TektonView mérete alapján számoljuk a középpontot (40x40)
        int centerX = pos.getX();
        int centerY = pos.getY();

        // Rovar méret (kisebb mint a kör)
        int drawWidth = 20;
        int drawHeight = 20;

        // Kép bal felső sarkának kiszámítása
        int drawX = centerX - drawWidth / 2;
        int drawY = centerY - drawHeight / 2;

        g.drawImage(img, drawX, drawY, drawWidth, drawHeight, null);
    }
}