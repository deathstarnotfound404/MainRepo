package view;
import model.Rovar;

import java.awt.*;

public class RovarView {
    private Rovar r;
    private int id;
    private Vec2 pos;
    private SporaHatasView hatas;

    public RovarView(TektonView pos, Rovar r, SporaHatasView hatas) {
        this.hatas = hatas;

        this.r = r;
        this.id = r.getId();

        int centerX = pos.getPosition().getX() + pos.getWidth() / 2;
        int centerY = pos.getPosition().getY() + pos.getHeight() / 2;
        this.pos = new Vec2(centerX, centerY);
    }

    public void updateView(Graphics g) {
        hatas.draw(g, pos);
    }

    public void setHatasView(SporaHatasView h) {
        this.hatas = h;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.r.toString();
    }

    public Rovar getRovar() {
        return this.r;
    }
}
