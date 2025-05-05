package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BaseTektonView extends TektonView {
    public BaseTektonView(int id, int x, int y, ActionListener listener) {
        super(id, x, y, listener);
        loadTexture();
    }

    @Override
    public void loadTexture() {
        //image = Toolkit.getDefaultToolkit().getImage("resources/base_tekton.png");
        image = new ImageIcon(getClass().getResource("/resources/base_tekton.png")).getImage();
        Image scaled = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
}
