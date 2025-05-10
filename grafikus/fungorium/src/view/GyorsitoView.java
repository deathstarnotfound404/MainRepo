package view;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GyorsitoView extends SporaHatasView {
    public GyorsitoView() {
        loadImage("/gyorsito_rovar.png");
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
