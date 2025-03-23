package FungoriumClasses;
import CallTracer.*;
import java.util.*;

/**
 * A {@code Field} osztály a játékteret reprezentálja, amely tartalmazza a tektonokat és a játékosokat.
 *
 * <p>Ez az osztály felelős a tektonok és játékosok kezeléséért, valamint a játék végén az eredmények kiértékeléséért.</p>
 *
 * <p>Kapcsolódó osztályok:</p>
 * <ul>
 *     <li>{@link Tekton} - A játékteret alkotó egységek.</li>
 *     <li>{@link Player} - A játékban részt vevő játékosok.</li>
 *     <li>{@link Rovarasz} - A rovarász szerepű játékosokat reprezentáló osztály.</li>
 *     <li>{@link Gombasz} - A gombász szerepű játékosokat reprezentáló osztály.</li>
 * </ul>
 *
 * @author NAME
 * @version 1.0
 * @since 2025-03-18
 */
public class Field {
    /** A játéktéren található tektonok listája. */
    private List<Tekton> tektonLista;

    /** A játéktéren lévő játékosok listája. */
    private List<Player> players;

    /**
     * Létrehoz egy új {@code Field} objektumot üres tekton és játékos listával.
     */
    public Field() {
        this.tektonLista = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    /**
     * Kiértékeli a játékot és visszaadja a legjobb rovarászt és gombászt.
     *
     * <p>A kiértékelés során minden játékos pontszáma alapján meghatározza:</p>
     * <ul>
     *     <li>A legjobb {@code Rovarasz} játékost.</li>
     *     <li>A legjobb {@code Gombasz} játékost.</li>
     * </ul>
     *
     * @return Egy lista, amely tartalmazza a legjobb gombászt és rovarászt.
     */
    public List<Player> kiertekeles() {
        Rovarasz bestRovarasz = null;
        Gombasz bestGombasz = null;
        for(Player p : players){
            // Rovarász keresése
            if(p.getClass() == (Rovarasz.class) && bestRovarasz == null) bestRovarasz = (Rovarasz)p;
            else if(p.getClass() == (Rovarasz.class) && p.getScore() > bestRovarasz.getScore()){
                bestRovarasz = (Rovarasz)p;
            }
            // Gombász keresése
            if(p.getClass() == (Gombasz.class) && bestGombasz == null) bestGombasz = (Gombasz)p;
            else if(p.getClass() == (Gombasz.class) && p.getScore() > bestGombasz.getScore()){
                bestGombasz = (Gombasz)p;
            }
        }
        List<Player> bestPlayers = new ArrayList<Player>();
        bestPlayers.add(bestGombasz);
        bestPlayers.add(bestRovarasz);
        return bestPlayers;
    }

    /**
     * Hozzáad egy játékost a játékhoz.
     *
     * @param player A hozzáadandó játékos.
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Hozzáad egy tekton objektumot a játéktérhez.
     *
     * @param tekton A hozzáadandó tekton.
     */
    public void addTekton(Tekton tekton) {
        this.tektonLista.add(tekton);
    }

    /**
     * Minden tektonhoz beállítja az összes többi tektont szomszédosként.
     *
     * <p>Minden tektonhoz hozzáadja az összes többi tektont szomszédosként, és ezt naplózza is a {@link CallTracer} segítségével.</p>
     */
    public void setAllTektonSzomszed() {
        for(Tekton t : tektonLista){
            for(Tekton tt : tektonLista){
                if(t != tt){
                    CallTracer.enter("addSzomszedosTekton", "Tekton", "t");
                    tt.addSzomszedosTekton(t);
                    CallTracer.exit("addSzomszedosTekton", "");
                }
            }
        }
    }

    /**
     * Visszaadja a játékteret alkotó tektonok listáját.
     *
     * @return A tektonok listája.
     */
    public List<Tekton> getTektonLista(){return tektonLista;}
}
