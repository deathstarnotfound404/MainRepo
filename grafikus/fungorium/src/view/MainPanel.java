package view;
import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private FieldView gamePanel;
    private InfoPanel infoPanel;

    public MainPanel(Controller controller, Timer timer) {
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

    public void updateView() {
        gamePanel.updateView();
        infoPanel.updateView();
    }
}
