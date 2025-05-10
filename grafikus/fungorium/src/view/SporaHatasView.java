package view;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SporaHatasView {
    protected BufferedImage img;

    public abstract void loadImage(String path);

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
