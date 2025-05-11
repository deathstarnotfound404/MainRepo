package view;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import model.*;

public class Controller {
    private static Field model;
    private GameFrame view;
    public static javax.swing.Timer timer;
    private int gameTime;
    private int remainingSeconds;
    private long startTime;
    private java.util.Timer sporaTimer;
    private java.util.Timer fonalTorloTimer;
    private TimeHandler timeHandler;


    private CustomKeyListener keyListener;

    private TektonView selectedTekton;
    private TektonView selectedSecondTekton;
    private TektonView selectedThirdTekton;
    private RovarView selectedRovar;
    private GombaTestView selectedGombaTest;

    public Controller(Field model) {
        Controller.model = model;
        timeHandler = new TimeHandler();
        this.view = new GameFrame(); // csak menü jön létre benne

        // KeyListener előkészítés
        this.keyListener = new CustomKeyListener(this);

        // Menü gombok figyelése
        view.getMenuPanel().startAL = e -> switchToGamePanel();
        view.getMenuPanel().exitAL = e -> System.exit(0);
    }

    public boolean isSelectedGombatest() {
        return selectedGombaTest != null;
    }

    public boolean isSelctedRovar() {
        return selectedRovar != null;
    }

    public void keyPressedError(String error) {
        JOptionPane.showMessageDialog(null,
                error,
                "Billentyű parancs",
                JOptionPane.INFORMATION_MESSAGE
        );
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


/*
        timeHandler.scheduleAtFixedRate(() -> {
            try {
                updateView(model);
                System.out.println("updateView");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }, 5000, 5000, this); // 10 sec delay + 10 sec period

 */

        timeHandler.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                System.out.println(">");
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
            });
        }, 10000, 10000,  this); // ez a this → lockObject, pl. model is lehetne

        onClearSelection();
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
        selectedThirdTekton = null;
        selectedRovar = null;
        selectedGombaTest = null;

        JComboBox<Object> box = view.getGamePanel().getInfoPanel().getElemek();
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

            //TODO a kombobox tobbi eleme

            InfoPanel infoPanel = view.getGamePanel().getInfoPanel();
            infoPanel.setOptionsList(options);

            // ComboBox listener figyelés:
            infoPanel.getElemek().addActionListener(e -> {
                onComboBoxSelection();
                view.getGamePanel().getGamePanel().requestFocusInWindow();
            });
        }
    }

    public void onComboBoxSelection() {
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
    }

    public void updateView(Field model) throws IOException {
        view.getGamePanel().updateView(model);
        view.repaint();
    }

    // Modellműveletek
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


        //return model.cutFonal(_selectedTekton, _selectedSecondTekton, _selectedRovar);
    }

    public boolean updateModelMoveRovar() {
        Rovar _selectedRovar = selectedRovar.getRovar();
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        boolean moved = model.moveRovar(_selectedRovar, _selectedSecondTekton);
        if(model.eatSpora(_selectedRovar)) {
            //Időzítés - reset rovar
            /*
            timeHandler.schedule(() -> {
                _selectedRovar.kepessegekAlaphelyzetbe();
            }, 30000, this);

             */
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

    public boolean updateModelGrowFonal() {
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        Tekton _selectedThirdTekton = selectedThirdTekton.getTekton();
        GombaTest _gombaTest = selectedGombaTest.getGombaTest();
        return model.growFonal(_selectedSecondTekton, _selectedThirdTekton, _gombaTest.getAlapGomba());
    }

    public boolean updateModelGrowGombaTest() {
        Gombasz _selecetdGomba = selectedGombaTest.getGombaTest().getAlapGomba().getGombasz();
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        return model.growGombaTest(_selecetdGomba, _selectedSecondTekton);
    }

    public boolean updateModelSpreadSpora() {
        Tekton _selectedTekton = selectedTekton.getTekton();
        Tekton _selectedSecondTekton = selectedSecondTekton.getTekton();
        Gomba _selectedAlapG = selectedGombaTest.getGombaTest().getAlapGomba();
        return model.spreadSpora(_selectedSecondTekton, _selectedAlapG);
    }

    public boolean updateModelEatRovar() {
        Gombasz _selectedGombasz = model.getGombaTestById(selectedGombaTest.getId()).getAlapGomba().getGombasz();
        Tekton _selectedTekton = model.getTektonById(selectedTekton.getId());
        Rovar _selectedRovar = model.getRovarById(selectedRovar.getId());
        return model.eatRovar(_selectedGombasz, _selectedTekton, _selectedRovar);
    }

    public boolean updateModelBuyFonal() {
        Gomba _selectedGomba = selectedGombaTest.getGombaTest().getAlapGomba();
        return model.buyFonal(_selectedGomba);
    }

    public void initTimer(int delay, int duration) {
        this.remainingSeconds = duration;
        this.startTime = System.currentTimeMillis();

        timer = new javax.swing.Timer(delay, e -> {
            remainingSeconds--;
            updateTimer();

            if (remainingSeconds <= 0) {
                timer.stop();
                showGameOverDialog();
            }
        });

        timer.start();
    }

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
                        //try {
                            //view.getGamePanel().getGamePanel().addGombaTestView(selectedTekton, g.getGombatest());
                        //} catch (IOException ex) {
                        //    throw new RuntimeException(ex);
                        //}
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

    public javax.swing.Timer getTimer() {
        return timer;
    }

    public void setView(GameFrame view) {
        this.view = view;
    }
}
