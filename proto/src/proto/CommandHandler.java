package proto;

import model.*;
import java.util.*;
import java.util.function.Consumer;

public class CommandHandler {
    private enum Mode { GAME, TEST }

    private final Map<String, Consumer<List<String>>> parancsok = new HashMap<>();
    private final Field jatekAllapot;
    private Mode currentMode = Mode.GAME;

    public CommandHandler(Field jatekAllapot) {
        this.jatekAllapot = jatekAllapot;
        initParancsok();
    }

    public void parseAndExecute(String commandLine) {
        String[] tokens = commandLine.trim().split("\\s+");
        if (tokens.length == 0) return;

        String parancs = tokens[0];
        List<String> args = Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length));

        Consumer<List<String>> action = parancsok.get(parancs);
        if (action != null) {
            if (currentMode == Mode.TEST && !List.of("runTest", "changeMode", "help").contains(parancs)) {
                System.out.println("TESZT módban csak 'runTest', 'changeMode' és 'help' parancs engedélyezett.");
            } else {
                action.accept(args);
            }
        } else {
            System.out.println("Ismeretlen parancs: " + parancs + "\nÍrd be: help");
        }
    }

    private void initParancsok() {
        parancsok.put("help", this::help);
        parancsok.put("spreadSpora", this::spreadSpora);
        parancsok.put("growFonal", this::growFonal);
        parancsok.put("moveRovar", this::moveRovar);
        parancsok.put("growGombaTest", this::growGombaTest);
        parancsok.put("cutFonal", this::cutFonal);
        parancsok.put("startNewGame", this::startNewGame);
        parancsok.put("setKezdoHelyzet", this::setKezdoHelyzet);
        parancsok.put("jatekKiertekeles", this::jatekKiertekeles);
        parancsok.put("loadMap", this::loadMap);
        parancsok.put("changeMode", this::changeMode);
        parancsok.put("runTest", this::runTest);
    }

    private void help(List<String> args) {
        System.out.println("Elérhető parancsok:");
        System.out.println("  help               - Parancsok listázása");
        System.out.println("  changeMode         - Teszt és játék mód közötti váltás");
        System.out.println("  runTest <i>        - Teszt futtatása TESZT módban (1–26)");
        System.out.println("  spreadSpora        - Spóra szórása");
        System.out.println("  growFonal          - Gombafonal növesztése");
        System.out.println("  moveRovar          - Rovar mozgatása");
        System.out.println("  growGombaTest      - Gombatest növesztése");
        System.out.println("  cutFonal           - Gombafonal elvágása");
        System.out.println("  startNewGame       - Új játék indítása");
        System.out.println("  setKezdoHelyzet    - Kezdőállapot beállítása");
        System.out.println("  jatekKiertekeles   - Játék kiértékelése");
        System.out.println("  loadMap            - Pálya betöltése");
        System.out.println("  exit               - Kilépés a programból");
        System.out.println("Módban vagy: " + currentMode);
    }

    // Példaparancsok (dummy implementációk)
    private void spreadSpora(List<String> args) { System.out.println("Spóra szórás: " + args); }

    private void growFonal(List<String> args) { System.out.println("Fonal növesztés: " + args); }

    private void moveRovar(List<String> args) { System.out.println("Rovar mozgatás: " + args); }

    private void growGombaTest(List<String> args) { System.out.println("Gombatest növesztés: " + args); }

    private void cutFonal(List<String> args) { System.out.println("Fonal vágás: " + args); }

    private void startNewGame(List<String> args) { currentMode = Mode.GAME; System.out.println("Új játék indítva."); }

    private void setKezdoHelyzet(List<String> args) { System.out.println("Kezdőhelyzet beállítva."); }

    private void jatekKiertekeles(List<String> args) { System.out.println("Játék kiértékelése."); }

    private void loadMap(List<String> args) { System.out.println("Pálya betöltve."); }

    private void changeMode(List<String> args) {
        currentMode = (currentMode == Mode.GAME) ? Mode.TEST : Mode.GAME;
        System.out.println("Mód váltva: " + currentMode);
    }

    private void runTest(List<String> args) {
        if (currentMode == Mode.TEST) {
            System.out.println("Teszt futtatása: " + args);
        } else {
            System.out.println("Teszt csak TESZT módban futtatható!");
        }
    }
}
