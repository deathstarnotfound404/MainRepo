package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JLabel titleLabel, instructionLabel, timeLabel;
    private JTextField timeField;
    private int time;

    private JLabel gombasz1Label, gombasz2Label;
    private JTextField gombasz1Field, gombasz2Field;
    private String gombasz1, gombasz2;

    private JLabel rovarasz1Label, rovarasz2Label;
    private JTextField rovarasz1Field, rovarasz2Field;
    private String rovarasz1, rovarasz2;

    private JButton startButton, exitButton;

    public ActionListener exitAL, startAL;

    public MenuPanel() {
        setLayout(new GridLayout(10, 2));

        titleLabel = new JLabel("Gomba-Rovar Játék", SwingConstants.CENTER);
        instructionLabel = new JLabel("Add meg a játék adatait", SwingConstants.CENTER);
        timeLabel = new JLabel("Játékidő (másodperc):");
        timeField = new JTextField();

        gombasz1Label = new JLabel("Gombász 1 neve:");
        gombasz2Label = new JLabel("Gombász 2 neve:");
        gombasz1Field = new JTextField();
        gombasz2Field = new JTextField();

        rovarasz1Label = new JLabel("Rovarász 1 neve:");
        rovarasz2Label = new JLabel("Rovarász 2 neve:");
        rovarasz1Field = new JTextField();
        rovarasz2Field = new JTextField();

        startButton = new JButton("Játék indítása");
        exitButton = new JButton("Kilépés");

        add(titleLabel);
        add(instructionLabel);
        add(timeLabel);
        add(timeField);
        add(gombasz1Label);
        add(gombasz1Field);
        add(gombasz2Label);
        add(gombasz2Field);
        add(rovarasz1Label);
        add(rovarasz1Field);
        add(rovarasz2Label);
        add(rovarasz2Field);
        add(startButton);
        add(exitButton);

        startButton.addActionListener(e -> {
            if (startAL != null) startAL.actionPerformed(e);
        });

        exitButton.addActionListener(e -> {
            if (exitAL != null) exitAL.actionPerformed(e);
        });
    }

    public int getGameDuration() {
        try {
            return Integer.parseInt(timeField.getText());
        } catch (NumberFormatException e) {
            return 60;
        }
    }

    public String getGombasz1Name() {
        return gombasz1Field.getText();
    }

    public String getGombasz2Name() {
        return gombasz2Field.getText();
    }

    public String getRovarasz1Name() {
        return rovarasz1Field.getText();
    }

    public String getRovarasz2Name() {
        return rovarasz2Field.getText();
    }
}
