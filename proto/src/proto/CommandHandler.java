package proto;

import model.*;
import java.util.*;
import java.util.function.Consumer;

public class CommandHandler {
    private enum Mode { GAME, TEST }

    private final Map<String, Consumer<List<String>>> parancsok = new HashMap<>();
    private static Field field = new Field(); // újrapéldányosítható mező
    private Mode currentMode = Mode.GAME;
    private volatile boolean gameRunning = false;
    private Timer gameTimer;

    private final Map<Integer, Player> players = new HashMap<>();
    private Player selectedPlayer = null;

    private static final Set<String> globalParancsok = Set.of("help", "exit", "playerNum", "select", "jatekKiertekeles", "printField");
    private static final Set<String> gombaszParancsok = Set.of("spreadSpora", "growFonal", "growGombaTest", "eatRovar");
    private static final Set<String> rovaraszParancsok = Set.of("cutFonal", "moveRovar");

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Főmenü ====");
            System.out.println("1. Játék indítása (GAME mód)");
            System.out.println("2. Teszt futtatása (TEST mód)");
            System.out.println("3. Kilépés");
            System.out.print("> ");
            String valasztas = scanner.nextLine().trim();

            switch (valasztas) {
                case "1":
                    field = new Field();
                    currentMode = Mode.GAME;
                    pregameBehaviour();
                    field.initGame();
                    initParancsok();
                    startCommandLoop();
                    return;
                case "2":
                    currentMode = Mode.TEST;
                    System.out.print("Add meg a tesztszámot (1–26): ");
                    String testSzam = scanner.nextLine().trim();
                    runTest(List.of(testSzam));
                    break;
                case "3":
                    System.out.println("Kilépés...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Érvénytelen választás. Próbáld újra.");
            }
        }
    }

    private void startCommandLoop() {
        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            if (selectedPlayer == null) {
                System.out.print("[select] > ");
            } else {
                System.out.print("[" + selectedPlayer.getName() + "] > ");
            }

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            synchronized (field) {
                parseAndExecute(line);
            }
        }
    }

    private void initParancsok() {
        parancsok.put("help", this::help);
        parancsok.put("spreadSpora", this::spreadSpora);
        parancsok.put("growFonal", this::growFonal);
        parancsok.put("moveRovar", this::moveRovar);
        parancsok.put("growGombaTest", this::growGombaTest);
        parancsok.put("cutFonal", this::cutFonal);
        parancsok.put("setKezdoHelyzet", this::setKezdoHelyzet);
        parancsok.put("jatekKiertekeles", this::jatekKiertekeles);
        parancsok.put("loadMap", this::loadMap);
        parancsok.put("runTest", this::runTest);
        parancsok.put("exit", this::exit);
        parancsok.put("playerNum", this::playerNum);
        parancsok.put("select", this::selectPlayer);
        parancsok.put("printField", this::printField);
        parancsok.put("eatRovar", this::eatRovar);
    }

    public void parseAndExecute(String commandLine) {
        String[] tokens = commandLine.trim().split("\\s+");
        if (tokens.length == 0) return;

        String parancs = tokens[0];
        List<String> args = Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length));

        Consumer<List<String>> action = parancsok.get(parancs);
        if (action != null) {
            if (currentMode == Mode.TEST && !globalParancsok.contains(parancs)) {
                System.out.println("TESZT módban csak a globális parancsok engedélyezettek.");
            } else if (currentMode == Mode.GAME) {
                if (globalParancsok.contains(parancs)) {
                    action.accept(args);
                } else if (selectedPlayer == null) {
                    System.out.println("Először válassz játékost a 'select <szám>' paranccsal.");
                } else if (gombaszParancsok.contains(parancs) && selectedPlayer instanceof Gombasz) {
                    action.accept(args);
                    selectedPlayer = null;
                } else if (rovaraszParancsok.contains(parancs) && selectedPlayer instanceof Rovarasz) {
                    action.accept(args);
                    selectedPlayer = null;
                } else {
                    System.out.println("Ez a parancs nem engedélyezett a kiválasztott játékosnak.");
                }
            }
        } else {
            System.out.println("Ismeretlen parancs: " + parancs + "\nÍrd be: help");
        }
    }

    private void help(List<String> args) {
        System.out.println("Elérhető parancsok:");
        parancsok.keySet().forEach(p -> System.out.println("  " + p));
        System.out.println("Módban vagy: " + currentMode);
    }

    private void spreadSpora(List<String> args) {
        System.out.println(selectedPlayer.getName() + ": Spóra szórás -> " + args);
    }

    private void growFonal(List<String> args) {
        System.out.println(selectedPlayer.getName() + ": Fonal növesztés -> " + args);
    }

    private void moveRovar(List<String> args) {
        System.out.println(selectedPlayer.getName() + ": Rovar mozgatás -> " + args);
    }

    private void growGombaTest(List<String> args) {
        System.out.println(selectedPlayer.getName() + ": Gombatest növesztés -> " + args);
    }

    private void cutFonal(List<String> args) {
        System.out.println(selectedPlayer.getName() + ": Fonal elvágása -> " + args);
    }

    private void eatRovar(List<String> args) {
        System.out.println(selectedPlayer.getName() + ": Rovar elfogyasztása -> " + args);
    }

    private void printField(List<String> args) {
        field.printGameState();
    }

    private void setKezdoHelyzet(List<String> args) {
        System.out.println("Kezdőhelyzet beállítva.");
    }

    private void jatekKiertekeles(List<String> args) {
        System.out.println("Játék kiértékelése.");
    }

    private void loadMap(List<String> args) {
        System.out.println("Pálya betöltve.");
    }

    private void runTest(List<String> args) {
        if (currentMode == Mode.TEST) {
            System.out.println("Teszt futtatása: " + args);
        } else {
            System.out.println("Teszt csak TESZT módban futtatható!");
        }
    }

    private void exit(List<String> args) {
        if (currentMode == Mode.GAME) {
            synchronized (field) {
                System.out.println("Játék megszakítva. Visszatérés a főmenübe.");
                gameRunning = false;
                if (gameTimer != null) {
                    gameTimer.cancel();
                }
            }
            start();
        } else if (currentMode == Mode.TEST) {
            System.out.println("Teszt módból kilépés. Visszatérés a főmenübe.");
            start();
        }
    }

    private void playerNum(List<String> args) {
        System.out.println("Játékosok:");
        for (Map.Entry<Integer, Player> entry : players.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue().getName());
        }
    }

    private void selectPlayer(List<String> args) {
        if (args.size() != 1) {
            System.out.println("Használat: select <szám>");
            return;
        }
        try {
            int id = Integer.parseInt(args.get(0));
            if (players.containsKey(id)) {
                selectedPlayer = players.get(id);
                System.out.println("Kiválasztott játékos: " + selectedPlayer.getName());
            } else {
                System.out.println("Nincs ilyen játékos ezzel a számmal.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Érvénytelen szám.");
        }
    }

    public void pregameBehaviour() {
        Scanner scanner = new Scanner(System.in);
        players.clear();

        System.out.print("Add meg, hány Gombász játszik: ");
        int gombaszokSzama = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < gombaszokSzama; i++) {
            System.out.print("Gombász " + (i + 1) + " neve: ");
            String name = scanner.nextLine();
            Gombasz g = new Gombasz(name);
            field.addGombasz(g);
            players.put(players.size() + 1, g);
        }

        System.out.print("Add meg, hány Rovarász játszik: ");
        int rovaraszokSzama = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < rovaraszokSzama; i++) {
            System.out.print("Rovarász " + (i + 1) + " neve: ");
            String name = scanner.nextLine();
            Rovarasz r = new Rovarasz(name);
            field.addRovarasz(r);
            players.put(players.size() + 1, r);
        }

        System.out.print("Add meg a játék időtartamát másodpercben: ");
        int idotartamMasodperc = scanner.nextInt();
        scanner.nextLine();

        gameRunning = true;
        System.out.println("Játék elindítva " + idotartamMasodperc + " másodpercre!");

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (field) {
                    gameRunning = false;
                    System.out.println("\nLejárt az idő! A játék véget ért.");
                    List<Player> gyoztesek = field.kiertekeles();
                    System.out.println("Győztes(ek):");
                    for (Player p : gyoztesek) {
                        System.out.println(" - " + p.getName() + " (" + p.getScoreFromPlayer() + " pont)");
                    }
                }
                start();
            }
        }, idotartamMasodperc * 1000L);
    }
}
