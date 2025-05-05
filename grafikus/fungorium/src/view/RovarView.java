package view;
import java.awt.*;

public class RovarView {
    int id;
    private Vec2 pos;
    private SporaHatasView hatas;

    public RovarView(Vec2 pos, int id) {
        this.id = id;
        this.pos = pos;
    }

    public void updateView(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{pos.getX(), pos.getX() - 5, pos.getX() + 5},
                new int[]{pos.getY(), pos.getY() + 10, pos.getY() + 10}, 3);
    }

    public void setHatasView(SporaHatasView h) {
        this.hatas = h;
    }

    public int getId() {
        return id;
    }
}
