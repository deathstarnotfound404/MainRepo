package view;
import java.awt.*;

public class GombaFonalView extends Line {
    public GombaFonalView(TektonView t1, TektonView t2) {
        super(t1, t2);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(2));
        g.drawLine(tekton1.coord.getX(), tekton1.coord.getY(), tekton2.coord.getX(), tekton2.coord.getY());
    }

    @Override
    public void updateView() {}
}
