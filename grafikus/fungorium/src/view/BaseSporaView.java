package view;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class BaseSporaView extends SporaHatasView {
    public BaseSporaView() {
        loadImage("/resources/rovar.png");
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
