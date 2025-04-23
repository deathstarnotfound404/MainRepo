package model;
import java.util.*;

public class Field {
    private static int idCounter = 0;
    private static List<Tekton> tektonLista = new ArrayList<>();
    private static Map<Player, String> playerLista = new HashMap<Player, String>();

    public Field() {}

    public static void addGombasz(Gombasz p) {
        playerLista.put(p, "G");
    }

    public static void addRovarasz(Rovarasz p) {
        playerLista.put(p, "R");
    }

    public static  void addTekton(Tekton t) {
        tektonLista.add(t);
    }

    //Fontos, hogy az 1.elem lesz a nyertes rovarasz, 2. a nyertes gombasz
    public static List<Player> kiertekeles() {
        int maxRovaraszScore = 0;
        int maxGombaszScore = 0;

        Player topRovarasz = null;
        Player topGombasz = null;

        for (Map.Entry<Player, String> entry : playerLista.entrySet()) {
            Player player = entry.getKey();
            String str = entry.getValue();

            if (str.equals("R")) {
                int score = player.getScore();
                if (score > maxRovaraszScore) {
                    maxRovaraszScore = score;
                    topRovarasz = player;
                }
            } else if (str.equals("G")) {
                int score = player.getScore();
                if (score > maxGombaszScore) {
                    maxGombaszScore = score;
                    topGombasz = player;
                }
            }
        }

        List<Player> eredmeny = new ArrayList<>();
        if (topRovarasz != null) eredmeny.add(topRovarasz);
        if (topGombasz != null) eredmeny.add(topGombasz);

        return eredmeny;
    }


    public static void printGameState() {
        int tektonCnt = 0;
        System.out.println("[Tektonok]");
        for (Tekton t : tektonLista) {
            System.out.println("ID: t" + tektonCnt);
            System.out.println(t.toString());
        }
        System.out.println("\n---------------------------------------------------------------------------------");
    }

    public static void setAllTektonSzomszed() {
        //TODO ????
    }

    public static int genID() {
        ++idCounter;
        return idCounter;
    }

    public static List<Tekton> getTektonList() {
        return tektonLista;
    }
}