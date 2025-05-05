package view;
import model.Rovar;

import java.awt.*;

public class RovarView {
    int id;
    private Vec2 pos;
    private SporaHatasView hatas;

    public RovarView(TektonView pos, int id) {
        hatas = new BaseSporaView();
        this.id = id;

        int centerX = pos.getPosition().getX() + pos.getWidth() / 2;
        int centerY = pos.getPosition().getY() + pos.getHeight() / 2;
        this.pos = new Vec2(centerX, centerY);
    }

    /*
    public void updateView(Graphics g) {
        Rovar r = model.getRovarById(id);
        if (r == null) return;

        if (hatas == null) return new BaseSporaView();
        else if (hatas instanceof BenitoHatas) return new BenitoView();
        else if (hatas instanceof GyorsitoHatas) return new GyorsitoView();
        else if (hatas instanceof LassitoHatas) return new LassitoView();
        else if (hatas instanceof KlonozoHatas) return new KlonozoView();
        else if (hatas instanceof VagastGatloHatas) return new VagastGatloView();
        else return new BaseSporaView(); // fallback

        SporaHatasView hatasView = getViewFromHatas(aktivHatas);
        hatasView.draw(g, pos);
    }

     */

    public void updateView(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillPolygon(new int[]{pos.getX(), pos.getX() - 5, pos.getX() + 5}, new int[]{pos.getY(), pos.getY() + 10, pos.getY() + 10}, 3);
        hatas.draw(g, pos);
    }

    public void setHatasView(SporaHatasView h) {
        this.hatas = h;
    }

    public int getId() {
        return id;
    }
}
