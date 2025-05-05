package view;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GombaTestView {
    int id;
    private BufferedImage image;
    private Vec2 pos;

    public GombaTestView(TektonView tPos, BufferedImage img, int id) {
        this.id = id;
        this.pos = tPos.getPosition();
        this.image = img;
    }

    public void updateView(Graphics g) {
        int drawWidth = 20;
        int drawHeight = 20;
        int drawX = pos.getX() + 10;  // középre helyezés
        int drawY = pos.getY() + 10;

        g.drawImage(image, drawX, drawY, drawWidth, drawHeight, null);
    }

    public int getId() {
        return id;
    }
}
