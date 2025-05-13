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

        int centerX_t1 = tekton1.coord.getX() + tekton1.getWidth() / 2;
        int centerY_t1 = tekton1.coord.getY() + tekton1.getHeight() / 2;
        int centerX_t2 = tekton2.coord.getX() + tekton2.getWidth() / 2;
        int centerY_t2 = tekton2.coord.getY() + tekton2.getHeight() / 2;
        g.drawLine(centerX_t1, centerY_t1, centerX_t2, centerY_t2);
    }

    @Override
    public void updateView() {}

    @Override
    public int getId() {
        return gf.getID();
    }

    public GombaFonal getGf() {
        return gf;
    }
}
