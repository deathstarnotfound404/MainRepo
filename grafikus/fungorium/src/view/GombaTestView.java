package view;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GombaTestView {
    int id;
    private BufferedImage image;
    private Vec2 pos;

    public GombaTestView(Vec2 pos, BufferedImage img, int id) {
        this.id = id;
        this.pos = pos;
        this.image = img;
    }

    public void updateView(Graphics g) {
        g.drawImage(image, pos.getX(), pos.getY(), null);
    }

    public int getId() {
        return id;
    }
}
