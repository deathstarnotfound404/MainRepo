package view;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldView extends JPanel {
    private List<TektonView> tektonViewList;
    private List<Line> szomszedsagViewList;
    private List<Line> gombaFonaViewList;
    private List<RovarView> rovarViewList;
    private List<GombaTestView> gombaTestViewList;
    ActionListener tektonClickListener;

    protected Map<Player, Color> colors;
    protected Map<Player, Direction> dir;

    FieldView(Controller controller) {
        tektonViewList = new ArrayList<>();
        szomszedsagViewList = new ArrayList<>();
        gombaFonaViewList = new ArrayList<>();
        rovarViewList = new ArrayList<>();
        gombaTestViewList = new ArrayList<>();
        colors = new HashMap<>();
        dir = new HashMap<>();

        Color g1_col;
        Color g2_col;

        //Set gombasz colors
        g1_col = Color.PINK;
        colors.put(controller.getModel().getFirstGombasz() , g1_col);
        if(controller.getModel().getPlayerCount() > 2) {
            g2_col = Color.CYAN;
            colors.put(controller.getModel().getSecondGombasz() , g2_col);
        }

        //Set Rovarasz directions
        Direction r1_dir;
        Direction r2_dir;
        r1_dir = Direction.UP;
        dir.put(controller.getModel().getFirstRovarasz() , r1_dir);
        if(controller.getModel().getPlayerCount() > 2) {
            r2_dir = Direction.DOWN;
            dir.put(controller.getModel().getSecondRovarasz() , r2_dir);
        }

        tektonClickListener = e -> {
            TektonView tv = (TektonView) e.getSource();
            System.out.println("-> Tekton Clicked");
            controller.onTektonSelection(tv);
        };

        genTektonViews(controller.getModel());
    }

    private void genTektonViews(Field model) {
        int x_i = 30;
        int row = 0;
        int y_i = 30;

        for (Tekton t : model.getTektons()) {
            if(t.isDefendFonalak()) {
                tektonViewList.add(new FonalDefenderTektonView(t, x_i, y_i, tektonClickListener));
            } else if(t.isGtGatlo()) {
                tektonViewList.add(new GombaTestGatloView(t, x_i, y_i, tektonClickListener));
            } else if (t.isMaxEgyFonal()) {
                tektonViewList.add(new FonalGatloView(t, x_i, y_i, tektonClickListener));
            } else if (t.getTektonHatas() instanceof FonalFelszivodoHatas) {
                tektonViewList.add(new FonalFelszivodoTektonView(t, x_i, y_i, tektonClickListener));
            } else {
                tektonViewList.add(new BaseTektonView(t, x_i, y_i, tektonClickListener));
            }

            x_i = x_i+100;
            row = row + 1;
            if(row == 4) {
                x_i = 30;
                row = 0;
                y_i = y_i+100;
            }
        }

        for (TektonView tv : tektonViewList) {
            this.add(tv);
        }

        //Szomszedosságok beálltása
        for(TektonView tv : tektonViewList) {
            for(TektonView tv2 : tektonViewList) {
                if(tv.getId() == tv2.getId()) {
                    break;
                } else {
                    if (tv.getTekton().isSzomszedok(tv2.getTekton())) {
                        SzomszedossagView szv = new SzomszedossagView(tv, tv2);
                        szomszedsagViewList.add(szv);
                    }
                }
            }
        }
    }

    public void updateView(Field model) throws IOException {
        //TODO Update a listákat
        tektonViewList.clear();
        szomszedsagViewList.clear();
        gombaFonaViewList.clear();
        rovarViewList.clear();
        gombaTestViewList.clear();

        //Tektonok + Szomszédságok
        genTektonViews(model);
        for(Tekton t : model.getTektons()) {

            //GombaTestek
            if(t.getGomba() != null) {
                for(TektonView tv : tektonViewList) {
                    if(tv.getTekton().getId() == t.getId()) {
                        gombaTestViewList.add(new GombaTestView(tv, t.getGomba().getGombatest(), colors.get(t.getGomba().getGombasz())));
                        //addGombaTestView(t, t.getGomba().getGombatest())
                    }
                }
            }

            //Rovarok
            if(t.getRovar() != null) {
                for(TektonView tv : tektonViewList) {
                    if(tv.getTekton().getId() == t.getRovar().getHelyzet().getId()) {
                        rovarViewList.add(new RovarView(tv, t.getRovar(), dir.get(t.getRovar().getRovarasz())));
                    }
                }
            }

            //GombaFonalak
            List<GombaFonal> gfList = t.getKapcsolodoFonalak();
            for(GombaFonal gf : gfList) {
                boolean marBenne = false;
                for(Line gfv : gombaFonaViewList) {
                    if(gfv.getId() != -1 && gfv.getId() == gf.getID()) {
                        marBenne = true;
                    }
                }
                if(!marBenne) {
                    int startId = gf.getStartTekton().getId();
                    int endId = gf.getCelTekton().getId();
                    TektonView start = null;
                    TektonView end = null;
                    for (TektonView tv : tektonViewList) {
                        if(tv.getTekton().getId() == startId) {
                            start = tv;
                        } else if(tv.getTekton().getId() == endId) {
                            end = tv;
                        }
                    }
                    if(start != null && end != null) {
                        gombaFonaViewList.add(new GombaFonalView(start, end, gf, colors.get(gf.getAlapGomba().getGombasz())));
                    }
                }
            }

        }

        //repaint();
    }

    public List<TektonView> getTektonViews() {
        return tektonViewList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(TektonView tv : tektonViewList) tv.updateView();
        for (Line line : szomszedsagViewList) line.draw((Graphics2D) g);
        for (Line line : gombaFonaViewList) line.draw((Graphics2D) g);
        for (RovarView r : rovarViewList) r.updateView(g);
        for (GombaTestView gt : gombaTestViewList) gt.updateView(g);
    }

    public void addGombaTestView(TektonView tektonView, GombaTest gombaTest) throws IOException {
        Gombasz gsz = gombaTest.getAlapGomba().getGombasz();
        Color col = colors.get(gsz);
        GombaTestView gtv = new GombaTestView(tektonView, gombaTest, col);
        gombaTestViewList.add(gtv);
        //TODO ezekt ki kell szedni, és updateView frissítse végig
        repaint(); // újrarajzolás
    }

    public void addRovarView(TektonView tektonView, Rovar rovar) {
        Rovarasz rsz = rovar.getRovarasz();
        Direction direction = dir.get(rsz);

        RovarView rv = new RovarView(tektonView, rovar, direction);
        rovarViewList.add(rv);
        repaint(); // újrarajzolás a panelen
    }

    public void addGombaFonalView(TektonView t1, TektonView t2, GombaFonal gf) {
        Gombasz gsz = gf.getAlapGomba().getGombasz();
        Color col = colors.get(gsz);
        GombaFonalView gfv = new GombaFonalView(t1, t2, gf, col);
        gombaFonaViewList.add(gfv);
        repaint();
    }

    public List<RovarView> getRovarViews() {
        return this.rovarViewList;
    }

    public List<GombaTestView> getGombaTestViews() {
        return this.gombaTestViewList;
    }
}
