package view;
import controller.Controller;
import model.Field;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FieldView extends JPanel {
    private List<TektonView> tektonViewList;
    private List<Line> szomszedsagViewList;
    private List<Line> gombaFonaViewList;
    private List<RovarView> rovarViewList;
    private List<GombaTestView> gombaTestViewList;
    ActionListener tektonClickListener;

    FieldView(Controller controller) {
        tektonViewList = new ArrayList<>();
        szomszedsagViewList = new ArrayList<>();
        gombaFonaViewList = new ArrayList<>();
        rovarViewList = new ArrayList<>();
        gombaTestViewList = new ArrayList<>();

        tektonClickListener = e -> {
            TektonView tv = (TektonView) e.getSource();
            System.out.println("-> Tekton Clicked");
            controller.onTektonSelection(tv);
        };

        int x_i = 30;
        int row = 0;
        int y_i = 30;

        for (Tekton t : controller.getModel().getTektonList()) {
            if(t.isDefendFonalak()) {
                tektonViewList.add(new FonalDefenderTektonView(t.getId(), x_i, y_i, tektonClickListener));
            } else if(t.isGtGatlo()) {
                tektonViewList.add(new GombaTestGatloView(t.getId(), x_i, y_i, tektonClickListener));
            } else if (t.isMaxEgyFonal()) {
                tektonViewList.add(new FonalGatloView(t.getId(), x_i, y_i, tektonClickListener));
            } else if (t.getTektonHatas() instanceof FonalFelszivodoHatas) {
                tektonViewList.add(new FonalFelszivodoTektonView(t.getId(), x_i, y_i, tektonClickListener));
            } else {
                tektonViewList.add(new BaseTektonView(t.getId(), x_i, y_i, tektonClickListener));
            }

            x_i = x_i+100;
            row = row + 1;
            if(row == 3) {
                x_i = 30;
                row = 0;
                y_i = y_i+100;
            }
        }

        for (TektonView tv : tektonViewList) {
            this.add(tv);
        }
    }

    public void updateView() {
        repaint();
    }

    public List<TektonView> getTektonViews() {
        return tektonViewList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Line line : szomszedsagViewList) line.draw((Graphics2D) g);
        for (Line line : gombaFonaViewList) line.draw((Graphics2D) g);
        for (RovarView r : rovarViewList) r.updateView(g);
        for (GombaTestView gt : gombaTestViewList) gt.updateView(g);
    }
}
