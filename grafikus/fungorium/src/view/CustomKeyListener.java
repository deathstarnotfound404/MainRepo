package view;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class CustomKeyListener implements KeyListener {
    private Controller controller;

    public CustomKeyListener(Controller c) {
        this.controller = c;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'c' -> {
                System.out.println("Pressed Key: C");

                if(controller.isSelectedRovar() && controller.isSelectedTekton() && controller.isSelectedSecondTekton()) {
                    controller.updateModelCutFonal();
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Rovart, majd utána egy cél Tektont ami között történik a vágás!");
                }
            }
            case 'm' -> {
                System.out.println("Pressed Key:M");
                if(controller.isSelectedRovar() && controller.isSelectedSecondTekton()) {
                    if(controller.updateModelMoveRovar()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Rovart, majd utána egy cél Gombatestet");
                }
            }
            case 'b' -> {
                System.out.println("Pressed Key: B");
                if(controller.isSelectedGombatest()) {
                    controller.updateModelBuyFonal();
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Gombatestet");
                }
            }
            case 'f' -> {
                System.out.println("Pressed Key: F");
                if(controller.isSelectedGombatest() && controller.isSelectedSecondTekton() && controller.isSelectedThirdTekton()) {
                    if(controller.updateModelGrowFonal()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Hint: Clear + 1.Tekton kattintás + 1. Tektonon GombaTest választás + 1.Start Tekton kattintás + 2. Cél Tekton kattintás");
                }
            }
            case 't' -> {
                System.out.println("Pressed Key:T");
                if(controller.isSelectedGombatest() && controller.isSelectedSecondTekton()) {
                    if(controller.updateModelGrowGombaTest()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Gombatestet!");
                }
            }
            case 's' -> {
                System.out.println("Pressed Key:S");
                if(controller.isSelectedGombatest() && controller.isSelectedSecondTekton()) {
                    if(controller.updateModelSpreadSpora()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Előbb válassz ki egy GombaTestet, majd egy céltektont!");
                }
            }
            case 'k' -> {
                System.out.println("Pressed Key:K");
                if(controller.isSelectedRovar() && controller.isSelectedGombatest()) {
                    if(controller.updateModelEatRovar()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Gombatestet, majd egy bénult Rovart!");
                }
            }
            case 'h' -> {
                System.out.println("Segítség parancs aktiválva.");
                String helpMessage = """
        c - fonal elvágása
        m - rovar mozgatása
        f - fonal növesztése
        t - GombaTest növesztése
        s - Spóra szórása
        k - rovar evés

        h - segítség kérése
        """;
                JOptionPane.showMessageDialog(null, helpMessage, "Segítség – Billentyűparancsok", JOptionPane.INFORMATION_MESSAGE);
            }
            default -> System.out.println("Ismeretlen parancs: " + e.getKeyChar());
        }
        controller.onClearSelection();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // jelenleg nem használt
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // jelenleg nem használt
    }
}
