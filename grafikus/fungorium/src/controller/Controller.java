package controller;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

import model.*;
import view.*;

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
        model.initGame(view.getMenuPanel().getGombasz1Name(), view.getMenuPanel().getGombasz2Name(), view.getMenuPanel().getRovarasz1Name(), view.getMenuPanel().getRovarasz2Name());

        onStart(); // Timer beállítás

        /*
        if (selectedStr.startsWith("Rovar")) {
            selectedRovar = model.getRovarById(selectedStr);
        } else if (selectedStr.startsWith("GombaTest")) {
            selectedGombaTest = model.getGombaTestById(selectedStr);
        }
         */

        // új MainPanel létrehozása Timerrel
        MainPanel panel = new MainPanel(this, timer);
        view.setGamePanel(panel); // késleltetve adjuk hozzá

        // InfoPanel gombfigyelők
        panel.getInfoPanel().clearListener = e -> onClearSelection();
        panel.getInfoPanel().exitListener = e -> switchToMenuPanel();

        // Billentyű figyelés
        panel.getGamePanel().addKeyListener(keyListener);
        panel.getGamePanel().setFocusable(true);

        view.switchToGame();
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
