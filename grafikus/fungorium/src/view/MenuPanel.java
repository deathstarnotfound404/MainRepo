package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel {
    private JLabel titleLabel, instructionLabel, timeLabel;
    private JTextField timeField;

    private JLabel gombasz1Label, gombasz2Label;
    private JTextField gombasz1Field, gombasz2Field;
    private String gombasz1, gombasz2;

    private JLabel rovarasz1Label, rovarasz2Label;
    private JTextField rovarasz1Field, rovarasz2Field;
    private String rovarasz1, rovarasz2;

    private JButton startButton, exitButton;

    public ActionListener exitAL, startAL;

    public MenuPanel() {
        // Bekérés játékos számokról
        int gCount = askPlayerCount("Hány gombász játszik? (1 vagy 2):", 1, 2);
        int rCount = askPlayerCount("Hány rovarász játszik? (1 vagy 2):", 1, 2);

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 5-ös margó minden elem körül
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        titleLabel = new JLabel("Last of us", SwingConstants.CENTER);
        add(titleLabel, gbc);

        gbc.gridy++;
        instructionLabel = new JLabel("Add meg a játék adatait", SwingConstants.CENTER);
        add(instructionLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        timeLabel = new JLabel("Játékidő (másodperc):");
        add(timeLabel, gbc);

        gbc.gridx = 1;
        timeField = new JTextField();
        add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        if (gCount >= 1) {
            gombasz1Label = new JLabel("Gombász 1 neve:");
            add(gombasz1Label, gbc);
            gbc.gridx = 1;
            gombasz1Field = new JTextField();
            gombasz1Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
                gombasz1 = getTrimmedOrNull(gombasz1Field.getText());
            }));
            add(gombasz1Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }
        if (gCount == 2) {
            gombasz2Label = new JLabel("Gombász 2 neve:");
            add(gombasz2Label, gbc);
            gbc.gridx = 1;
            gombasz2Field = new JTextField();
            gombasz2Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
                gombasz2 = getTrimmedOrNull(gombasz2Field.getText());
            }));
            add(gombasz2Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }

        if (rCount >= 1) {
            rovarasz1Label = new JLabel("Rovarász 1 neve:");
            add(rovarasz1Label, gbc);
            gbc.gridx = 1;
            rovarasz1Field = new JTextField();
            rovarasz1Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
                rovarasz1 = getTrimmedOrNull(rovarasz1Field.getText());
            }));
            add(rovarasz1Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }
        if (rCount == 2) {
            rovarasz2Label = new JLabel("Rovarász 2 neve:");
            add(rovarasz2Label, gbc);
            gbc.gridx = 1;
            rovarasz2Field = new JTextField();
            rovarasz2Field.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
                rovarasz2 = getTrimmedOrNull(rovarasz2Field.getText());
            }));
            add(rovarasz2Field, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
        }

        startButton = new JButton("Játék indítása");
        exitButton = new JButton("Kilépés");

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

    private int askPlayerCount(String message, int min, int max) {
        int value;
        do {
            String input = JOptionPane.showInputDialog(null, message);
            if (input == null) System.exit(0); // felhasználó kilépett
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                value = -1;
            }
        } while (value < min || value > max);
        return value;
    }

    private String getTrimmedOrNull(String text) {
        String trimmed = text.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public int getGameDuration() {
        try {
            return Integer.parseInt(timeField.getText());
        } catch (NumberFormatException e) {
            return 60;
        }
    }

    public String getGombasz1Name() { return gombasz1; }

    public String getGombasz2Name() { return gombasz2; }

    public String getRovarasz1Name() { return rovarasz1; }

    public String getRovarasz2Name() { return rovarasz2; }
}

