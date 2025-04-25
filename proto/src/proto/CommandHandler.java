package proto;

import model.*;
import java.util.*;
import java.util.function.BiConsumer;

public class CommandHandler {
    private enum Mode { GAME, TEST }

    private final Map<String, BiConsumer<Player, List<String>>> parancsok = new HashMap<>();
    private static Field field = new Field();
    private Mode currentMode = Mode.GAME;
    private volatile boolean gameRunning = false;
    private Timer gameTimer;
    private Timer sporaTimer;

    private final Map<Integer, Player> players = new LinkedHashMap<>();
    private Player selectedPlayer = null;

    private static final Set<String> globalParancsok = Set.of("help", "exit", "playerNum", "select", "jatekKiertekeles", "printField");
    private static final Set<String> gombaszParancsok = Set.of("spreadSpora", "growFonal", "growGombaTest", "eatRovar", "buyFonal");
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
        parancsok.put("help", (p, args) -> help(args));
        parancsok.put("spreadSpora", (p, args) -> spreadSpora((Gombasz) p, args));
        parancsok.put("growFonal", (p, args) -> growFonal((Gombasz) p, args));
        parancsok.put("moveRovar", (p, args) -> moveRovar((Rovarasz) p, args));
        parancsok.put("growGombaTest", (p, args) -> growGombaTest((Gombasz) p, args));
        parancsok.put("cutFonal", (p, args) -> cutFonal((Rovarasz) p, args));
        parancsok.put("setKezdoHelyzet", (p, args) -> setKezdoHelyzet(args));
        parancsok.put("jatekKiertekeles", (p, args) -> jatekKiertekeles(args));
        parancsok.put("loadMap", (p, args) -> loadMap(args));
        parancsok.put("runTest", (p, args) -> runTest(args));
        parancsok.put("exit", (p, args) -> exit(args));
        parancsok.put("playerNum", (p, args) -> playerNum(args));
        parancsok.put("select", (p, args) -> selectPlayer(args));
        parancsok.put("printField", (p, args) -> printField(args));
        parancsok.put("eatRovar", (p, args) -> eatRovar((Gombasz) p, args));
        parancsok.put("buyFonal", (p, args) -> buyFonal((Gombasz) p));
    }

    public void parseAndExecute(String commandLine) {
        String[] tokens = commandLine.trim().split("\\s+");
        if (tokens.length == 0) return;

        String parancs = tokens[0];
        List<String> args = Arrays.asList(Arrays.copyOfRange(tokens, 1, tokens.length));

        BiConsumer<Player, List<String>> action = parancsok.get(parancs);
        if (action != null) {
            if (currentMode == Mode.TEST && !globalParancsok.contains(parancs)) {
                System.out.println("TESZT módban csak a globális parancsok engedélyezettek.");
            } else if (currentMode == Mode.GAME) {
                if (globalParancsok.contains(parancs)) {
                    action.accept(null, args);
                } else if (selectedPlayer == null) {
                    System.out.println("Először válassz játékost a 'select <szám>' paranccsal.");
                } else if (gombaszParancsok.contains(parancs) && selectedPlayer instanceof Gombasz) {
                    action.accept(selectedPlayer, args);
                    selectedPlayer = null;
                } else if (rovaraszParancsok.contains(parancs) && selectedPlayer instanceof Rovarasz) {
                    action.accept(selectedPlayer, args);
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

    private void spreadSpora(Gombasz gsz, List<String> args) {
        Scanner scanner = new Scanner(System.in);
        Gomba gomba = null;
        Tekton celTekton = null;

        // Gomba bekérés és validálása
        while (true) {
            System.out.println("[Szórás]");
            System.out.print("\tGombász gombáinak id-jei: ");
            for (Gomba g : gsz.getGombaLista() ) {
                System.out.print(g.getId());
            }
            System.out.print("\n");
            System.out.println("\tGomba:");
            System.out.print("\t\t> ");
            try {
                int gombaId = Integer.parseInt(scanner.nextLine().trim());
                gomba = gsz.getGombaById(gombaId);
                if (gomba != null) break;
                System.out.println("\tNincs ilyen azonosítójú gomba. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tNincs ilyen azonosítójú gomba. Próbáld újra");
            }
        }

        // Céltekton bekérés és validálása
        int tektonId;
        while (true) {
            System.out.println("\tCéltekton:");
            System.out.print("\t\t> ");
            try {
                tektonId = Integer.parseInt(scanner.nextLine().trim());
                List<Tekton> tektonList = field.getTektonList();
                if (tektonId >= 0 && tektonId < tektonList.size()) {
                    celTekton = tektonList.get(tektonId);
                    break;
                }
                System.out.println("\tNincs ilyen azonosítójú tekton. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Szórás végrehajtása
        if (gsz.szoras(gomba, celTekton)) {
            System.out.println(gsz.getName() + ": Sikeres szórás a(z) " + tektonId + ". tektonra.");
        } else {
            System.out.println(gsz.getName() + ": Sikertelen szórás.");
        }
    }

    private void growFonal(Gombasz gsz, List<String> args) {
        Scanner scanner = new Scanner(System.in);
        Gomba gomba = null;
        Tekton startTekton = null;
        Tekton celTekton = null;

        // Gomba bekérés és validálása
        while (true) {
            System.out.println("[Fonal növesztés]");
            System.out.print("\tGombász gombáinak id-jei: ");
            for (Gomba g : gsz.getGombaLista()) {
                System.out.print(g.getId() + " ");
            }
            System.out.print("\n\tGomba:\n\t\t> ");
            try {
                int gombaId = Integer.parseInt(scanner.nextLine().trim());
                gomba = gsz.getGombaById(gombaId);
                if (gomba != null) break;
                System.out.println("\tNincs ilyen azonosítójú gomba. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tHibás számformátum. Próbáld újra.");
            }
        }

        List<Tekton> tektonList = field.getTektonList();

        // Start tekton bekérés
        while (true) {
            System.out.println("\tStartTekton:");
            System.out.print("\t\t> ");
            try {
                int startId = Integer.parseInt(scanner.nextLine().trim());
                if (startId >= 0 && startId < tektonList.size()) {
                    startTekton = tektonList.get(startId);
                    break;
                }
                System.out.println("\tNincs ilyen azonosítójú tekton. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Cél tekton bekérés
        while (true) {
            System.out.println("\tCélTekton:");
            System.out.print("\t\t> ");
            try {
                int celId = Integer.parseInt(scanner.nextLine().trim());
                if (celId >= 0 && celId < tektonList.size()) {
                    celTekton = tektonList.get(celId);
                    break;
                }
                System.out.println("\tNincs ilyen azonosítójú tekton. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Fonal növesztés meghívása
        if (gsz.gombafonalIranyitas(gomba, startTekton, celTekton, false)) {
            System.out.println(gsz.getName() + ": Sikeres fonal növesztés a(z) " + startTekton.getId() + " -> " + celTekton.getId() + " útvonalon.");
        } else {
            System.out.println(gsz.getName() + ": Sikertelen fonal növesztés.");
        }
    }

    private void buyFonal(Gombasz gsz) {
        Scanner scanner = new Scanner(System.in);
        Gomba gomba = null;

        // Gomba bekérés és validálása
        while (true) {
            System.out.println("[Fonal növesztés]");
            System.out.print("\tGombász gombáinak id-jei: ");
            for (Gomba g : gsz.getGombaLista()) {
                System.out.print(g.getId() + " ");
            }
            System.out.print("\n\tGomba:\n\t\t> ");
            try {
                int gombaId = Integer.parseInt(scanner.nextLine().trim());
                gomba = gsz.getGombaById(gombaId);
                if (gomba != null) break;
                System.out.println("\tNincs ilyen azonosítójú gomba. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tHibás számformátum. Próbáld újra.");
            }
        }

        //TODO jól növeli a fonalszamot a Tekton print nél, miért nem nő a fonalfokszam
        //Fonal vásárlás
        if (gsz.fonalVasarlas(gomba)) {
            System.out.println(gsz.getName() + ": Sikeres fonal vásárlás a kijelölt Gombán: ID: " + gomba.getId());
        } else {
            System.out.println(gsz.getName() + ": Sikertelen fonal vásárlás.");
        }
    }

    private void moveRovar(Rovarasz r, List<String> args) { System.out.println(r.getName() + ": Rovar mozgatás -> " + args); }

    private void growGombaTest(Gombasz g, List<String> args) { System.out.println(g.getName() + ": Gombatest növesztés -> " + args); }

    private void cutFonal(Rovarasz r, List<String> args) { System.out.println(r.getName() + ": Fonal elvágása -> " + args); }

    private void eatRovar(Gombasz g, List<String> args) { System.out.println(g.getName() + ": Rovar elfogyasztása -> " + args); }

    private void printField(List<String> args) { field.printGameState(); }

    private void setKezdoHelyzet(List<String> args) { System.out.println("Kezdőhelyzet beállítva."); }

    private void jatekKiertekeles(List<String> args) { System.out.println("Játék kiértékelése."); }

    private void loadMap(List<String> args) { System.out.println("Pálya betöltve."); }

    private void runTest(List<String> args) { System.out.println("Teszt futtatása: " + args); }

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

        field.initGame();

        List<Tekton> tektonList = field.getTektonList();
        for (Player p : players.values()) {
            System.out.println("[" + p.getName() + ":" + p.getClass().getSimpleName() + "]");
            if (p instanceof Gombasz g) {
                System.out.print("    kezdo Gomba helye > ");
                int tIndex = scanner.nextInt();
                scanner.nextLine();
                Gomba gomba = new Gomba(tektonList.get(tIndex), g, 0);
                g.addGomba(gomba);
                System.out.println("Gomba létrehozva a(z) " + tIndex + ". Tektorra.");
            } else if (p instanceof Rovarasz r) {
                System.out.print("    kezdo Rovar helye > ");
                int tIndex = scanner.nextInt();
                scanner.nextLine();
                Rovar rovar = new Rovar();
                rovar.setHelyzet(tektonList.get(tIndex));
                tektonList.get(tIndex).setRovar(rovar);
                rovar.setRovarasz(r);
                r.addRovar(rovar, tektonList.get(tIndex));
                System.out.println("Rovar létrehozva a(z) " + tIndex + ". Tektorra.");
            }
        }

        gameRunning = true;
        System.out.println("Játék elindítva!");

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

        // ÚJ: 20 másodpercenként spóra termelés meghívása minden Gombászra
        sporaTimer = new Timer();
        sporaTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (field) {
                    for (Player p : players.values()) {
                        if (p instanceof Gombasz g) {
                            g.sporaTermelesAll();
                            //System.out.println("\n[Spóra termelés] " + g.getName() + " végrehajtotta a spóra termelést.");
                        }
                    }
                }
            }
        }, 5000, 5000); // 20 (20000)másodpercenként
    }
}

// TODO, most kb működik a menü, minden parancsot rá kell kötni a Field parancsaira, ezek képesek lesznek mozgatni a cuccokat
// Le kell tesztelni mindent, + keresni kell a hibákat
// lehetne egy játékmód ahol nincs idő
// Tesztek implementálása