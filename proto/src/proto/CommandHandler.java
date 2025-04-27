package proto;

import model.*;
import java.util.*;
import java.util.function.BiConsumer;
import test.*;

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

        //Fonal vásárlás
        if (gsz.fonalVasarlas(gomba)) {
            System.out.println(gsz.getName() + ": Sikeres fonal vásárlás a kijelölt Gombán: ID: " + gomba.getId());
        } else {
            System.out.println(gsz.getName() + ": Sikertelen fonal vásárlás.");
        }
    }

    private void moveRovar(Rovarasz rsz, List<String> args) {
        Scanner scanner = new Scanner(System.in);
        Rovar rovar = null;
        Tekton celTekton = null;

        // Rovar bekérés és validálása
        while (true) {
            System.out.println("[Rovar mozgatás]");
            System.out.print("\tRovarász rovarainak id-jei: ");
            for (Rovar r : rsz.getRovarLista()) {
                System.out.print(r.getId() + " ");
            }
            System.out.print("\n");
            System.out.println("\tRovar:");
            System.out.print("\t\t> ");
            try {
                int rovarId = Integer.parseInt(scanner.nextLine().trim());
                rovar = rsz.getRovarById(rovarId);
                if (rovar != null) break;
                System.out.println("\tNincs ilyen azonosítójú rovar. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Céltekton bekérés és validálása
        while (true) {
            System.out.println("\tCéltekton:");
            System.out.print("\t\t> ");
            try {
                int tektonId = Integer.parseInt(scanner.nextLine().trim());
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

        // Mozgatás végrehajtása
        if (rsz.rovarIranyitas(rovar, celTekton)) {
            System.out.println(rsz.getName() + ": Sikeresen mozgatta a rovart a(z) " + celTekton.getId() + ". tektonra.");
            //TODO - Időzítés beállítása Rovarra alaphelyzetre (30 sec)
            rovar.sporaEves();
            celTekton.hatasKifejtes();

            List<Tekton> modositando = new ArrayList<>(Field.getTektonList());
            for (Tekton t : modositando) {
                t.tektonTores();
            }

        } else {
            System.out.println(rsz.getName() + ": Sikertelen rovarmozgatás.");
        }
    }

    private void growGombaTest(Gombasz gsz, List<String> args) {
        Scanner scanner = new Scanner(System.in);
        Tekton celTekton = null;

        System.out.println("[Gombatest növesztés]");

        // Céltekton bekérése és validálása
        while (true) {
            System.out.println("\tElérhető tektonok (id-k):");
            List<Tekton> tektonList = field.getTektonList();
            for (Tekton t : tektonList) {
                System.out.print(t.getId() + " ");
            }
            System.out.println();

            System.out.println("\tCéltekton:");
            System.out.print("\t\t> ");
            try {
                int tektonId = Integer.parseInt(scanner.nextLine().trim());
                if (tektonId >= 0 && tektonId < tektonList.size()) {
                    celTekton = tektonList.get(tektonId);
                    break;
                } else {
                    System.out.println("\tNincs ilyen azonosítójú tekton. Próbáld újra.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Gombatest növesztés meghívása
        gsz.gombatestNovesztes(celTekton, false);
    }


    private void cutFonal(Rovarasz rsz, List<String> args) {
        //TODO - bonyolultabb fonalstruktúrák vágása
        Scanner scanner = new Scanner(System.in);
        Rovar rovar = null;
        Tekton tekton = null;

        System.out.println("[Fonal elvágása]");

        // Rovar kiválasztása és validálása
        while (true) {
            System.out.println("\tRovarász rovárjai (id-k):");
            for (Rovar rv : rsz.getRovarLista()) {
                System.out.print(rv.getId() + " ");
            }
            System.out.println();

            System.out.println("\tRovar:");
            System.out.print("\t\t> ");
            try {
                int rovarId = Integer.parseInt(scanner.nextLine().trim());
                rovar = rsz.getRovarById(rovarId);
                if (rovar != null) break;
                System.out.println("\tNincs ilyen azonosítójú rovar. Próbáld újra.");
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Tekton kiválasztása és validálása (amelyen a fonalat vágjuk)
        while (true) {
            System.out.println("\tElérhető tektonok (id-k):");
            List<Tekton> tektonList = field.getTektonList();
            for (Tekton t : tektonList) {
                System.out.print(t.getId() + " ");
            }
            System.out.println();

            System.out.println("\tTekton ami között vágás történik:");
            System.out.print("\t\t> ");
            try {
                int tektonId = Integer.parseInt(scanner.nextLine().trim());
                if (tektonId >= 0 && tektonId < tektonList.size()) {
                    tekton = tektonList.get(tektonId);
                    break;
                } else {
                    System.out.println("\tNincs ilyen azonosítójú tekton. Próbáld újra.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        //Gombafonal kivétele a modellből
        GombaFonal kivalaztottFonal = null;
        for(GombaFonal gf : rovar.getHelyzet().getKapcsolodoFonalak()) {
            for(GombaFonal gf_2 : tekton.getKapcsolodoFonalak()) {
                if (gf.getID() == gf_2.getID()) {
                    //Van köztes fonal
                    kivalaztottFonal = gf;
                }
            }
        }
        if(kivalaztottFonal == null) {
            System.out.println("Nincs a Rovar tektonja és a kiválasztott Tekton között fonal!");
            return;
        }

        if(kivalaztottFonal.getCelTekton().isDefendFonalak() || kivalaztottFonal.getStartTekton().isDefendFonalak()) {
            System.out.println("[defendFonalak]: A kijelölt fonal nem elvágható");
            return;
        }

        // Fonal vágás meghívása
        if (rsz.fonalVagas(rovar, kivalaztottFonal)) {
            System.out.println(rsz.getName() + ": Sikeres fonalvágás a " + tekton.getId() + ". tektonon.");
        } else {
            System.out.println(rsz.getName() + ": Sikertelen fonalvágás.");
        }
    }


    private void eatRovar(Gombasz gsz, List<String> args) {
        Scanner scanner = new Scanner(System.in);
        Rovar rovar = null;

        System.out.println("[Rovar elfogyasztása]");

        // Rovar kiválasztása és validálása
        while (true) {
            System.out.println("\tElérhető rovarok (id-k):");
            List<Tekton> tektonList = field.getTektonList();
            boolean vanRovar = false;
            for (Tekton t : tektonList) {
                if (t.getRovar() != null) {
                    System.out.print(t.getRovar().getId() + " ");
                    vanRovar = true;
                }
            }
            if (!vanRovar) {
                System.out.println("\tNincsenek rovarok a pályán.");
                return;
            }
            System.out.println();

            System.out.println("\tRovar:");
            System.out.print("\t\t> ");
            try {
                int rovarId = Integer.parseInt(scanner.nextLine().trim());
                // Rovar keresése a pályán
                for (Tekton t : tektonList) {
                    if (t.getRovar() != null && t.getRovar().getId() == rovarId) {
                        rovar = t.getRovar();
                        break;
                    }
                }
                if (rovar != null) {
                    break;
                } else {
                    System.out.println("\tNincs ilyen azonosítójú rovar. Próbáld újra.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\tÉrvénytelen számformátum. Próbáld újra.");
            }
        }

        // Rovar eltávolítása
        if (gsz.rovarEves(rovar)) {
            System.out.println(gsz.getName() + ": Sikeresen elfogyasztotta a " + rovar.getId() + ". azonosítójú rovart.");
        } else {
            System.out.println(gsz.getName() + ": Sikertelen rovar evés.");
        }
    }


    private void printField(List<String> args) { System.out.println(field.printGameState());}

    private void setKezdoHelyzet(List<String> args) { System.out.println("Kezdőhelyzet beállítva."); }

    private void jatekKiertekeles(List<String> args) { System.out.println("Játék kiértékelése."); }

    private void loadMap(List<String> args) { System.out.println("Pálya betöltve."); }

    private void runTest(List<String> args) {
        if (args.size() != 1) {
            System.out.println("Használat: runTest <teszt_szám>");
            return;
        }

        String szam = args.get(0);
        BaseTest teszt = null;

        switch (szam) {
            case "1":
                teszt = new Teszt_01();
                break;
            case "2":
                teszt = new Teszt_02();
                break;
            case "3":
                teszt = new Teszt_03();
                break;
            case "4":
                teszt = new Teszt_04();
                break;
            case "5":
                teszt = new Teszt_05();
                break;
            case "6":
                teszt = new Teszt_06();
                break;
            case "7":
                teszt = new Teszt_07();
                break;
            case "8":
                teszt = new Teszt_08();
                break;
            case "9":
                teszt = new Teszt_09();
                break;
            case "10":
                teszt = new Teszt_10();
                break;
            case "11":
                teszt = new Teszt_11();
                break;
            case "12":
                teszt = new Teszt_12();
                break;
            case "13":
                teszt = new Teszt_13();
                break;
            case "14":
                teszt = new Teszt_14();
                break;
            case "15":
                teszt = new Teszt_15();
                break;
            case "16":
                teszt = new Teszt_16();
                break;
            case "17":
                teszt = new Teszt_17();
                break;
            case "18":
                teszt = new Teszt_18();
                break;
            case "19":
                teszt = new Teszt_19();
                break;
            case "20":
                teszt = new Teszt_20();
                break;
            case "21":
                teszt = new Teszt_21();
                break;
            case "22":
                teszt = new Teszt_22();
                break;
            case "23":
                teszt = new Teszt_23();
                break;
            case "24":
                teszt = new Teszt_24();
                break;
            case "25":
                teszt = new Teszt_25();
                break;
            case "26":
                teszt = new Teszt_26();
                break;
            default:
                System.out.println("Nincs ilyen teszt: " + szam);
                return;
        }

        if (teszt != null) {
            System.out.println("Teszt futtatása...");
            teszt.runTest();
            System.out.println("Teszt befejeződött.");
            System.out.println("Teszt eredménye fájlba mentve.");
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
                Field.getTektonList().clear();
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
                rovar.setRovarasz(r);
                r.addRovar(rovar, tektonList.get(tIndex));
                rovar.setHelyzet(tektonList.get(tIndex));
                tektonList.get(tIndex).setRovar(rovar);
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
                        }
                    }
                }
            }
        }, 2000, 2000); // 20 (20000)másodpercenként
    }
}
