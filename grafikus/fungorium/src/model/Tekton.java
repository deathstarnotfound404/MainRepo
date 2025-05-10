package model;
import java.util.*;

/**
 * Represents a cell (field segment) in the game world.
 * Tekton is a fundamental building block of the game environment that can contain spores,
 * fungal threads, insects, and fungi. It manages interactions between these elements and
 * maintains connections with neighboring Tektons.
 * Implements the IDestroyable interface to handle its destruction.
 */
public class Tekton implements IDestroyable {
    /** Unique identifier for this Tekton */
    private int id;

    /** Counter tracking how many times insects have visited this cell */
    private int rovarLatogatottsag = 0;

    /** Number of fungal threads connected to this Tekton */
    private int fonalFokszam = 0;

    /** List of spores present on this Tekton */
    private List<BaseSpora> sporaLista;

    /** List of fungal threads connected to this Tekton */
    private List<GombaFonal> kapcsolodoFonalak;

    /** List of neighboring Tekton cells */
    private List<Tekton> szomszedosTektonok;

    /** Flag indicating if fungal threads are protected from destruction */
    private boolean defendFonalak = false;

    /** Flag limiting the Tekton to have at most one fungal thread */
    private boolean maxEgyFonal = false;

    /** Flag indicating if a fungal body is present on this Tekton */
    private boolean vanGombaTestTektonon = false;

    /** Flag indicating if fungal body growth is inhibited on this Tekton */
    private boolean gtGatlo = false;

    /** Special effect associated with this Tekton */
    private TektonHatas tektonHatas;

    /** Fungus located on this Tekton */
    private Gomba tektononLevoGomba;

    /** Insect located on this Tekton */
    private Rovar tektononLevoRovar;

    /**
     * Constructs a new Tekton with a specific effect.
     *
     * @param hatas the effect to associate with this Tekton
     */
    public Tekton(TektonHatas hatas) {
        id = Field.genID();
        sporaLista = new ArrayList<>();
        kapcsolodoFonalak = new ArrayList<>();
        szomszedosTektonok = new ArrayList<>();
        tektonHatas = hatas;
    }

    /**
     * Returns the list of neighboring Tektons.
     *
     * @return list of adjacent Tektons
     */
    public List<Tekton> getSzomszedok() {
        return this.szomszedosTektonok;
    }

    /**
     * Triggers the effect associated with this Tekton.
     *
     * @return a description of the applied effect
     */
    public String hatasKifejtes() {
        return tektonHatas.hatas();
    }

    /**
     * Returns the number of spores present on this Tekton.
     *
     * @return count of spores
     */
    public int sporaCount() {
        return sporaLista.size();
    }

    /**
     * Adds a spore to this Tekton.
     *
     * @param spora the spore to add
     */
    public void addSpora(BaseSpora spora) {
        sporaLista.add(spora);
    }

    /**
     * Handles the splitting of this Tekton when it has been visited too many times.
     * Creates two new Tektons that inherit the connections of the original one.
     *
     * @return true if the Tekton was split, false otherwise
     */
    public boolean tektonTores(){
        if(rovarLatogatottsag >= 10) {
            this.fonalakFelszivasa();

            if(tektononLevoGomba != null) {
                tektononLevoGomba.elpusztul();
            }

            List<Tekton> meglevoSzomszedok = szomszedosTektonok;

            Tekton ujTekton1 = new Tekton(TektonHatas.generateRandomTektonHatas());
            Tekton ujTekton2= new Tekton(TektonHatas.generateRandomTektonHatas());
            Field.addTekton(ujTekton1);
            Field.addTekton(ujTekton2);

            ujTekton1.addSzomszedosTekton(ujTekton1);
            ujTekton2.addSzomszedosTekton(ujTekton2);
            ujTekton1.getSzomszedok().addAll(this.szomszedosTektonok);
            ujTekton2.getSzomszedok().addAll(this.szomszedosTektonok);

            for (Tekton t : meglevoSzomszedok) {
                t.addSzomszedosTekton(ujTekton1);
                t.addSzomszedosTekton(ujTekton2);
            }

            if(this.vanRovarATektonon()) {
                ujTekton1.setRovar(tektononLevoRovar);
                tektononLevoRovar.setHelyzet(ujTekton1);
                this.setRovar(null);
            }

            //Régi tekton törlése
            this.elpusztul();
            System.out.println("[TektonTores] Tekton kettétört.");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there's an insect on this Tekton.
     *
     * @return true if an insect is present, false otherwise
     */
    public boolean vanRovarATektonon(){
        return this.tektononLevoRovar != null;
    }

    /**
     * Sets the neighboring Tektons.
     *
     * @param szomszedok list of neighboring Tektons
     */
    public void setSzomszedok(List<Tekton> szomszedok) {
        this.szomszedosTektonok = szomszedok;
    }

    /**
     * Clears all fungal thread connections if they're not protected.
     */
    public void clearKapcsolodoFonalak(){
        if(!defendFonalak){
            this.kapcsolodoFonalak.clear();
            this.fonalFokszam = 0;
        }
    }

    /**
     * Absorbs all connected fungal threads if they're not protected.
     * Removes the threads from their parent fungi.
     */
    public void fonalakFelszivasa(){
        if(!defendFonalak) {
            for(GombaFonal gf : kapcsolodoFonalak) {
                gf.getAlapGomba().deleteFonal(gf);
            }
            clearKapcsolodoFonalak();
        }
    }

    /**
     * Sets whether a fungal body is present on this Tekton.
     *
     * @param val true if a fungal body is present, false otherwise
     */
    public void setVanGombaTest(boolean val){
        this.vanGombaTestTektonon = val;
    }

    /**
     * Checks if a fungal body is present on this Tekton.
     *
     * @return true if a fungal body is present, false otherwise
     */
    public boolean getVanGombaTest(){
        return this.vanGombaTestTektonon;
    }

    /**
     * Returns the fungus present on this Tekton.
     *
     * @return the fungus, or null if none is present
     */
    public Gomba getGomba(){
        return this.tektononLevoGomba;
    }

    /**
     * Returns the list of spores on this Tekton.
     *
     * @return list of spores
     */
    List<BaseSpora> getSporaLista() {
        return sporaLista;
    }

    /**
     * Returns the number of fungal threads connected to this Tekton.
     *
     * @return count of connected threads
     */
    public int getFokszam(){
        return this.fonalFokszam;
    }

    /**
     * Increments the visitor counter for this Tekton.
     */
    public void addLatogatottsag() {
        this.rovarLatogatottsag++;
    }

    /**
     * Returns the number of times this Tekton has been visited.
     *
     * @return visit count
     */
    public int getLatogatottsag() {
        return this.rovarLatogatottsag;
    }

    /**
     * Returns the list of fungal threads connected to this Tekton.
     *
     * @return list of connected fungal threads
     */
    public List<GombaFonal> getKapcsolodoFonalak() {
        return kapcsolodoFonalak;
    }

    /**
     * Adds a fungal thread connection to this Tekton if possible.
     * The addition may fail if the maxEgyFonal constraint is active.
     *
     * @param gf the fungal thread to connect
     * @return true if the thread was successfully connected, false otherwise
     */
    public boolean addKapcsolodoFonalak(GombaFonal gf) {
        if(maxEgyFonal) {
            if(kapcsolodoFonalak.size() >= 1) {
                return false;
            }
        }

        if(!kapcsolodoFonalak.contains(gf)){
            this.kapcsolodoFonalak.add(gf);
            this.fonalFokszam = kapcsolodoFonalak.size();
        }

        return true;
    }

    /**
     * Sets the insect on this Tekton.
     * Only one insect can be present at a time.
     *
     * @param r the insect to place on this Tekton
     * @return true if the insect was successfully set or removed, false otherwise
     */
    public boolean setRovar(Rovar r) {
        if(tektononLevoRovar!=null){    //Ha van már rovar a tektonon
            if(r == null){  //HA ezt null ra akarjuk állítani
                rovarLatogatottsag++;
                tektononLevoRovar = null;
                return true;
            } else {
                return false;   //már van egy rovar a tektonon, tekton nem üres
            }
        } else {
            this.tektononLevoRovar = r;
            return true;
        }
    }

    /**
     * Returns the insect present on this Tekton.
     *
     * @return the insect, or null if none is present
     */
    public Rovar getRovar(){
        return this.tektononLevoRovar;
    }

    /**
     * Sets the fungus on this Tekton if conditions allow.
     * A fungus can only be placed if there isn't one already,
     * and if fungal growth is not inhibited.
     *
     * @param g the fungus to place, or null to remove the current fungus
     */
    public void setGomba(Gomba g){
        if(g != null) {
            if(!vanGombaTestTektonon && this.tektononLevoGomba == null && !gtGatlo) {
                this.tektononLevoGomba = g;
                vanGombaTestTektonon = true;
            }
        } else {
            this.tektononLevoGomba = null;
            vanGombaTestTektonon = false;
        }
    }

    /**
     * Returns the list of neighboring Tektons.
     *
     * @return list of neighboring Tektons
     */
    public List<Tekton> getSzomszedosTektonok(){
        return szomszedosTektonok;
    }

    /**
     * Removes a fungal thread connection if it's not protected.
     *
     * @param gf the fungal thread to remove
     */
    public void removeKapcsolodoFonal(GombaFonal gf) {
        if(this.kapcsolodoFonalak.contains(gf) && !this.defendFonalak){
            this.kapcsolodoFonalak.remove(gf);
            this.fonalFokszam = kapcsolodoFonalak.size();
        } else {
            return;
        }
    }

    /**
     * Removes a specific number of spores from this Tekton.
     * If there are fewer spores than requested, removes all of them.
     *
     * @param val number of spores to remove
     */
    public void removeSporak(int val){
        if(this.sporaLista.size() >= val){
            //Ekkor eltávolítható val-nyi
            sporaLista.subList(0, val).clear();
        } else {
            sporaLista.clear();
        }
    }

    /**
     * Adds a neighboring Tekton if not already connected.
     *
     * @param t the Tekton to add as a neighbor
     */
    public void addSzomszedosTekton(Tekton t) {
        if(!szomszedosTektonok.contains(t)){
            szomszedosTektonok.add(t);
        }
    }

    /**
     * Removes a neighboring Tekton connection.
     * Also removes the reciprocal connection.
     *
     * @param t the Tekton to remove from neighbors
     * @return true if the neighbor was successfully removed, false otherwise
     */
    public boolean removeSzomszedosTekton(Tekton t){
        if(t.getSzomszedosTektonok().contains(this)){
            t.getSzomszedosTektonok().remove(this);
        }
        return szomszedosTektonok.remove(t);
    }

    /**
     * Sets the protection status for fungal threads on this Tekton.
     *
     * @param val true to protect threads, false to allow them to be removed
     */
    public void setDefendFonalak(boolean val){
        this.defendFonalak = val;
    }

    /**
     * Checks if fungal threads are protected on this Tekton.
     *
     * @return true if threads are protected, false otherwise
     */
    public boolean isDefendFonalak(){
        return this.defendFonalak;
    }

    /**
     * Sets whether this Tekton is limited to having at most one fungal thread.
     *
     * @param val true to limit to one thread, false to allow multiple threads
     */
    public void setMaxEgyFonal(boolean val){
        this.maxEgyFonal = val;
    }

    /**
     * Checks if this Tekton is limited to having at most one fungal thread.
     *
     * @return true if limited to one thread, false otherwise
     */
    public boolean isMaxEgyFonal(){
        return this.maxEgyFonal;
    }

    /**
     * Returns the effect associated with this Tekton.
     *
     * @return the Tekton effect
     */
    public TektonHatas getTektonHatas() {
        return tektonHatas;
    }

    /**
     * Returns the unique ID of this Tekton.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Destroys this Tekton, cleaning up all its associations.
     * Removes it from the field and disconnects all neighbors.
     */
    @Override
    public void elpusztul() {
        if(tektononLevoGomba != null) {
            this.tektononLevoGomba.elpusztul();
        }
        this.sporaLista.clear();
        this.kapcsolodoFonalak.clear();
        Field.getTektonList().remove(this);
        for (Tekton t : Field.getTektonList()){
            t.getSzomszedosTektonok().remove(this);
        }
    }

    /**
     * Checks if two Tektons are connected by a common fungal thread.
     *
     * @param t1 first Tekton to check
     * @param t2 second Tekton to check
     * @return true if they share a fungal thread connection, false otherwise
     */
    public static boolean ketTektonFonallalOsszekotott(Tekton t1, Tekton t2){
        List<GombaFonal> startFonalak = t1.getKapcsolodoFonalak();
        List<GombaFonal> celFonalak = t2.getKapcsolodoFonalak();

        boolean vanKozosFonal = false;

        for (GombaFonal f1 : startFonalak) {
            for (GombaFonal f2 : celFonalak) {
                if (f1.getID() == f2.getID()) {
                    vanKozosFonal = true;
                    break;
                }
            }
            if (vanKozosFonal) break;
        }

        return vanKozosFonal;
    }

    /**
     * Checks if fungal body growth is inhibited on this Tekton.
     *
     * @return true if fungal growth is inhibited, false otherwise
     */
    public boolean isGtGatlo(){
        return gtGatlo;
    }

    /**
     * Sets whether fungal body growth should be inhibited on this Tekton.
     *
     * @param val true to inhibit fungal growth, false to allow it
     */
    public void setGtGatlo(boolean val){
        this.gtGatlo = val;
    }

    /**
     * Creates a string representation of this Tekton's state.
     * Includes details about visitor count, connected threads, neighboring cells,
     * and presence of fungi and insects.
     *
     * @return a detailed string representation of the Tekton
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\trovarLatogatottsag: ").append(this.rovarLatogatottsag).append("\n");
        sb.append("\tfonalFokszam: ").append(this.fonalFokszam).append("\n");
        sb.append("\tsporaListaSize: ").append(this.sporaLista.size()).append("\n");
        sb.append("\tszomszedosTektonokSize: ").append(this.szomszedosTektonok.size()).append("\n");

        if(defendFonalak) {
            sb.append("\tdefendFonalak: 1\n");
        } else {
            sb.append("\tdefendFonalak: 0\n");
        }

        if (tektononLevoGomba != null) {
            sb.append("\tgombatest: 1 - sporaszam: ")
                    .append(tektononLevoGomba.getGombatest().getSporakeszlet())
                    .append(" - fonalszam: ")
                    .append(tektononLevoGomba.getFonalKeszlet())
                    .append(" - szint: ")
                    .append(tektononLevoGomba.getGombatest().getSzint())
                    .append(" - szorasCount:")
                    .append(tektononLevoGomba.getGombatest().getSzorasCount())
                    .append("\n");
        } else {
            sb.append("\tgombatest: 0\n");
        }

        if (tektononLevoRovar == null) {
            sb.append("\trovar: 0\n");
        } else {
            sb.append("\trovar: 1 - ID: ").append(tektononLevoRovar.getID()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Establishes a bidirectional neighbor relationship between two Tektons.
     *
     * @param t1 first Tekton to connect
     * @param t2 second Tekton to connect
     */
    public static void connectSzomszedok(Tekton t1, Tekton t2) {
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
    }

    public boolean isSzomszedok(Tekton t) {
        if(szomszedosTektonok.contains(t)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the visitor count for this Tekton.
     *
     * @param i the new visitor count
     */
    public void setRovarLatogatottsag(int i) {
        this.rovarLatogatottsag = i;
    }
}