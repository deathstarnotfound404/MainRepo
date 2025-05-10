package view;
import model.GombaFonal;

import java.awt.*;

public class GombaFonalView extends Line {
    private GombaFonal gf;

    public GombaFonalView(TektonView t1, TektonView t2, GombaFonal gf, Color c) {
        super(t1, t2, c);
        this.gf = gf;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(super.color);
        g.setStroke(new BasicStroke(2));
        g.drawLine(tekton1.coord.getX(), tekton1.coord.getY(), tekton2.coord.getX(), tekton2.coord.getY());
    }

    @Override
    public void updateView() {}

    public GombaFonal getGf() {
        return gf;
    }
}
