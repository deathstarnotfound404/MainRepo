package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class InfoPanel extends JPanel {
    private Timer timer;
    private JComboBox<Object> optionsOnTekton;
    private JTextArea pontszamok;
    private JButton exitButton, clearButton;
    private List<String> options;
    private JLabel timerLabel;
    private JLabel timeValue;
    private JTextArea tektonInfo;
    private JTextArea rovarInfo;

    private JLabel pontszamlabel;
    private JLabel tektonInfoLabel;
    private JLabel rovarInfoLabel;

    public ActionListener exitListener;
    public ActionListener clearListener;

    public InfoPanel(Timer t) {
        this.timer = t;
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        int panelWidth = this.getPreferredSize().width;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        timerLabel = new JLabel("Visszaszámláló:");
        timerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        timeValue = new JLabel(); // Külső vezérlés frissíti majd
        timeValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        optionsOnTekton = new JComboBox<>();
        optionsOnTekton.setMaximumSize(new Dimension(200, 25));

        pontszamlabel = new JLabel("Pontszámok");
        pontszamlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pontszamlabel.setPreferredSize(new Dimension(200, 20));

        pontszamok = new JTextArea();
        pontszamok.setForeground(Color.GRAY);

        pontszamok.setEditable(false);
        pontszamok.setLineWrap(true);
        pontszamok.setWrapStyleWord(true);
        pontszamok.setBackground(this.getBackground());
        pontszamok.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        tektonInfoLabel = new JLabel("Tekton infó");
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

        rovarInfoLabel = new JLabel("Rovar infó");
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

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(230, 230, 230));
        clearButton = new JButton("Clear Selection");
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

    public Timer getTimer() {
        return timer;
    }

    public JComboBox<Object> getElemek() {
        return optionsOnTekton;
    }

    public JTextArea getPontszamok() {
        return pontszamok;
    }

    public void updateView(Controller controller) {
        pontszamok.setText(controller.getModel().getAllas());

        String tektonText = (controller.getSelectedTekton() != null)
                ? controller.getSelectedTekton().toStringUI()
                : "";
        tektonInfo.setText(tektonText);

        String rovarText = (controller.getSelectedRovar() != null)
                ? controller.getSelectedRovar().toStringUI()
                : "";
        rovarInfo.setText(rovarText);
    }

    public void setOptionsList(List<Object> opts) {
        optionsOnTekton.removeAllItems();
        for (Object opt : opts) {
            optionsOnTekton.addItem(opt);
        }
    }

    public JLabel getTimeLabel() {
        return timeValue;
    }
}
