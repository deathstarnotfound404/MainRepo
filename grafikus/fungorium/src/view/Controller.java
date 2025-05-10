package view;

import javax.swing.*;
import javax.swing.Timer;
import java.io.IOException;
import java.util.*;

import model.*;

public class Controller {
    private static Field model;
    private GameFrame view;
    public static Timer timer;
    private int gameTime;
    private int remainingSeconds;
    private long startTime;


    private CustomKeyListener keyListener;

    private TektonView selectedTekton;
    private TektonView selectedSecondTekton;
    private RovarView selectedRovar;
    private GombaTestView selectedGombaTest;

    public Controller(Field model) {
        Controller.model = model;
        this.view = new GameFrame(); // csak menü jön létre benne

        // KeyListener előkészítés
        this.keyListener = new CustomKeyListener(this);

        // Menü gombok figyelése
        view.getMenuPanel().startAL = e -> switchToGamePanel();
        view.getMenuPanel().exitAL = e -> System.exit(0);
    }

    public void onStart() {
        gameTime = view.getMenuPanel().getGameDuration();
        initTimer(1000, gameTime);
    }

    public void switchToGamePanel() {
        //TODO ellenőrizni h van-e min 1-1 játékos

        model.initGame(view.getMenuPanel().getGombasz1Name(),
                view.getMenuPanel().getGombasz2Name(),
                view.getMenuPanel().getRovarasz1Name(),
                view.getMenuPanel().getRovarasz2Name());

        MainPanel panel = new MainPanel(this, timer);
        view.setGamePanel(panel);

        panel.getInfoPanel().clearListener = e -> onClearSelection();
        panel.getInfoPanel().exitListener = e -> switchToMenuPanel();

        panel.getGamePanel().addKeyListener(keyListener);
        panel.getGamePanel().setFocusable(true);

        view.switchToGame();

        // Első felugró üzenet
        JOptionPane.showMessageDialog(null,
                "Minden játékos végezze el a kezdőlépéseit:\n" +
                        "Gombászok helyezzenek el 1-1 gombát\n" +
                        "Rovarászok helyezzenek el 1-1 rovart",
                "Kezdőlépések",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Játékosok listájának lekérdezése a modeltől
        List<Player> players = model.getPlayers(); // pl. G1, G2, R1, R2

        // Az óra még nem indul el
        kezdoLepesekLepesenkent(panel, players, 0);
    }

    private void kezdoLepesekLepesenkent(MainPanel panel, List<Player> players, int index) {
        if (index >= players.size()) {
            // Végére értünk a kezdőlépéseknek → vége dialog és indul az óra
            JOptionPane.showMessageDialog(null,
                    "Kezdőlépések elvégezve!",
                    "Indul a játék",
                    JOptionPane.INFORMATION_MESSAGE
            );
            onStart();
            return;
        }

        Player currentPlayer = players.get(index);
        JOptionPane.showMessageDialog(null,
                currentPlayer.getName() + " végezze el a kezdőlépését!",
                "Kezdőlépés",
                JOptionPane.INFORMATION_MESSAGE
        );

        performFirstStep(currentPlayer);

        // Gomb megjelenítése az InfoPanelen
        JButton doneButton = new JButton("Kezdőlépés kész (" + currentPlayer.getName() + ")");
        onClearSelection();
        panel.getInfoPanel().add(doneButton);
        panel.getInfoPanel().revalidate();
        panel.getInfoPanel().repaint();


        doneButton.addActionListener(e -> {
            panel.getInfoPanel().remove(doneButton);
            panel.getInfoPanel().revalidate();
            panel.getInfoPanel().repaint();

            kezdoLepesekLepesenkent(panel, players, index + 1);
        });
    }




    public void switchToMenuPanel() {
        onClearSelection();
        view.switchToMenu();
    }

    public void onClearSelection() {
        selectedTekton = null;
        selectedSecondTekton = null;
        selectedRovar = null;
        selectedGombaTest = null;

        JComboBox<String> box = view.getGamePanel().getInfoPanel().getElemek();
        box.removeAllItems();
    }

    public void onExit() {
        onClearSelection();
        switchToMenuPanel();
    }

    public void onTektonSelection(TektonView clicked) {
        if (selectedTekton == null) {
            selectedTekton = clicked;
        } else if (selectedSecondTekton == null) {
            selectedSecondTekton = clicked;
        } else {
            onClearSelection();
            selectedTekton = clicked;
        }

        // ComboBox frissítés:
        List<String> options = new ArrayList<>();

        if(model.getRovarokOnTekton(model.getTektonById(selectedTekton.getId())) != null) {
            for (Rovar r : model.getRovarokOnTekton(model.getTektonById(selectedTekton.getId()))) {
                if(r != null) {
                    options.add("Rovar #" + r.getId());
                }
            }
        }

        if (model.getGombaTestekOnTekton(model.getTektonById(clicked.getId())) != null) {
            for (GombaTest gt : model.getGombaTestekOnTekton(model.getTektonById(clicked.getId()))) {
                if(gt != null) {
                    options.add("GombaTest #" + gt.getId());
                }
            }
        }

        //TODO a kombobox tobbi eleme

        InfoPanel infoPanel = view.getGamePanel().getInfoPanel();
        infoPanel.setOptionsList(options);

        // ComboBox listener figyelés:
        infoPanel.getElemek().addActionListener(e -> onComboBoxSelection());
    }

    public void onComboBoxSelection() {
        JComboBox<String> box = view.getGamePanel().getInfoPanel().getElemek();
        Object selected = box.getSelectedItem();

        if (selected == null) {
            return;
        }

        // Eredmény visszaállítása alaphelyzetbe
        selectedRovar = null;
        selectedGombaTest = null;

        // Egyezés alapján a modellből visszakeressük
        if(selected instanceof RovarView) {
            selectedRovar = (RovarView) selected;
        } else if (selected instanceof GombaTestView) {
            selectedGombaTest = (GombaTestView) selected;
        }
    }

    public void updateView() {
        view.getGamePanel().updateView();
    }

    // Modellműveletek
    public boolean updateModelCutFonal() {
        Tekton _selectedTekton = model.getTektonById(selectedTekton.getId());
        Tekton _selectedSecondTekton = model.getTektonById(selectedSecondTekton.getId());
        Rovar _selectedRovar = model.getRovarById(selectedRovar.getId());
        return model.cutFonal(_selectedTekton, _selectedSecondTekton, _selectedRovar);
    }

    public boolean updateModelMoveRovar() {
        Rovar _selectedRovar = model.getRovarById(selectedRovar.getId());
        Tekton _selectedSecondTekton = model.getTektonById(selectedSecondTekton.getId());
        return model.moveRovar(_selectedRovar, _selectedSecondTekton);
    }

    public boolean updateModelGrowFonal() {
        Tekton _selectedTekton = model.getTektonById(selectedTekton.getId());
        Tekton _selectedSecondTekton = model.getTektonById(selectedSecondTekton.getId());
        GombaTest _gombaTest = model.getGombaTestById(selectedGombaTest.getId());
        return model.growFonal(_selectedTekton, _selectedSecondTekton, _gombaTest.getAlapGomba());
    }

    public boolean updateModelGrowGombaTest() {
        Gombasz _selecetdGomba = model.getGombaTestById(selectedGombaTest.getId()).getAlapGomba().getGombasz();
        Tekton _selectedTekton = model.getTektonById(selectedTekton.getId());
        return model.growGombaTest(_selecetdGomba, _selectedTekton);
    }

    public boolean updateModelSpreadSpora() {
        Tekton _selectedTekton = model.getTektonById(selectedTekton.getId());
        Gomba _selectedAlapG = model.getGombaTestById(selectedGombaTest.getId()).getAlapGomba();
        return model.spreadSpora(_selectedTekton, _selectedAlapG);
    }

    public boolean updateModelEatRovar() {
        Gombasz _selectedGombasz = model.getGombaTestById(selectedGombaTest.getId()).getAlapGomba().getGombasz();
        Tekton _selectedTekton = model.getTektonById(selectedTekton.getId());
        Rovar _selectedRovar = model.getRovarById(selectedRovar.getId());
        return model.eatRovar(_selectedGombasz, _selectedTekton, _selectedRovar);
    }

    public boolean updateModelBuyFonal() {
        Gomba _selectedGomba = model.getGombaTestById(selectedGombaTest.getId()).getAlapGomba();
        return model.buyFonal(_selectedGomba);
    }

    public void initTimer(int delay, int duration) {
        this.remainingSeconds = duration;
        this.startTime = System.currentTimeMillis();

        timer = new Timer(delay, e -> {
            remainingSeconds--;
            updateTimer();

            if (remainingSeconds <= 0) {
                timer.stop();
                showGameOverDialog();
            }
        });

        timer.start();
    }

    /*
    public void performFirstStep(Player currentPlayer) {
        Timer waitTimer = new Timer(100, null); // 100ms-onként ellenőrzés, mert nem lehet while al várni a user inputját

        waitTimer.addActionListener(e -> {
            if (selectedTekton != null) {
                ((Timer) e.getSource()).stop(); // leállítjuk a timer-t

                Tekton target = model.getTektonById(selectedTekton.getId());

                if (currentPlayer instanceof Rovarasz) {
                    Rovar r = model.firstRovar((Rovarasz) currentPlayer, target);
                    view.getGamePanel().getGamePanel().addRovarView(selectedTekton,  r.getId());

                } else if (currentPlayer instanceof Gombasz) {
                    Gomba g = model.firstGomba((Gombasz) currentPlayer, target);
                    if (g != null) {
                        try {
                            view.getGamePanel().getGamePanel().addGombaTestView(selectedTekton, "/gombatest.png", g.getId());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        ((Timer) e.getSource()).stop(); // sikeres -> kilépés
                        updateView();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ide nem helyezhető gombatest!",
                                "Érvénytelen kezdőlépés",
                                JOptionPane.WARNING_MESSAGE);
                        selectedTekton = null; // új próbálkozásra várunk
                    }
                }
            }
        });

        waitTimer.start();
    }

     */

    public void performFirstStep(Player currentPlayer) {
        Timer waitTimer = new Timer(100, null); // 100ms-onként ellenőrzés
        waitTimer.addActionListener(e -> {
            if (selectedTekton != null) {
                Tekton target = model.getTektonById(selectedTekton.getId());

                if (currentPlayer instanceof Rovarasz) {
                    Rovar r = model.firstRovar((Rovarasz) currentPlayer, target);
                    if (r != null) {
                        view.getGamePanel().getGamePanel().addRovarView(selectedTekton, r.getId());
                        ((Timer) e.getSource()).stop(); // sikeres -> kilépés
                        updateView();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ide nem helyezhető rovar!",
                                "Érvénytelen kezdőlépés",
                                JOptionPane.WARNING_MESSAGE);
                        selectedTekton = null; // új próbálkozásra várunk
                    }

                } else if (currentPlayer instanceof Gombasz) {
                    Gomba g = model.firstGomba((Gombasz) currentPlayer, target);
                    if (g != null) {
                        try {
                            view.getGamePanel().getGamePanel().addGombaTestView(selectedTekton, "/gombatest.png", g.getId());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        ((Timer) e.getSource()).stop(); // sikeres -> kilépés
                        updateView();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Ide nem helyezhető gombatest!",
                                "Érvénytelen kezdőlépés",
                                JOptionPane.WARNING_MESSAGE);
                        selectedTekton = null; // új próbálkozásra várunk
                    }
                }
            }
        });
        waitTimer.start();
    }

    private void showGameOverDialog() {
        String eredmeny = "VÉGE!\nPontszámok:\n" + view.getGamePanel().getInfoPanel().getPontszamok().getText();

        int valasz = JOptionPane.showOptionDialog(
                null,
                eredmeny,
                "Játék vége",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Vissza a menübe"},
                "Vissza a menübe"
        );

        if (valasz == 0) {
            switchToMenuPanel();
        }
    }

    public void updateTimer() {
        JLabel timeLabel = view.getGamePanel().getInfoPanel().getTimeLabel();
        timeLabel.setText("Hátra: " + remainingSeconds + " mp");
    }

    // Getters
    public GameFrame getView() {
        return view;
    }

    public Field getModel() {
        return model;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setView(GameFrame view) {
        this.view = view;
    }
}
