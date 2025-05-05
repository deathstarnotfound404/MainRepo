package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class TektonView extends JButton {
    protected int id;
    protected ActionListener tektonSelectionAL;
    protected Vec2 coord;
    protected Image image;

    protected TektonView(int id,  int x, int y, ActionListener listener) {
        this.id = id;
        coord = new Vec2(x, y);
        setBounds(x, y, 40, 40);
        this.tektonSelectionAL = listener;
        addActionListener(tektonSelectionAL);
    }

    public int getId() { return id; }

    public void updateView() {
        repaint();
    }

    public abstract void loadTexture();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) g.drawImage(image, 0, 0, this);
    }
}

