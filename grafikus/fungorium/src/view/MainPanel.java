package view;
import model.Field;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {
    private FieldView gamePanel;
    private InfoPanel infoPanel;
    private Controller controller;

    public MainPanel(Controller controller, Timer timer) {
        this.controller = controller;

        setLayout(new BorderLayout());

        gamePanel = new FieldView(controller);
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(600, 600));

        infoPanel = new InfoPanel(timer);
        infoPanel.setPreferredSize(new Dimension(200, 600));

        add(gamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
    }

    public FieldView getGamePanel() {
        return gamePanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void updateView(Field model) throws IOException {
        gamePanel.updateView(model);
        infoPanel.updateView(controller);
    }
}
