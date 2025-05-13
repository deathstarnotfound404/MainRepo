package view;

import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class FonalFelszivodoTektonView extends TektonView {
    public FonalFelszivodoTektonView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    @Override
    public void loadTexture() {
        //image = Toolkit.getDefaultToolkit().getImage("resources/fonal_felszivodo.png");

        image = new ImageIcon(getClass().getResource( "/fonal_felszivodo.png")).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}
