package view;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BenitoView extends SporaHatasView {
    public BenitoView(Direction dir) {
        loadImage("/benito_rovar.png", dir);
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