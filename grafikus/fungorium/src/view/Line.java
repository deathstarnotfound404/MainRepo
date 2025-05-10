package view;

import java.awt.*;

public abstract class Line {
    protected TektonView tekton1;
    protected TektonView tekton2;
    protected Color color = Color.BLACK;

    public Line(TektonView t1, TektonView t2, Color color) {
        this.tekton1 = t1;
        this.tekton2 = t2;
        this.color = color;
    }

    public abstract void draw(Graphics2D g);
    public abstract void updateView();
}
