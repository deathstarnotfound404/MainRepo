package view;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class FieldView extends JPanel {
    private List<TektonView> tektonViewList;
    private List<Line> szomszedsagViewList;
    private List<Line> gombaFonaViewList;
    private List<RovarView> rovarViewList;
    private List<GombaTestView> gombaTestViewList;
    private Map<Tekton, Vec2> layoutCache = new HashMap<>();
    private ActionListener tektonClickListener;

    protected Map<Player, Color> colors;
    protected Map<Player, Direction> dir;

    FieldView(Controller controller) {
        this.setPreferredSize(new Dimension(600, 600));

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
            try {
                controller.onTektonSelection(tv);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        genTektonViews(controller.getModel());
    }

    private Map<Tekton, Vec2> calculateLayout(List<Tekton> tektons, int panelWidth, int panelHeight) {
        Map<Tekton, Vec2> positions = new HashMap<>();
        Random rand = new Random();

        int margin = 50;
        int usableWidth = panelWidth - 2 * margin;
        int usableHeight = panelHeight - 2 * margin;

        int area = usableWidth * usableHeight;
        int n = tektons.size();
        double k = Math.sqrt((double) area / n); // ideális élhossz
        double temperature = panelWidth / 10.0;

        // 0. Megkeressük a törölt elemeket
        Map<Tekton, Vec2> deletedPositions = new HashMap<>();
        for (Tekton old : layoutCache.keySet()) {
            if (!tektons.contains(old)) {
                deletedPositions.put(old, layoutCache.get(old));
            }
        }

        // 1. Meglévő pozíciók megtartása vagy új generálása
        for (Tekton t : tektons) {
            if (layoutCache.containsKey(t)) {
                positions.put(t, layoutCache.get(t));
            } else {
                // Próbáljuk a törölt szomszédok közelébe tenni
                Vec2 base = null;
                for (Tekton deleted : deletedPositions.keySet()) {
                    if (t.getSzomszedosTektonok().contains(deleted)) {
                        base = deletedPositions.get(deleted);
                        break;
                    }
                }

                Vec2 newPos;
                boolean tooClose;
                do {
                    tooClose = false;
                    if (base != null) {
                        int dx = rand.nextInt(41) - 20;
                        int dy = rand.nextInt(41) - 20;
                        newPos = new Vec2(base.getX() + dx, base.getY() + dy);
                    } else {
                        newPos = new Vec2(margin + rand.nextInt(usableWidth), margin + rand.nextInt(usableHeight));
                    }

                    for (Vec2 pos : positions.values()) {
                        int dx = pos.getX() - newPos.getX();
                        int dy = pos.getY() - newPos.getY();
                        if (Math.sqrt(dx * dx + dy * dy) < 50) {
                            tooClose = true;
                            break;
                        }
                    }
                } while (tooClose);
                positions.put(t, newPos);
            }
        }

        // 2. Mozgathatók: újak és azok szomszédai
        Set<Tekton> movable = new HashSet<>();
        for (Tekton t : tektons) {
            if (!layoutCache.containsKey(t)) {
                movable.add(t);
                movable.addAll(t.getSzomszedosTektonok());
            }
        }

        // 3. Iteráció – vonzás, taszítás, gravity
        for (int iter = 0; iter < 200; iter++) {
            Map<Tekton, Vec2> displacements = new HashMap<>();
            for (Tekton t : movable) {
                displacements.put(t, new Vec2(0, 0));
            }

            // Taszítás minden elem között
            for (Tekton v : movable) {
                for (Tekton u : tektons) {
                    if (v == u) continue;
                    Vec2 posV = positions.get(v);
                    Vec2 posU = positions.get(u);
                    int dx = posV.getX() - posU.getX();
                    int dy = posV.getY() - posU.getY();
                    double dist = Math.sqrt(dx * dx + dy * dy) + 0.01;
                    double force = k * k / dist;

                    displacements.put(v, displacements.get(v).add(new Vec2(
                            (int) (dx / dist * force),
                            (int) (dy / dist * force)
                    )));
                }
            }

            // Vonzás szomszédok között
            for (Tekton v : movable) {
                for (Tekton u : v.getSzomszedosTektonok()) {
                    if (!positions.containsKey(u)) continue;
                    Vec2 posV = positions.get(v);
                    Vec2 posU = positions.get(u);
                    int dx = posV.getX() - posU.getX();
                    int dy = posV.getY() - posU.getY();
                    double dist = Math.sqrt(dx * dx + dy * dy) + 0.01;
                    double force = dist * dist / k;

                    displacements.put(v, displacements.get(v).add(new Vec2(
                            (int) (-dx / dist * force),
                            (int) (-dy / dist * force)
                    )));
                }
            }

            // Gravity középre
            int centerX = panelWidth / 2;
            int centerY = panelHeight / 2;
            for (Tekton t : movable) {
                Vec2 pos = positions.get(t);
                int dx = centerX - pos.getX();
                int dy = centerY - pos.getY();
                double gravity = 0.1;
                displacements.put(t, displacements.get(t).add(new Vec2(
                        (int) (dx * gravity),
                        (int) (dy * gravity)
                )));
            }

            // 4. Elmozdítás + margin korrekció
            for (Tekton t : movable) {
                Vec2 pos = positions.get(t);
                Vec2 disp = displacements.get(t);
                double len = Math.sqrt(disp.getX() * disp.getX() + disp.getY() * disp.getY()) + 0.01;
                int dx = (int) (disp.getX() / len * Math.min(len, temperature));
                int dy = (int) (disp.getY() / len * Math.min(len, temperature));
                int newX = Math.max(margin, Math.min(panelWidth - margin, pos.getX() + dx));
                int newY = Math.max(margin, Math.min(panelHeight - margin, pos.getY() + dy));
                positions.put(t, new Vec2(newX, newY));
            }

            temperature *= 0.95;
        }

        // 5. Frissítjük a cache-t
        layoutCache.clear();
        layoutCache.putAll(positions);
        return positions;
    }

    private void genTektonViews(Field model) {
        if (layoutCache.isEmpty()) {
            layoutCache = calculateLayout(model.getTektons(), 500, 500);
        }

        List<Tekton> currentModelTektons = model.getTektons();
        List<TektonView> updatedTektonViews = new ArrayList<>();

        // Megtartjuk azokat, amik még léteznek
        for (TektonView tv : tektonViewList) {
            if (currentModelTektons.contains(tv.getTekton())) {
                updatedTektonViews.add(tv);
            }
        }

        // A meglévők listáját frissítjük
        tektonViewList = updatedTektonViews;

        // Megkeressük az új Tektonokat
        for (Tekton t : currentModelTektons) {
            boolean alreadyExists = false;
            for (TektonView tv : tektonViewList) {
                if (tv.getTekton().equals(t)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (!alreadyExists) {
                Vec2 pos = layoutCache.get(t);
                TektonView newView;
                if (t.isDefendFonalak()) {
                    newView = new FonalDefenderTektonView(t, pos.getX(), pos.getY(), tektonClickListener);
                } else if (t.isGtGatlo()) {
                    newView = new GombaTestGatloView(t, pos.getX(), pos.getY(), tektonClickListener);
                } else if (t.getTektonHatas() instanceof FonalGatloHatas) {
                    newView = new FonalGatloView(t, pos.getX(), pos.getY(), tektonClickListener);
                } else if (t.getTektonHatas() instanceof FonalFelszivodoHatas) {
                    newView = new FonalFelszivodoTektonView(t, pos.getX(), pos.getY(), tektonClickListener);
                } else {
                    newView = new BaseTektonView(t, pos.getX(), pos.getY(), tektonClickListener);
                }
                tektonViewList.add(newView);
            }
        }

        // Előző View-k törlése, új hozzáadása
        this.removeAll();
        for (TektonView tv : tektonViewList) {
            this.add(tv);
        }

        // Szomszédosság újragenerálása
        szomszedsagViewList.clear();
        for (TektonView tv : tektonViewList) {
            for (TektonView tv2 : tektonViewList) {
                if (tv.getId() == tv2.getId()) continue;
                if (tv.getTekton().isSzomszedok(tv2.getTekton())) {
                    szomszedsagViewList.add(new SzomszedossagView(tv, tv2));
                }
            }
        }
    }

    public void updateView(Field model) throws IOException {
        this.removeAll();
        szomszedsagViewList.clear();
        gombaFonaViewList.clear();
        rovarViewList.clear();
        gombaTestViewList.clear();

        if(model.getTektons().size() != layoutCache.size()) {
            layoutCache.clear();
        }

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
                        Rovar r = t.getRovar();
                        Direction direction = dir.get(r.getRovarasz());
                        if (!r.getTudVagni()) { //vágásgátló
                            rovarViewList.add(new RovarView(tv, r, new VagastGatloView(direction)));
                        } else if (r.getEvesHatekonysag() == 0.25) {    //Lassító
                            rovarViewList.add(new RovarView(tv, r, new LassitoView(direction)));
                        } else if (r.getEvesHatekonysag() == 0.0) {  //Benito
                            rovarViewList.add(new RovarView(tv, r, new BenitoView(direction)));
                        } else if (r.getEvesHatekonysag() == 1.0) {  //Gyorsító
                            rovarViewList.add(new RovarView(tv, r, new GyorsitoView(direction)));
                        } else {  //Sima / Klonozot
                            rovarViewList.add(new RovarView(tv, r, new BaseSporaView(direction)));
                        }
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

        RovarView rv = new RovarView(tektonView, rovar, new BaseSporaView(direction));
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
