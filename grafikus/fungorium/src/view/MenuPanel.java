package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 * A játék kezdeti menüjét megvalósító panel osztály.
 * Ez az osztály felelős a játék indítása előtt szükséges beállítások és
 * játékos nevek bekéréséért, valamint a játékidő meghatározásáért.
 */
public class MenuPanel extends JPanel {
    /** A játékidő beviteli mező */
    private final JTextField timeField;

    /** A gombász játékosok neveinek beviteli mezői */
    private JTextField gombasz1Field, gombasz2Field;
    /** A gombász játékosok nevei */
    private String gombasz1, gombasz2;

    /** A rovarász játékosok neveinek beviteli mezői */
    private JTextField rovarasz1Field, rovarasz2Field;
    /** A rovarász játékosok nevei */
    private String rovarasz1, rovarasz2;

    /** Az eseménykezelők a kilépéshez és a játék indításához */
    public ActionListener exitAL, startAL;

    /**
     * Létrehoz egy új menüpanelt, bekéri a játékosok számát,
     * és inicializálja a panel komponenseit.
     */
    public MenuPanel() {
        // Bekérés játékos számokról
        int gCount = askPlayerCount("Hány gombász játszik? (1 vagy 2):");
        int rCount = askPlayerCount("Hány rovarász játszik? (1 vagy 2):");

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 5-ös margó minden elem körül
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("The Last of Us", SwingConstants.CENTER);
        add(titleLabel, gbc);
        Font titleFont = new Font("Times New Roman", Font.BOLD, 80);
        titleLabel.setFont(titleFont);

        gbc.gridy++;
        JLabel titleLabel2 = new JLabel("Fungorium", SwingConstants.CENTER);
        add(titleLabel2, gbc);
        Font titleFont2 = new Font("Times New Roman", Font.BOLD, 20);
        titleLabel2.setFont(titleFont2);

        gbc.gridy++;
        JLabel instructionLabel = new JLabel("Add meg a játék adatait", SwingConstants.CENTER);
        add(instructionLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel timeLabel = new JLabel("Játékidő (másodperc):");
        add(timeLabel, gbc);

        gbc.gridx = 1;
        timeField = new JTextField();
        add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        if (gCount >= 1) {
            JLabel gombasz1Label = new JLabel("Gombász 1 neve:");
            add(gombasz1Label, gbc);
            gbc.gridx = 1;
            gombasz1Field = new JTextField();
            gombasz1Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> gombasz1 = getTrimmedOrNull(gombasz1Field.getText())));
            add(gombasz1Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }
        if (gCount == 2) {
            JLabel gombasz2Label = new JLabel("Gombász 2 neve:");
            add(gombasz2Label, gbc);
            gbc.gridx = 1;
            gombasz2Field = new JTextField();
            gombasz2Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> gombasz2 = getTrimmedOrNull(gombasz2Field.getText())));
            add(gombasz2Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }

        if (rCount >= 1) {
            JLabel rovarasz1Label = new JLabel("Rovarász 1 neve:");
            add(rovarasz1Label, gbc);
            gbc.gridx = 1;
            rovarasz1Field = new JTextField();
            rovarasz1Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> rovarasz1 = getTrimmedOrNull(rovarasz1Field.getText())));
            add(rovarasz1Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }
        if (rCount == 2) {
            JLabel rovarasz2Label = new JLabel("Rovarász 2 neve:");
            add(rovarasz2Label, gbc);
            gbc.gridx = 1;
            rovarasz2Field = new JTextField();
            rovarasz2Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> rovarasz2 = getTrimmedOrNull(rovarasz2Field.getText())));
            add(rovarasz2Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }

        JButton startButton = new JButton("Játék indítása");
        JButton exitButton = new JButton("Kilépés");

        startButton.setBackground(new Color(230, 230, 230));
        exitButton.setBackground(new Color(230, 230, 230));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(startButton, gbc);
        gbc.gridx = 1;
        add(exitButton, gbc);

        startButton.addActionListener(e -> {
            if (startAL != null) startAL.actionPerformed(e);
        });

        exitButton.addActionListener(e -> {
            if (exitAL != null) exitAL.actionPerformed(e);
        });
    }

    /**
     * Egy felugró ablakban bekéri a játékos számot a megadott határértékek között.
     *
     * @param message A felhasználónak megjelenítendő üzenet
     * @return A felhasználó által megadott érték
     */
    private int askPlayerCount(String message) {
        int value;
        do {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) System.exit(0); // felhasználó kilépett
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                value = -1;
            }
        } while (value < 1 || value > 2);
        return value;
    }

    /**
     * Eltávolítja a szöveg elejéről és végéről a whitespace karaktereket,
     * ha az eredmény üres string, akkor null-t ad vissza.
     *
     * @param text A feldolgozandó szöveg
     * @return A trimelt szöveg vagy null, ha üres
     */
    private String getTrimmedOrNull(String text) {
        String trimmed = text.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    /**
     * Visszaadja a beállított játékidő értékét másodpercben.
     * Ha érvénytelen érték van megadva, 60 másodperces alapértelmezett értéket ad vissza.
     *
     * @return A játék időtartama másodpercben
     */
    public int getGameDuration() {
        try {
            return Integer.parseInt(timeField.getText());
        } catch (NumberFormatException e) {
            return 60;
        }
    }

    /**
     * Visszaadja az első gombász játékos nevét.
     *
     * @return Az első gombász játékos neve
     */
    public String getGombasz1Name() { return gombasz1; }

    /**
     * Visszaadja a második gombász játékos nevét.
     *
     * @return A második gombász játékos neve
     */
    public String getGombasz2Name() { return gombasz2; }

    /**
     * Visszaadja az első rovarász játékos nevét.
     *
     * @return Az első rovarász játékos neve
     */
    public String getRovarasz1Name() { return rovarasz1; }

    /**
     * Visszaadja a második rovarász játékos nevét.
     *
     * @return A második rovarász játékos neve
     */
    public String getRovarasz2Name() { return rovarasz2; }
}