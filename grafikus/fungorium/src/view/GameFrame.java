package view;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private MainPanel gamePanel;
    private MenuPanel menuPanel;
    private JPanel cards;

    public GameFrame() {
        super("Fungorium");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        menuPanel = new MenuPanel();

        cards.add(menuPanel, "Menu");

        add(cards);
        setVisible(true);
    }

    public void setGamePanel(MainPanel panel) {
        this.gamePanel = panel;
        cards.add(gamePanel, "Game");
    }

    public void switchToMenu() {
        cardLayout.show(cards, "Menu");
    }

    public void switchToGame() {
        cardLayout.show(cards, "Game");
    }

    public MainPanel getGamePanel() {
        return gamePanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
}
