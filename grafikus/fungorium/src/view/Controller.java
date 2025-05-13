package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;

import model.*;

/**
 * A játékmechanikát vezérlő osztály, amely összeköti a modellt és a nézetet.
 * Kezeli a felhasználói interakciókat, időzítőket és játéklogikát.
 */
public class Controller {
    private Field model;
    private GameFrame view;
    public static javax.swing.Timer timer;
    private int remainingSeconds;
    private TimeHandler timeHandler;
    private Timer sporaTimer;
    private final CustomKeyListener keyListener;

    private TektonView selectedTekton;
    private TektonView selectedSecondTekton;
    private TektonView selectedThirdTekton;
    private RovarView selectedRovar;
    private GombaTestView selectedGombaTest;

    /**
     * Létrehoz egy új Controller példányt a megadott játéktér modellel.
     *
     * @param model a játéktér modellje
     */
    public Controller(Field model) {
        this.model = model;
        timeHandler = new TimeHandler();
        this.view = new GameFrame(); // csak menü jön létre benne

        // KeyListener előkészítés
        this.keyListener = new CustomKeyListener(this);

        // Menü gombok figyelése
        view.getMenuPanel().startAL = e -> {
            try {
                switchToGamePanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
        view.getMenuPanel().exitAL = e -> {
            model.delete();
            timer.stop();
            sporaTimer.cancel();
            timeHandler.shutdown();
            System.exit(0);};
    }

    /**
     * Hibaüzenetet jelenít meg billentyűparancsokkal kapcsolatos hibák esetén.
     *
     * @param error a megjelenítendő hibaüzenet
     */
    public void keyPressedError(String error) {
        JOptionPane.showMessageDialog(null,
                error,
                "Billentyű parancs",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * A játék indításakor hívódik meg, inicializálja az időzítőt.
     */
    public void onStart() {
        int gameTime = view.getMenuPanel().getGameDuration();
        initTimer(1000, gameTime);
    }

    /**
     * Átvált a játékpanelre a menüből, inicializálja a játékot és a kezdőlépéseket.
     *
     * @throws IOException ha a nézet frissítése során hiba történik
     */
    public void switchToGamePanel() throws IOException {
        model.initGame(view.getMenuPanel().getGombasz1Name(),
                view.getMenuPanel().getGombasz2Name(),
                view.getMenuPanel().getRovarasz1Name(),
                view.getMenuPanel().getRovarasz2Name());

        MainPanel panel = new MainPanel(this);
        timeHandler.shutdown();
        timeHandler = new TimeHandler();
        view.setGamePanel(panel);

        panel.getInfoPanel().clearListener = e -> {
            onClearSelection();
            try {
                updateView(model);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        panel.getInfoPanel().exitListener = e -> {
            model.delete();
            timer.stop();
            sporaTimer.cancel();
            timeHandler.shutdown();
            switchToMenuPanel();
        };

        panel.getGamePanel().addKeyListener(keyListener);
        panel.getGamePanel().setFocusable(true);

        view.switchToGame();

        // Első felugró üzenet
        JOptionPane.showMessageDialog(null,
                """
                        Minden játékos végezze el a kezdőlépéseit:
                        Gombászok helyezzenek el 1-1 gombát
                        Rovarászok helyezzenek el 1-1 rovart""",
                "Kezdőlépések",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Játékosok listájának lekérdezése a modeltől
        List<Player> players = model.getPlayers(); // pl. G1, G2, R1, R2

        // Az óra még nem indul el
        kezdoLepesekLepesenkent(panel, players, 0);

        // 20 másodpercenként spóra termelés meghívása minden Gombászra
        sporaTimer = new Timer();
        sporaTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (model) {
                    model.sporaTermeles();
                }
            }
        }, 5000, 5000); // 20 (20000)másodpercenként



        timeHandler.scheduleAtFixedRate(() -> SwingUtilities.invokeLater(() -> {
            for(Player player : players) {
                if(player instanceof Gombasz gsz) {
                    gsz.removeUnconnectedFonalak();
                }
            }
            try {
                updateView(model);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }), 10000, 10000,  this); // ez a this → lockObject, pl. model is lehetne

        onClearSelection();
    }

    /**
     * A játékosok kezdőlépéseit bonyolítja le lépésről lépésre.
     *
     * @param panel a játék főpanelje
     * @param players a játékosok listája
     * @param index az aktuális játékos indexe
     * @throws IOException ha a nézet frissítése során hiba történik
     */
    private void kezdoLepesekLepesenkent(MainPanel panel, List<Player> players, int index) throws IOException {
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
        doneButton.setBackground(new Color(230, 230, 230));
        Dimension buttonSize = new Dimension(160, 30);
        doneButton.setMaximumSize(buttonSize);
        doneButton.setMaximumSize(buttonSize);
        doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        onClearSelection();
        updateView(model);

        panel.getInfoPanel().add(doneButton);
        panel.getInfoPanel().revalidate();
        panel.getInfoPanel().repaint();


        doneButton.addActionListener(e -> {
            panel.getInfoPanel().remove(doneButton);
            panel.getInfoPanel().revalidate();
            panel.getInfoPanel().repaint();

            try {
                kezdoLepesekLepesenkent(panel, players, index + 1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        onClearSelection();
        updateView(model);
    }

    /**
     * Visszavált a menüpanelre a játékból.
     */
    public void switchToMenuPanel() {
        onClearSelection();
        view.switchToMenu();
    }

    /**
     * Törli az aktuális kijelöléseket.
     */
    public void onClearSelection() {
        selectedTekton = null;
        selectedSecondTekton = null;
        selectedThirdTekton = null;
        selectedRovar = null;
        selectedGombaTest = null;

        JComboBox<Object> box = view.getGamePanel().getInfoPanel().getElemek();
        box.removeAllItems();
    }

    /**
     * Kezeli egy tektonlemez kijelölését.
     *
     * @param clicked a kiválasztott tektonlemez nézet
     * @throws IOException ha a nézet frissítése során hiba történik
     */
    public void onTektonSelection(TektonView clicked) throws IOException {
        if (selectedTekton == null) {
            selectedTekton = clicked;
        } else if (selectedSecondTekton == null) {
            selectedSecondTekton = clicked;
        } else if (selectedThirdTekton == null) {
            selectedThirdTekton = clicked;
            view.getGamePanel().getGamePanel().requestFocusInWindow();

        } else {
            onClearSelection();
        }

        // ComboBox frissítés:
        List<Object> options = new ArrayList<>();

        if(selectedTekton != null) {
            if(selectedTekton.getTekton().getRovar() != null) {
                Rovar r = selectedTekton.getTekton().getRovar();
                for(RovarView rv : view.getGamePanel().getGamePanel().getRovarViews()) {
                    if(rv.getRovar().getId() == r.getId()) {
                        options.add(rv);
                    }
                }
            }

            if (clicked.getTekton().getGomba() != null) {
                GombaTest gt = clicked.getTekton().getGomba().getGombatest();
                for(GombaTestView gtv : view.getGamePanel().getGamePanel().getGombaTestViews()) {
                    if(gtv.getGombaTest().getId() == gt.getId()) {
                        options.add(gtv);
                    }
                }
            }

            InfoPanel infoPanel = view.getGamePanel().getInfoPanel();
            JComboBox<Object> comboBox = infoPanel.getElemek();

            for (ActionListener al : comboBox.getActionListeners()) {
                comboBox.removeActionListener(al);
            }

            infoPanel.setOptionsList(options); // ez ne váltson ki eseményt

            comboBox.addActionListener(e -> {
                try {
                    onComboBoxSelection();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                view.getGamePanel().getGamePanel().requestFocusInWindow();
            });


            updateView(model);
        }
    }

    /**
     * Kezeli a ComboBox-ban történő kiválasztást.
     *
     * @throws IOException ha a nézet frissítése során hiba történik
     */
    public void onComboBoxSelection() throws IOException {
        JComboBox<Object> box = view.getGamePanel().getInfoPanel().getElemek();
        Object selected = box.getSelectedItem();

        if (selected == null) {
            return;
        }

        // Egyezés alapján a modellből visszakeressük
        if(selected instanceof RovarView) {
            selectedRovar = (RovarView) selected;
        } else if (selected instanceof GombaTestView) {
            selectedGombaTest = (GombaTestView) selected;
        } else if (selected instanceof TektonView) {
            selectedSecondTekton = (TektonView) selected;
        }

        updateView(model);
    }

    /**
     * Frissíti a játék nézetét a modell aktuális állapota alapján.
     *
     * @param model a játéktér modellje
     * @throws IOException ha a nézet frissítése során hiba történik
     */
    public void updateView(Field model) throws IOException {
        view.getGamePanel().updateView(model);
        view.repaint();
    }

    /**
     * Fonal elvágását végzi el a modellben.
     */
    public void updateModelCutFonal() {
        Tekton _selectedTekton = selectedTekton.getTekton();
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        Rovar _selectedRovar = selectedRovar.getRovar().deepCopy();

        timeHandler.schedule(() -> {
            model.cutFonal(_selectedTekton, _selectedSecondTekton, _selectedRovar);

            SwingUtilities.invokeLater(() -> {
                try {
                    updateView(model);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }, 10000, this); // ez a this → lockObject, pl. model is lehetne
    }

    /**
     * Rovar mozgatását végzi el a modellben.
     *
     * @return igaz, ha a mozgatás sikeres volt
     */
    public boolean updateModelMoveRovar() {
        Rovar _selectedRovar = selectedRovar.getRovar();
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        boolean moved = model.moveRovar(_selectedRovar, _selectedSecondTekton);
        if(model.eatSpora(_selectedRovar)) {
            timeHandler.schedule(() -> {
                _selectedRovar.kepessegekAlaphelyzetbe();
                SwingUtilities.invokeLater(() -> {
                    try {
                        updateView(model);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }, 30000, this); // ez a this → lockObject, pl. model is lehetne

        }
        return moved;
    }

    /**
     * Fonal növesztését végzi el a modellben.
     *
     * @return igaz, ha a növesztés sikeres volt
     */
    public boolean updateModelGrowFonal() {
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        Tekton _selectedThirdTekton = selectedThirdTekton.getTekton();
        GombaTest _gombaTest = selectedGombaTest.getGombaTest();
        return model.growFonal(_selectedSecondTekton, _selectedThirdTekton, _gombaTest.getAlapGomba());
    }

    /**
     * Gombatest növesztését végzi el a modellben.
     *
     * @return igaz, ha a növesztés sikeres volt
     */
    public boolean updateModelGrowGombaTest() {
        Gombasz _selecetdGomba = selectedGombaTest.getGombaTest().getAlapGomba().getGombasz();
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        return model.growGombaTest(_selecetdGomba, _selectedSecondTekton);
    }

    /**
     * Spóra terjesztését végzi el a modellben.
     *
     * @return igaz, ha a terjesztés sikeres volt
     */
    public boolean updateModelSpreadSpora() {
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        Gomba _selectedAlapG = selectedGombaTest.getGombaTest().getAlapGomba();
        return model.spreadSpora(_selectedSecondTekton, _selectedAlapG);
    }

    /**
     * Rovar elfogyasztását végzi el a modellben.
     *
     * @return igaz, ha az elfogyasztás sikeres volt
     */
    public boolean updateModelEatRovar() {
        Gombasz _selectedGombasz = model.getGombaTestById(selectedGombaTest.getId()).getAlapGomba().getGombasz();
        Rovar _selectedRovar = model.getRovarById(selectedRovar.getId());
        return model.eatRovar(_selectedGombasz, _selectedRovar);
    }

    /**
     * Fonal vásárlását végzi el a modellben.
     */
    public void updateModelBuyFonal() {
        Gomba _selectedGomba = selectedGombaTest.getGombaTest().getAlapGomba();
        model.buyFonal(_selectedGomba);
    }

    /**
     * Inicializálja a játék időzítőjét.
     *
     * @param delay az időzítő frissítési gyakorisága milliszekundumban
     * @param duration a játék időtartama másodpercben
     */
    public void initTimer(int delay, int duration) {
        this.remainingSeconds = duration;

        timer = new javax.swing.Timer(delay, e -> {
            remainingSeconds--;
            updateTimer();

            if (remainingSeconds <= 0) {
                timer.stop();
                sporaTimer.cancel();
                timeHandler.shutdown();
                showGameOverDialog();
            }
        });

        timer.start();
    }

    /**
     * Végrehajtja a játékos kezdőlépését.
     *
     * @param currentPlayer az aktuális játékos
     */
    public void performFirstStep(Player currentPlayer) {
        javax.swing.Timer waitTimer = new javax.swing.Timer(100, null); // 100ms-onként ellenőrzés
        waitTimer.addActionListener(e -> {
            if (selectedTekton != null) {
                Tekton target = model.getTektonById(selectedTekton.getId());

                if (currentPlayer instanceof Rovarasz) {
                    Rovar r = model.firstRovar((Rovarasz) currentPlayer, target);
                    if (r != null) {
                        view.getGamePanel().getGamePanel().addRovarView(selectedTekton, r);
                        ((javax.swing.Timer) e.getSource()).stop(); // sikeres -> kilépés
                        try {
                            updateView(model);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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
                            updateView(model);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        ((javax.swing.Timer) e.getSource()).stop(); // sikeres -> kilépés
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

    /**
     * Megjeleníti a játék vége dialógust az eredményekkel.
     */
    private void showGameOverDialog() {
        String eredmeny = "VÉGE!\n" + model.getAllas();
        model.delete();
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

    /**
     * Frissíti az időzítő kijelzőjét a felületen.
     */
    public void updateTimer() {
        JLabel timeLabel = view.getGamePanel().getInfoPanel().getTimeLabel();
        timeLabel.setText("Hátra: " + remainingSeconds + " mp");
    }

    /**
     * @return a játék nézete
     */
    public GameFrame getView() {
        return view;
    }

    /**
     * @return a játéktér modellje
     */
    public Field getModel() {
        return model;
    }

    /**
     * Beállítja a játék nézetét.
     *
     * @param view az új játék nézet
     */
    public void setView(GameFrame view) {
        this.view = view;
    }

    /**
     * @return a kijelölt tektonlemez, vagy null ha nincs kijelölve
     */
    public Tekton getSelectedTekton() {
        return selectedTekton != null ? selectedTekton.getTekton() : null;
    }

    /**
     * @return a kijelölt rovar, vagy null ha nincs kijelölve
     */
    public Rovar getSelectedRovar() {
        return selectedRovar != null ? selectedRovar.getRovar() : null;
    }

    /**
     * @return igaz, ha van kijelölt tektonlemez
     */
    public boolean isSelectedTekton() {
        return selectedTekton != null;
    }

    /**
     * @return igaz, ha van kijelölt második tektonlemez
     */
    public boolean isSelectedSecondTekton() {
        return selectedSecondTekton != null;
    }

    /**
     * @return igaz, ha van kijelölt harmadik tektonlemez
     */
    public boolean isSelectedThirdTekton() {
        return selectedThirdTekton != null;
    }

    /**
     * @return igaz, ha van kijelölt rovar
     */
    public boolean isSelectedRovar() {
        return selectedRovar != null;
    }

    /**
     * @return igaz, ha van kijelölt gombatest
     */
    public boolean isSelectedGombatest() {
        return selectedGombaTest != null;
    }
}