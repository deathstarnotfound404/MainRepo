package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class InfoPanel extends JPanel {
    private Timer timer;
    private JComboBox<Object> optionsOnTekton;
    private JLabel pontszamok;
    private JButton exitButton, clearButton;
    private List<String> options;
    JLabel timerLabel;
    JLabel timeValue;

    public ActionListener exitListener;
    public ActionListener clearListener;

    public InfoPanel(Timer t) {
        this.timer = t;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        timerLabel = new JLabel("Visszaszámláló: ");
        timeValue = new JLabel(); // Külső vezérlés fogja frissíteni

        optionsOnTekton = new JComboBox<>();
        pontszamok = new JLabel("Pontszámok: G1 - 0 | G2 - 0 | R1 - 0 | R2 - 0");

        exitButton = new JButton("Kilépés");
        clearButton = new JButton("Clear");

        add(timerLabel);
        add(timeValue);
        add(new JLabel("Kiválasztás:"));
        add(optionsOnTekton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(pontszamok);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(clearButton);
        add(exitButton);

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

    public JLabel getPontszamok() {
        return pontszamok;
    }

    public void updateView() {
    }

    /*
    public void setOptionsList(List<String> opts) {
        optionsOnTekton.removeAllItems();
        for (String opt : opts) {
            optionsOnTekton.addItem(opt);
        }
    }
     */

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
