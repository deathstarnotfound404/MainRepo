package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A játék információs paneljét megvalósító osztály.
 * Ez az osztály felelős az összes játék közben megjelenő információ és kezelőfelület
 * megjelenítéséért, többek között megjeleníti a visszaszámlálót, pontszámokat,
 * a kiválasztott Tektonról és rovarról szóló információkat, valamint a kezelőgombokat.
 */
public class InfoPanel extends JPanel {
    /** A Tektonon végrehajtható műveletek legördülő listája */
    private final JComboBox<Object> optionsOnTekton;
    /** A pontszámok megjelenítésére szolgáló szövegmező */
    private final JTextArea pontszamok;
    /** Az idő értékét megjelenítő címke */
    private final JLabel timeValue;
    /** A kiválasztott Tektonról információt megjelenítő szövegmező */
    private final JTextArea tektonInfo;
    /** A kiválasztott rovarról információt megjelenítő szövegmező */
    private final JTextArea rovarInfo;
    /**Akítv kiválasztások jelölése*/
    private final JTextArea selectedElemek;

    /** A kilépés gomb eseménykezelője */
    public ActionListener exitListener;
    /** A kijelölés törlése gomb eseménykezelője */
    public ActionListener clearListener;

    /**
     * Létrehoz egy új információs panelt a megadott időzítővel.
     * Inicializálja a panel komponenseit és elrendezését.
     */
    public InfoPanel() {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        int panelWidth = this.getPreferredSize().width;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel timerLabel = new JLabel("Visszaszámláló:");
        timerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        timeValue = new JLabel(); // Külső vezérlés frissíti majd
        timeValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        optionsOnTekton = new JComboBox<>();
        optionsOnTekton.setMaximumSize(new Dimension(200, 25));
        optionsOnTekton.addItem("-- Válassz --");

        JLabel pontszamlabel = new JLabel("Pontszámok");
        pontszamlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pontszamlabel.setPreferredSize(new Dimension(200, 20));

        pontszamok = new JTextArea();
        pontszamok.setForeground(Color.GRAY);
        pontszamok.setEditable(false);
        pontszamok.setLineWrap(true);
        pontszamok.setWrapStyleWord(true);
        pontszamok.setBackground(this.getBackground());
        pontszamok.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel selectedeklabel = new JLabel("Selected");
        pontszamlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pontszamlabel.setPreferredSize(new Dimension(200, 20));

        selectedElemek = new JTextArea(
                "Selected Tekton : - \n" +
                "Selected 2. Tekton : - \n" +
                "Selected 3. Tekton : - \n" +
                "Selected Rovar : - \n" +
                "Selected GombaTest : - \n"
        );
        selectedElemek.setForeground(Color.GRAY);
        selectedElemek.setEditable(false);
        selectedElemek.setLineWrap(true);
        selectedElemek.setWrapStyleWord(true);
        selectedElemek.setBackground(this.getBackground());
        selectedElemek.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel tektonInfoLabel = new JLabel("Tekton infó");
        tektonInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tektonInfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tektonInfoLabel.setPreferredSize(new Dimension(panelWidth, tektonInfoLabel.getPreferredSize().height));

        tektonInfo = new JTextArea("Kiválasztott Tekton: -");
        tektonInfo.setForeground(Color.GRAY);
        tektonInfo.setEditable(false);
        tektonInfo.setLineWrap(true);
        tektonInfo.setWrapStyleWord(true);
        tektonInfo.setBackground(this.getBackground());
        tektonInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //tektonInfo.setMaximumSize(new Dimension(180, 60));

        JLabel rovarInfoLabel = new JLabel("Rovar infó");
        rovarInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        rovarInfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rovarInfoLabel.setPreferredSize(new Dimension(panelWidth, rovarInfoLabel.getPreferredSize().height));

        rovarInfo = new JTextArea("Kiválasztott Rovar: -");
        rovarInfo.setForeground(Color.GRAY);
        rovarInfo.setEditable(false);
        rovarInfo.setLineWrap(true);
        rovarInfo.setWrapStyleWord(true);
        rovarInfo.setBackground(this.getBackground());
        rovarInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //rovarInfo.setMaximumSize(new Dimension(180, 60));

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(230, 230, 230));
        JButton clearButton = new JButton("Clear Selection");
        clearButton.setBackground(new Color(230, 230, 230));
        Dimension buttonSize = new Dimension(160, 30);
        exitButton.setMaximumSize(buttonSize);
        clearButton.setMaximumSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(timerLabel);
        add(timeValue);
        add(new JLabel("Kiválasztás:"));
        add(optionsOnTekton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(pontszamlabel);
        add(pontszamok);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(selectedeklabel);
        add(selectedElemek);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(tektonInfoLabel);
        add(tektonInfo);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(rovarInfoLabel);
        add(rovarInfo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(clearButton);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(exitButton);
        add(Box.createRigidArea(new Dimension(0, 5)));

        clearButton.addActionListener(e -> {
            if (clearListener != null) clearListener.actionPerformed(e);
        });

        exitButton.addActionListener(e -> {
            if (exitListener != null) exitListener.actionPerformed(e);
        });
    }

    /**
     * Visszaadja a Tektonon végrehajtható műveletek legördülő listáját.
     *
     * @return A legördülő lista objektum
     */
    public JComboBox<Object> getElemek() {
        return optionsOnTekton;
    }

    /**
     * Frissíti az információs panel megjelenítését a vezérlő aktuális állapota alapján.
     * Frissíti a pontszámokat, valamint a kiválasztott Tekton és rovar információit.
     *
     * @param controller A játékvezérlő objektum
     */
    public void updateView(Controller controller) {
        pontszamok.setText(controller.getModel().getAllas());

        selectedElemek.setText(controller.activeSelections());

        String tektonText = (controller.getSelectedTekton() != null)
                ? controller.getSelectedTekton().toStringUI()
                : "";
        tektonInfo.setText(tektonText);

        String rovarText = (controller.getSelectedRovar() != null)
                ? controller.getSelectedRovar().toStringUI()
                : "";
        rovarInfo.setText(rovarText);
    }

    /**
     * Beállítja a Tektonon végrehajtható műveletek listáját.
     *
     * @param opts A megjelenítendő műveletek listája
     */
    public void setOptionsList(List<Object> opts) {
        optionsOnTekton.removeAllItems();
        optionsOnTekton.addItem("-- Válassz --");
        for (Object opt : opts) {
            optionsOnTekton.addItem(opt);
        }
    }

    /**
     * Visszaadja az idő értékét megjelenítő címkét.
     *
     * @return Az idő címke
     */
    public JLabel getTimeLabel() {
        return timeValue;
    }
}