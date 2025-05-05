package view;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class KlonozoView extends SporaHatasView {
    public KlonozoView() {
        loadImage("/resources/klonozo_rovar.png");
    }

    @Override
    public void loadImage(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            img = ImageIO.read(is);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
