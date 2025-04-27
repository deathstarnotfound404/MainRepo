package model;
import java.util.*;

/**
 * Represents a Mycologist player in the game.
 * The Gombasz (Mycologist) controls fungi and manages their actions,
 * such as growing fungal threads, spreading spores, and consuming insects.
 * This player's score is determined by the number of fungal bodies they control.
 * Extends the Player base class.
 */
public class Gombasz extends Player {
    /** List of fungi controlled by this player */
    private List<Gomba> gombaLista;

    /**
     * Constructs a new Gombasz player with a given name.
     * Initializes an empty list to store controlled fungi.
     *
     * @param name the player's name
     */
    public Gombasz(String name) {
        super(name);
        gombaLista = new ArrayList<>();
    }

    /**
     * Adds a fungus to this player's control and increases the score.
     *
     * @param g the fungus to add
     */
    public void addGomba(Gomba g) {
        this.gombaLista.add(g);
        score++;    //Gomba - GombaTest 1:1
    }

    /**
     * Calculates the total score based on the number of fungal bodies.
     *
     * @return the score (equivalent to the number of fungal bodies)
     */
    public int calcAllGombatestScore() {
        return score;
    }

    /**
     * Deletes disconnected fungal threads that are no longer connected to their parent fungus.
     * Only destroyable threads that are not connected to their parent fungus will be removed.
     *
     * @param disconnectedFonalak list of potentially disconnected fungal threads
     */
    public void deleteFonalak(List<GombaFonal> disconnectedFonalak) {
        for(GombaFonal gf : disconnectedFonalak) {
            if(gf.IsDestroyable() && !gf.connectedToAlapGomba()) {
                gf.getAlapGomba().deleteFonal(gf); //Removes the thread from all instances in the fungus
            }
        }
    }

    /**
     * Checks if a fungal thread can be placed between two Tekton cells.
     * Validates conditions such as existing connections and maximum thread limits.
     *
     * @param t1 the first Tekton cell
     * @param t2 the second Tekton cell
     * @return true if a thread can be placed, false otherwise
     */
    public boolean fonalLerakasEllenorzes(Tekton t1, Tekton t2) {
        //Check if the two Tektons are already connected
        if(Tekton.ketTektonFonallalOsszekotott(t1, t2)){
            System.out.println("Hiba: A két tekton már össze van kötve!");
            return false;
        }

        //Check maxEgyFonal rule violations
        if(t1.isMaxEgyFonal() && t1.getKapcsolodoFonalak().size() >= 1) {
            System.out.println("Hiba: Az egyik tektonon maximum egy fonal lehet!");
            return false;
        }

        if(t2.isMaxEgyFonal() && t2.getKapcsolodoFonalak().size() >= 1) {
            System.out.println("Hiba: Az egyik tektonon maximum egy fonal lehet!");
            return false;
        }

        return true;
    }

    /**
     * Purchases fungal threads for a fungus by spending spores.
     * Decreases the spore count and increases the thread count.
     *
     * @param g the fungus to purchase threads for
     * @return true if the purchase was successful, false if not enough spores
     */
    public boolean fonalVasarlas(Gomba g) {
        if(g.getGombatest().decreaseSporakeszlet(1)) {
            g.increaseFonalkeszlet(3);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Controls the growth of fungal threads between two Tekton cells.
     * Can create bonus threads if conditions are met.
     *
     * @param g the fungus growing the thread
     * @param stratTekton the origin Tekton cell
     * @param celTekton the destination Tekton cell
     * @param ingyen true if the thread should be placed for free, false otherwise
     * @return true if the thread was successfully placed, false otherwise
     */
    public boolean gombafonalIranyitas(Gomba g, Tekton stratTekton, Tekton celTekton, boolean ingyen){
        //Check if placement is valid
        if(fonalLerakasEllenorzes(stratTekton, celTekton)) {
            if(g.fonalFolytonossagVizsgalat(stratTekton)) {

                //Decrease thread stock if not free
                if(!ingyen) {
                    if(!g.decreaseFonalkeszlet()) {
                        System.out.println("Hiba: Nincs elég fonal készlet a fonalnövesztéshez!");
                        return false;
                    }
                }

                //Create and add the new thread
                GombaFonal ujFonal = new GombaFonal(g, stratTekton, celTekton);
                if(!g.addFonal(ujFonal) || !celTekton.addKapcsolodoFonalak(ujFonal) || !stratTekton.addKapcsolodoFonalak(ujFonal)) {
                    System.out.println("Hiba: A fonal nem adható a Gombához vagy a Tektonokhoz!");
                    return false;
                }

                //Check for bonus thread placement
                if(celTekton.getSporaLista().size() > 0 && !ingyen) {
                    System.out.println("\t[Növesztés gyorsítása] Még egy fonal ingyen lerakható ha van szabad Tekton");
                    List<Tekton> celSzomszedok = new ArrayList<>(celTekton.getSzomszedok());
                    celSzomszedok.remove(stratTekton);

                    boolean valasztott = false;
                    while(!valasztott) {
                        if(celSzomszedok.size() == 0) {
                            System.out.println("\nNincs megfelelő Tekton az ingyenes lerakáshoz!");
                            return false;
                        }

                        Random rand = new Random();
                        Tekton randomUJCel = celSzomszedok.get(rand.nextInt(celSzomszedok.size()));
                        if(gombafonalIranyitas(g, celTekton, randomUJCel, true)) {
                            valasztott = true;
                            System.out.println("Ingyenes bónusz fonal lerakva!");
                        } else {
                            celSzomszedok.remove(randomUJCel);
                        }
                    }
                    return true;
                } else {
                    if(!ingyen) {
                        System.out.println("\t[Nincs bónusz fonal lerakás]");
                    }
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns the list of fungi controlled by this player.
     *
     * @return the list of controlled fungi
     */
    public List<Gomba> getGombaLista() {
        return gombaLista;
    }

    /**
     * Grows a new fungal body on a Tekton cell.
     * Requires a minimum number of spores on the cell unless grown for free.
     *
     * @param t the Tekton cell where the fungal body should grow
     * @param ingyen true if growth should be free, false if it requires spores
     * @return true if the growth was successful, false otherwise
     */
    public boolean gombatestNovesztes(Tekton t, boolean ingyen) {
        //Check if the Tekton is available
        if(t.getGomba() != null || t.isGtGatlo() || t.getVanGombaTest()) {
            System.out.println("Hiba: GombaTest nem lerakható a Tektonon!");
            return false;
        }

        int sporaszam = t.getSporaLista().size();

        if(!ingyen) {
            //Check if there are enough spores
            if(sporaszam < 5) {
                System.out.println("Hiba: Nincs elég spóra a céltektonon!");
                return false;
            } else {
                t.removeSporak(sporaszam);
                Gomba ujGomba = new Gomba(t, this, sporaszam - 3);
                addGomba(ujGomba);
                addScore(1);
                System.out.println("[growGombaTest]: Sikeres gombatest növesztés'");
                return true;
            }
        } else {
            t.removeSporak(sporaszam);
            Gomba ujGomba = new Gomba(t, this, sporaszam);
            addGomba(ujGomba);
            addScore(1);
            System.out.println("[growGombaTest]: Sikeres ingyenes gombatest növesztés'");
            return true;
        }
    }

    /**
     * Filters a list of fungal threads to retain only those that are protected.
     * A thread is protected if either of its connected Tektons has thread protection enabled.
     *
     * @param listOfDisconnectedFonalak list of fungal threads to filter
     * @return list containing only the protected threads
     */
    public static List<GombaFonal> protectedSzures(List<GombaFonal> listOfDisconnectedFonalak) {
        List<GombaFonal> filtered = new ArrayList<>();

        for (GombaFonal fonal : listOfDisconnectedFonalak) {
            Tekton start = fonal.getStartTekton();
            Tekton cel = fonal.getCelTekton();

            if (start.isDefendFonalak() || cel.isDefendFonalak()) {
                filtered.add(fonal);
            }
        }

        return filtered;
    }

    /**
     * Triggers spore production for all fungi controlled by this player.
     */
    public void sporaTermelesAll() {
        for (Gomba g : gombaLista) {
            g.sporaTermeles();
        }
    }

    /**
     * Commands a fungus to spread spores to a target Tekton cell.
     * Also triggers level progression for the fungal body.
     *
     * @param g the fungus to spread spores
     * @param celTekton the target Tekton cell
     * @return true if spreading was successful, false otherwise
     */
    public boolean szoras(Gomba g, Tekton celTekton) {
        boolean ret = g.szor(celTekton, g.getGombatest());
        g.gombatestSzintlepes();
        return ret;
    }

    /**
     * Calculates and returns the player's score.
     * Implements the abstract method from the Player class.
     *
     * @return the player's current score
     */
    @Override
    public int getScoreFromPlayer(){
        return calcAllGombatestScore();
    }

    /**
     * Finds and returns a fungus with the specified ID.
     *
     * @param id the ID of the fungus to find
     * @return the matching fungus, or null if no fungus with that ID was found
     */
    public Gomba getGombaById(int id) {
        for(Gomba g : gombaLista) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    /**
     * Attempts to consume a paralyzed insect with a fungus.
     * The insect must be paralyzed (eating efficiency 0) and accessible via fungal threads.
     * If successful, a new fungal body grows in the insect's location.
     *
     * @param r the insect to consume
     * @return true if the insect was consumed, false otherwise
     */
    public boolean rovarEves(Rovar r){
        if(r.getEvesHatekonysag() != 0) {
            System.out.println("Hiba: Csak bénült Rovarok ehetőek meg!");
            return false;
        }

        boolean rFound = false;
        Tekton helyzet = r.getHelyzet();

        for(Gomba g : this.getGombaLista()) {
            for(List<GombaFonal> l : g.getFonalLista()){
                for(GombaFonal gf : l){
                    if(gf.getStartTekton().getRovar() != null){
                        if(gf.getStartTekton().getRovar().getId() == r.getId()){
                            r.getRovarasz().removeRovar(r);
                            System.out.println("[eatRovar]: Sikeres!");
                            rFound = true;
                        }
                    }

                    if(gf.getCelTekton().getRovar() != null){
                        if(gf.getCelTekton().getRovar().getId() == r.getId()){
                            r.getRovarasz().removeRovar(r);
                            rFound = true;
                        }
                    }
                }
            }
        }

        if(rFound){
            //Grow a new fungal body
            this.gombatestNovesztes(helyzet, true);
            return true;
        }

        System.out.println("Hiba: A kijelölt ovar nem érhető el fonallal!");
        return false;
    }
}