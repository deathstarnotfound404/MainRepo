package view;

import javax.swing.*;
import java.awt.*;

/**
 * A játék fő ablakát reprezentáló osztály, amely a különböző nézetek között vált.
 * A CardLayout segítségével teszi lehetővé a menü és a játékpanel közötti váltást.
 */
public class GameFrame extends JFrame {
    /** A különböző nézetek közötti váltást kezelő elrendezés-kezelő. */
    private final CardLayout cardLayout;
    /** A játék fő panele, amely a játékelemeket tartalmazza. */
    private MainPanel gamePanel;
    /** A menü panel, amely a játék indításához szükséges vezérlőelemeket tartalmazza. */
    private final MenuPanel menuPanel;
    /** A kártyákat tartalmazó fő konténer panel. */
    private final JPanel cards;

    /**
     * Létrehozza a játék fő ablakát és inicializálja a kezdeti beállításokat.
     * Beállítja az ablak címét, ikonját, méretét és elhelyezkedését.
     * Inicializálja a kártya elrendezést és a menüpanelt.
     */
    public GameFrame() {
        super("Fungorium");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png"));
        setIconImage(icon);
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

    /**
     * Beállítja a játék fő panelét és hozzáadja a kártya elrendezéshez.
     *
     * @param panel a használandó játékpanel
     */
    public void setGamePanel(MainPanel panel) {
        this.gamePanel = panel;
        cards.add(gamePanel, "Game");
    }

    /**
     * Átvált a menü nézetre.
     * A CardLayout segítségével megjeleníti a menü panelt.
     */
    public void switchToMenu() {
        cardLayout.show(cards, "Menu");
    }

    /**
     * Átvált a játék nézetre.
     * A CardLayout segítségével megjeleníti a játék panelt.
     */
    public void switchToGame() {
        cardLayout.show(cards, "Game");
    }

    /**
     * Visszaadja a játék fő panelét.
     *
     * @return a játék fő panelje
     */
    public MainPanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Visszaadja a menü panelt.
     *
     * @return a menü panel
     */
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
}