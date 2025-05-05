package view;

import java.awt.*;

public abstract class Line {
    protected TektonView tekton1;
    protected TektonView tekton2;

    public Line(TektonView t1, TektonView t2) {
        this.tekton1 = t1;
        this.tekton2 = t2;
    }

    public abstract void draw(Graphics2D g);
    public abstract void updateView();
}
