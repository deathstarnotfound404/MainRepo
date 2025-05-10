package model;
import java.util.*;

/**
 * Represents the game field that contains all the game elements.
 * This class manages the game environment including Tekton cells, players,
 * and handles game initialization, evaluation, and state tracking.
 */
public class Field {
    /** Counter to generate unique IDs */
    private static int idCounter = 0;

    TimeHandler timeHandler;

    /** List of all Tekton cells in the field */
    private static List<Tekton> tektonLista = new ArrayList<>();

    /** Map of players and their types (G for Gombasz/Mycologist, R for Rovarasz/Entomologist) */
    private Map<Player, String> playerLista = new HashMap<>();

    /**
     * Constructs a new empty Field.
     */
    public Field() {
        timeHandler = new TimeHandler();
    }

    /**
     * Adds a Gombasz (Mycologist) player to the game.
     *
     * @param p the Gombasz player to add
     */
    public void addGombasz(Gombasz p) {
        playerLista.put(p, "G");
    }

    /**
     * Adds a Rovarasz (Entomologist) player to the game.
     *
     * @param p the Rovarasz player to add
     */
    public void addRovarasz(Rovarasz p) {
        playerLista.put(p, "R");
    }

    /**
     * Adds a Tekton cell to the field.
     *
     * @param t the Tekton to add
     */
    public static void addTekton(Tekton t) {
        tektonLista.add(t);
    }

    /**
     * Evaluates the game state and determines the winners.
     * The first element in the returned list is the winning Rovarasz (Entomologist),
     * and the second is the winning Gombasz (Mycologist).
     *
     * @return a list containing the winning players in order: Rovarasz, then Gombasz
     */
    public List<Player> kiertekeles() {
        int maxRovaraszScore = 0;
        int maxGombaszScore = 0;

        for (Map.Entry<Player, String> entry : playerLista.entrySet()) {
            Player player = entry.getKey();
            String str = entry.getValue();

            if (str.equals("R")) {
                int score = player.getScore();
                if (score > maxRovaraszScore) {
                    maxRovaraszScore = score;
                }
            } else if (str.equals("G")) {
                int score = player.getScore();
                if (score > maxGombaszScore) {
                    maxGombaszScore = score;
                }
            }
        }

        List<Player> eredmeny = new ArrayList<>();

        for (Map.Entry<Player, String> entry : playerLista.entrySet()) {
            Player player = entry.getKey();
            String str = entry.getValue();

            if (str.equals("R")) {
                int score = player.getScore();
                if (score == maxRovaraszScore && player!=null) {
                    eredmeny.add(player);
                }
            }
        }

        for (Map.Entry<Player, String> entry : playerLista.entrySet()) {
            Player player = entry.getKey();
            String str = entry.getValue();

            if (str.equals("G")) {
                int score = player.getScore();
                if (score == maxGombaszScore && player!=null) {
                    eredmeny.add(player);
                }
            }
        }

        return eredmeny;
    }

    /**
     * Generates a string representation of the current game state.
     * Includes details about all Tektons on the field.
     *
     * @return a formatted string describing the current game state
     */
    public String printGameState() {
        StringBuilder sb = new StringBuilder();
        int tektonCnt = 0;

        sb.append("[Tektonok]\n");
        for (Tekton t : tektonLista) {
            sb.append("ID: t").append(tektonCnt).append("\n");
            sb.append(t.toString()).append("\n");
            tektonCnt++;
        }
        sb.append("\n---------------------------------------------------------------------------------\n");

        return sb.toString();
    }

    /**
     * Generates a new unique ID.
     *
     * @return a unique integer ID
     */
    public static int genID() {
        int current = idCounter;
        ++idCounter;
        return current;
    }

    /**
     * Returns the list of all Tektons in the field.
     *
     * @return the list of Tektons
     */
    public static List<Tekton> getTektonList() {
        return tektonLista;
    }

    /**
     * Initializes the game by creating Tekton cells and setting up their neighbor relationships.
     * Creates 12 Tektons with random effects and connects them in a specific topology.
     */
    public void initGame( String g1Name, String g2Name, String r1Name, String r2Name) {
        if(g1Name != null) {
            Gombasz g1 = new Gombasz(g1Name);
            this.addGombasz(g1);

            if(g2Name != null) {
                Gombasz g2 = new Gombasz(g2Name);
                this.addGombasz(g2);
            }
        }

        if(r1Name != null) {
            Rovarasz r1 = new Rovarasz(r1Name);
            this.addRovarasz(r1);

            if(r2Name != null) {
                Rovarasz r2 = new Rovarasz(r2Name);
                this.addRovarasz(r2);
            }
        }

        //Létrehozza a játék mapot

        for (int i = 0; i<12; i++) {
            Tekton t = new Tekton(TektonHatas.generateRandomTektonHatas());
            t.getTektonHatas().setTekton(t);
            if(!t.getTektonHatas().isHatasEsemenyfuggo()) {
                t.hatasKifejtes();  //Ha a hatás nem események bekövetkezésétől, hanem kezdettől fogva érvényes
            }
            tektonLista.add(t);
            tektonLista.get(i).getTektonHatas().setTekton(tektonLista.get(i));
        }

        //Szomszédok beállítása
        //1
        Tekton.connectSzomszedok(tektonLista.get(0), tektonLista.get(5));
        //2
        Tekton.connectSzomszedok(tektonLista.get(5), tektonLista.get(1));
        //3
        Tekton.connectSzomszedok(tektonLista.get(1), tektonLista.get(6));
        //4
        Tekton.connectSzomszedok(tektonLista.get(1), tektonLista.get(2));
        //5
        Tekton.connectSzomszedok(tektonLista.get(6), tektonLista.get(3));
        //6
        Tekton.connectSzomszedok(tektonLista.get(3), tektonLista.get(7));
        //7
        Tekton.connectSzomszedok(tektonLista.get(7), tektonLista.get(6));
        //8
        Tekton.connectSzomszedok(tektonLista.get(4), tektonLista.get(8));
        //9
        Tekton.connectSzomszedok(tektonLista.get(8), tektonLista.get(9));
        //10
        Tekton.connectSzomszedok(tektonLista.get(9), tektonLista.get(5));
        //11
        Tekton.connectSzomszedok(tektonLista.get(9), tektonLista.get(6));
        //12
        Tekton.connectSzomszedok(tektonLista.get(6), tektonLista.get(10));
        //13
        Tekton.connectSzomszedok(tektonLista.get(10), tektonLista.get(11));
        //14
        Tekton.connectSzomszedok(tektonLista.get(7), tektonLista.get(11));
    }

    public boolean cutFonal(Tekton selectedTekton, Tekton selectedSecondTekton, Rovar r) {
        //Gombafonal kivétele a modellből
        GombaFonal kivalaztottFonal = null;
        for(GombaFonal gf : r.getHelyzet().getKapcsolodoFonalak()) {
            for(GombaFonal gf_2 : selectedSecondTekton.getKapcsolodoFonalak()) {
                if (gf.getID() == gf_2.getID()) {
                    //Van köztes fonal
                    kivalaztottFonal = gf;
                }
            }
        }
        if(kivalaztottFonal == null) {
            System.out.println("Nincs a Rovar tektonja és a kiválasztott Tekton között fonal!");
            return false;
        }

        if(kivalaztottFonal.getCelTekton().isDefendFonalak() || kivalaztottFonal.getStartTekton().isDefendFonalak()) {
            System.out.println("[defendFonalak]: A kijelölt fonal nem elvágható");
            return false;
        }

        final Rovar rovar = r;
        final GombaFonal gf = kivalaztottFonal;

        timeHandler.schedule(() -> r.getRovarasz().fonalVagas(r, gf), 10000, this);
        return true;
    }

    public boolean moveRovar(Rovar selectedRovar, Tekton selectedSecondTekton) {
        final Rovar rovar = selectedRovar;

        if (rovar.getRovarasz().rovarIranyitas(rovar, selectedSecondTekton)) {
            rovar.sporaEves();
            selectedSecondTekton.hatasKifejtes();

            //Időzítés - reset rovar
            timeHandler.schedule(() -> rovar.kepessegekAlaphelyzetbe(), 30000, this);

            List<Tekton> modositando = new ArrayList<>(Field.getTektonList());
            for (Tekton t : modositando) {
                t.tektonTores();
            }

            return true;

        } else {
            System.out.println(rovar.getRovarasz().getName() + ": Sikertelen rovarmozgatás.");
            return false;
        }
    }

    public boolean growFonal(Tekton selectedTekton, Tekton selectedSecondTekton, Gomba gomba) {
        List<Tekton> tektonList = tektonLista;
        // Fonal növesztés meghívása
        if (gomba.getGombasz().gombafonalIranyitas(gomba, selectedTekton, selectedSecondTekton, false)) {
            System.out.println(gomba.getGombasz().getName() + ": Sikeres fonal növesztés a(z) " + selectedTekton.getId() + " -> " + selectedSecondTekton.getId() + " útvonalon.");
            return true;
        } else {
            System.out.println(gomba.getGombasz().getName() + ": Sikertelen fonal növesztés.");
            return false;
        }
    }

    public boolean growGombaTest(Gombasz gsz, Tekton selectedTekton) {
        // Gombatest növesztés meghívása
        return gsz.gombatestNovesztes(selectedTekton, false);
    }

    public boolean spreadSpora(Tekton selectedTekton, Gomba gomba) {
        Tekton celTekton = selectedTekton;
        // Szórás végrehajtása
        return gomba.getGombasz().szoras(gomba, celTekton);
    }

    public boolean eatRovar(Gombasz gsz, Tekton selectedTekton, Rovar selectedRovar) {
        // Rovar eltávolítása
        if (gsz.rovarEves(selectedRovar)) {
            System.out.println(gsz.getName() + ": Sikeresen elfogyasztotta a " + selectedRovar.getId() + ". azonosítójú rovart.");
            return true;
        } else {
            System.out.println(gsz.getName() + ": Sikertelen rovar evés.");
            return false;
        }
    }

    public boolean buyFonal(Gomba g) {
        Gomba gomba = g;

        //Fonal vásárlás
        if (g.getGombasz().fonalVasarlas(gomba)) {
            System.out.println(g.getGombasz().getName() + ": Sikeres fonal vásárlás a kijelölt Gombán: ID: " + gomba.getId());
            return true;
        } else {
            System.out.println(g.getGombasz().getName() + ": Sikertelen fonal vásárlás.");
            return false;
        }
    }

    public Rovar getRovarById(int id) {
        for (Player player : playerLista.keySet()) {
            if(Objects.equals(playerLista.get(player), "R")) {
                Rovarasz r =  (Rovarasz) player;
                for(Rovar i : r.getRovarLista()) {
                    if(i.getId() == id) {
                        return i;
                    }
                }
            }
        }
        return null;
    }

    public GombaTest getGombaTestById(int id) {
        for (Tekton tekton : tektonLista) {
            if(tekton.getVanGombaTest() && tekton.getGomba().getGombatest().getId() == id) {
                return tekton.getGomba().getGombatest();
            }
        }
        return null;
    }

    public Tekton getTektonById(int id) {
        for (Tekton tekton : tektonLista) {
            if(tekton.getId() == id) {
                return tekton;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(playerLista.keySet());
    }

    public List<Tekton> getTektons() {
        return tektonLista;
    }

    public Rovar firstRovar(Rovarasz currentPlayer, Tekton target) {
        Rovar rovar = new Rovar();
        rovar.setRovarasz(currentPlayer);
        currentPlayer.addRovar(rovar, target);
        rovar.setHelyzet(target);
        target.setRovar(rovar);
        System.out.println("Rovar létrehozva a(z) " + target.getId() + ". Tektorra.");
        return rovar;
    }

    public int getPlayerCount() {
        return playerLista.size();
    }

    public Gomba firstGomba(Gombasz currentPlayer, Tekton target) {
        if(target.isGtGatlo()) {
            return null;
        }
        Gomba gomba = new Gomba(target, currentPlayer, 0);
        currentPlayer.addGomba(gomba);
        System.out.println("Gomba létrehozva a(z) " + target.getId() + ". Tektorra.");
        return gomba;
    }

    public Player getFirstGombasz() {
        for (Player player : playerLista.keySet()) {
            if(playerLista.get(player).equals("G")) {
                return player;
            }
        }
        return null;
    }

    public Player getSecondGombasz() {
        if(playerLista.keySet().size() > 2) {
            boolean first = false;
            for (Player player : playerLista.keySet()) {
                if(playerLista.get(player).equals("G")) {
                    if(!first) {
                        first = true;
                    } else {
                        return player;
                    }
                }
            }
        } else {
            return null;
        }

        return null;
    }

    public Player getFirstRovarasz() {
        for (Player player : playerLista.keySet()) {
            if(playerLista.get(player).equals("R")) {
                return player;
            }
        }
        return null;
    }

    public Player getSecondRovarasz() {
        if(playerLista.keySet().size() > 2) {
            boolean first = false;
            for (Player player : playerLista.keySet()) {
                if(playerLista.get(player).equals("R")) {
                    if(!first) {
                        first = true;
                    } else {
                        return player;
                    }
                }
            }
        } else {
            return null;
        }

        return null;
    }

    public void sporaTermeles() {
        for (Player player : playerLista.keySet()) {
            if (player instanceof Gombasz g) {
                g.sporaTermelesAll();
            }
        }
    }
}