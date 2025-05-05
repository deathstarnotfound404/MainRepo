import controller.Controller;
import model.Field;
import view.GameFrame;

public class Main {
    public static void main(String[] args) {
        // Modell létrehozása
        Field model = new Field();

        // Kontroller létrehozása, amely összeköti a modellt és a nézetet
        Controller controller = new Controller(model);

        GameFrame view = controller.getView();

        // (A vezérlő automatikusan beállítja a figyelőket és elindítja a menüt)
    }
}
