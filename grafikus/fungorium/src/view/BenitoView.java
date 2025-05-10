package view;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BenitoView extends SporaHatasView {
    public BenitoView() {
        loadImage("/benito_rovar.png");
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