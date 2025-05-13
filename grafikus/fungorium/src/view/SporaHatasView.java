package view;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SporaHatasView {
    protected BufferedImage img;

    public abstract void loadImage(String path, Direction dir);

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
