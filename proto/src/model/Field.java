package model;
import java.util.*;

public class Field {
    private static int idCounter = 0;
    private static List<Tekton> tektonLista = new ArrayList<>();
    private Map<Player, String> playerLista = new HashMap<>();

    public Field() {}

    public void addGombasz(Gombasz p) {
        playerLista.put(p, "G");
    }

    public void addRovarasz(Rovarasz p) {
        playerLista.put(p, "R");
    }

    public static void addTekton(Tekton t) {
        tektonLista.add(t);
    }

    //Fontos, hogy az 1.elem lesz a nyertes rovarasz, 2. a nyertes gombasz
    public  List<Player> kiertekeles() {
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


    public void setAllTektonSzomszed() {
        //TODO ????
    }

    public static int genID() {
        ++idCounter;
        return idCounter;
    }

    public static List<Tekton> getTektonList() {
        return tektonLista;
    }

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