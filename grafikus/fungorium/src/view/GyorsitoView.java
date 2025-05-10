package view;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GyorsitoView extends SporaHatasView {
    public GyorsitoView(Direction dir) {
        loadImage("/gyorsito_rovar.png", dir);
    }

    @Override
    public void loadImage(String path, Direction dir) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            img = ImageIO.read(is);
            if(dir == Direction.DOWN) {
                super.imageForgatas();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
