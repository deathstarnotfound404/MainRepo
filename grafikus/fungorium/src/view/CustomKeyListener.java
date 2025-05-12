package view;
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

                if(controller.isSelctedRovar()) {
                    controller.updateModelCutFonal();
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Rovart, majd utána egy cél Tektont ami között történik a vágás!");
                }
            }
            case 'm' -> {
                System.out.println("Pressed Key:M");
                if(controller.isSelctedRovar()) {
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
                if(controller.isSelectedGombatest()) {
                    if(controller.updateModelGrowFonal()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Hint: Clear + 1.Tekton kattintás + 1. Tektonon GombaTest választás + 2.Tekton kattintás");
                }
            }
            case 't' -> {
                System.out.println("Pressed Key:T");
                if(controller.isSelectedGombatest()) {
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
                if(controller.isSelectedGombatest()) {
                    if(controller.updateModelSpreadSpora()) {
                        try {
                            controller.updateView(controller.getModel());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    controller.keyPressedError("Előbb válassz ki egy Gombatestet, majd utána egy cél Tektont");
                }
            }
            case 'h' -> {
                // segítség parancs – opcionálisan implementálható
                System.out.println("Segítség parancs aktiválva.");
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
