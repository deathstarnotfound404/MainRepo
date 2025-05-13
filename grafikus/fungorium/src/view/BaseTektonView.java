package view;
import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class BaseTektonView extends TektonView {
    public BaseTektonView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    @Override
    public void loadTexture() {
        //image = Toolkit.getDefaultToolkit().getImage("resources/base_tekton.png");
        image = new ImageIcon(getClass().getResource("/base_tekton.png")).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}
