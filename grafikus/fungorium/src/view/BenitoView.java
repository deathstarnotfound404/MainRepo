package view;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BenitoView extends SporaHatasView {
    @Override
    public void loadImage(String path) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//Todo Többi hatás