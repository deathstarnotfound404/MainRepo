package view;

import model.Tekton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class FonalDefenderTektonView extends TektonView {
    public FonalDefenderTektonView(Tekton t, int x, int y, ActionListener listener) {
        super(t, x, y, listener);
        loadTexture();
    }

    @Override
    public void loadTexture() {

        //image = Toolkit.getDefaultToolkit().getImage("resources/fonal_defender.png");

        image = new ImageIcon(getClass().getResource("/fonal_defender.png")).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

    }
}
