package view;
import model.Field;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A játék fő panel osztálya, amely tartalmazza a játékmező nézetet és az információs panelt.
 * Ez az osztály felelős a teljes játékablak elrendezéséért és a nézet frissítéséért.
 */
public class MainPanel extends JPanel {
    /** A játékmezőt megjelenítő panel */
    private final FieldView gamePanel;
    /** Az információkat és vezérlőgombokat megjelenítő panel */
    private final InfoPanel infoPanel;
    /** A játék vezérlője */
    private final Controller controller;

    /**
     * Létrehoz egy új főpanelt a megadott vezérlővel és időzítővel.
     * Inicializálja a panel komponenseit és beállítja az elrendezést.
     *
     * @param controller A játékvezérlő objektum
     */
    public MainPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        gamePanel = new FieldView(controller);
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(600, 600));

        infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new Dimension(200, 600));

        add(gamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
    }

    /**
     * Visszaadja a játékmezőt megjelenítő panelt.
     *
     * @return A játékmező panel
     */
    public FieldView getGamePanel() {
        return gamePanel;
    }

    /**
     * Visszaadja az információs panelt.
     *
     * @return Az információs panel
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    /**
     * Frissíti a panel megjelenítését a játékmodell aktuális állapota alapján.
     * Frissíti mind a játékmező, mind az információs panel tartalmát.
     *
     * @param model A játék modell objektuma
     * @throws IOException Ha a kép betöltés közben hiba történik
     */
    public void updateView(Field model) throws IOException {
        gamePanel.updateView(model);
        infoPanel.updateView(controller);
    }
}