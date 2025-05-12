package view;
import java.awt.*;

public class SzomszedossagView extends Line {
    public SzomszedossagView(TektonView t1, TektonView t2) {
        super(t1, t2, Color.BLACK);
    }

    @Override
    public void draw(Graphics2D g) {
        // Antialiasing bekapcsolása a simább vonalakért
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(super.color);

        // Finomabb, rövidebb szaggatott minta beállítása
        Stroke dashed = new BasicStroke(
                1f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1f,
                new float[]{4f, 4f},
                0f
        );
        g.setStroke(dashed);

        int centerX_t1 = tekton1.coord.getX() + tekton1.getWidth() / 2;
        int centerY_t1 = tekton1.coord.getY() + tekton1.getHeight() / 2;
        int centerX_t2 = tekton2.coord.getX() + tekton2.getWidth() / 2;
        int centerY_t2 = tekton2.coord.getY() + tekton2.getHeight() / 2;

        g.drawLine(centerX_t1, centerY_t1, centerX_t2, centerY_t2);
    }


    //TODO
    @Override
    public void updateView() {}

    @Override
    public int getId() {
        return -1;
    }
}

