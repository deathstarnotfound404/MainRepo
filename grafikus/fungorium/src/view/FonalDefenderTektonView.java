package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FonalDefenderTektonView extends TektonView {
    public FonalDefenderTektonView(int id, int x, int y, ActionListener listener) {
        super(id, x, y, listener);
        loadTexture();
    }

    @Override
    public void loadTexture() {

        //image = Toolkit.getDefaultToolkit().getImage("resources/fonal_defender.png");

        image = new ImageIcon(getClass().getResource("/resources/fonal_defender.png")).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

    }
}
