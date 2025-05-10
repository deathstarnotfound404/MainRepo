package view;
import java.awt.*;

public class SzomszedossagView extends Line {
    public SzomszedossagView(TektonView t1, TektonView t2) {
        super(t1, t2);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
        g.setStroke(dashed);
        g.drawLine(tekton1.coord.getX(), tekton1.coord.getY(), tekton2.coord.getX(), tekton2.coord.getY());
    }

    @Override
    public void updateView() {}
}

