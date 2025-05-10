package view;
import model.GombaTest;
import model.Tekton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GombaTestView {
    private GombaTest gombaTest;
    private int id;
    private BufferedImage image;
    private Vec2 pos;

    public GombaTestView(TektonView tPos, GombaTest test, Color c) throws IOException {
        this.gombaTest = test;
        this.id = gombaTest.getId();
        this.pos = tPos.getPosition();


        //Gen img
        if(c == Color.CYAN) {
            image = ImageIO.read(getClass().getResource("/cyan_gt.png"));
        } else {
            image = ImageIO.read(getClass().getResource("/pink_gt.png"));
        }
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

    public GombaTest getGombaTest() {
        return this.gombaTest;
    }
}
