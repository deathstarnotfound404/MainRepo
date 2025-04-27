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

    /** List of all Tekton cells in the field */
    private static List<Tekton> tektonLista = new ArrayList<>();

    /** Map of players and their types (G for Gombasz/Mycologist, R for Rovarasz/Entomologist) */
    private Map<Player, String> playerLista = new HashMap<>();

    /**
     * Constructs a new empty Field.
     */
    public Field() {}

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
        ++idCounter;
        return idCounter;
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
    public void initGame(){
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
}