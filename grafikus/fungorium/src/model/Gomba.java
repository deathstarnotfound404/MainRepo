package model;
import java.util.*;

/**
 * Represents a fungus in the game world.
 * The Gomba (fungus) consists of a central fungal body (GombaTest) and a network
 * of fungal threads (GombaFonal) that connect various Tekton cells. Fungi are controlled
 * by Gombasz (Mycologist) players and can spread spores, grow threads, and consume insects.
 * Implements the IDestroyable interface to handle its destruction.
 */
public class Gomba implements IDestroyable {
    /** List of fungal thread paths, each path is a list of connected threads */
    private List<List<GombaFonal>> fonalLista;

    /** Current stock of fungal threads available for growth */
    private int fonalKeszlet = 0;

    /** The Tekton cell where this fungus's central body is located */
    private Tekton tekton;

    /** The central fungal body of this fungus */
    private GombaTest gombaTest;

    /** Unique identifier for this fungus */
    private int id;

    /** The player that controls this fungus */
    private Gombasz gombasz;

    /**
     * Constructs a new fungus with a central body on a specified Tekton cell.
     *
     * @param t the Tekton cell where the fungal body will be placed
     * @param gsz the Gombasz player that will control this fungus
     * @param kezdoSporaszam initial number of spores for the fungal body
     */
    public Gomba(Tekton t, Gombasz gsz, int kezdoSporaszam) {
        id = Field.genID();
        gombasz = gsz;
        tekton = t;
        t.setGomba(this);   // Set the fungus on the Tekton cell
        fonalLista = new ArrayList<>();
        gombaTest = new GombaTest(this, kezdoSporaszam);
    }

    /**
     * Returns the list of thread paths maintained by this fungus.
     * Each path is represented as a list of connected fungal threads.
     *
     * @return the list of thread paths
     */
    public List<List<GombaFonal>> getFonalLista() { return fonalLista; }

    /**
     * Sets the stock of fungal threads available for growth.
     *
     * @param val the new thread stock value
     */
    public void setFonalKeszlet(int val) {
        fonalKeszlet = val;
    }

    /**
     * Returns the current stock of fungal threads available for growth.
     *
     * @return the thread stock
     */
    public int getFonalKeszlet() {
        return fonalKeszlet;
    }

    /**
     * Returns the central fungal body of this fungus.
     *
     * @return the fungal body
     */
    public GombaTest getGombatest() {
        return gombaTest;
    }

    /**
     * Triggers spore production in the fungal body.
     * The amount produced depends on the fungal body's current level.
     */
    public void sporaTermeles() {
        this.gombaTest.addToSporaKeszletTermelessel();
    }

    /**
     * Initiates level progression for the fungal body based on its spore dispersal count.
     * Higher counts lead to level advancement, with potential fungus destruction at maximum level.
     */
    public void gombatestSzintlepes() {
        int mennyiseg = gombaTest.getSzorasCount();
        gombaTest.szintlepes();
    }

    /**
     * Checks for discontinuities in all fungal thread paths.
     * A path is discontinuous if a thread's destination does not match the next thread's origin.
     *
     * @return a list of threads that are part of discontinuous paths
     */
    public List<GombaFonal> fonalFolytonossagVizsgalat() {
        List<GombaFonal> nemfolytonosList = new ArrayList<>();

        // Check continuity by comparing destination of one thread to origin of the next
        // Add all threads after a discontinuity to the list
        for (List<GombaFonal> l : fonalLista) {
            for (int i = 0; i < l.size(); i++) {
                if(l.get(0).getStartTekton().getId() != this.tekton.getId()) {
                    nemfolytonosList.addAll(l);
                }
                if(i < l.size() - 2) {
                    if(l.get(i).getCelTekton().getId() != l.get(i+1).getStartTekton().getId()) {
                        nemfolytonosList.addAll(l.subList(i + 1, l.size()));
                        break;
                    }
                }
            }
        }

        return nemfolytonosList;
    }

    /**
     * Checks if a specific thread path is continuous.
     * A path is continuous if each thread's destination connects to the next thread's origin.
     *
     * @param l_i the thread path to check
     * @return true if the path is continuous, false otherwise
     */
    public boolean fonalFolytonossagVizsgalat(List<GombaFonal> l_i) {
        if(l_i.size() == 0){
            return true;
        }
        for (int i = 0; i < l_i.size(); i++) {
            if(i < l_i.size() - 2) {
                if(l_i.get(i).getCelTekton().getId() != l_i.get(i+1).getStartTekton().getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a specific fungal thread is continuously connected to the fungal body.
     * A thread is continuous if it's part of an unbroken path from the fungal body.
     *
     * @param gf the thread to check
     * @return true if the thread is continuously connected, false otherwise
     */
    public boolean fonalFolytonossagVizsgalat(GombaFonal gf){
        // Check if the thread is part of a continuous path from the fungal body
        for (List<GombaFonal> l : fonalLista) {
            for (int i = 0; i < l.size(); i++) {
                if(i < l.size() - 2) {
                    if(l.get(i).getCelTekton().getId() == l.get(i+1).getStartTekton().getId()) {
                        if(l.get(i).getID() == gf.getID()) {
                            return true;
                        }
                    } else {
                        return false; // If a path is discontinuous, stop checking it
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a specific Tekton cell is continuously connected to the fungal body.
     * A cell is continuous if there is an unbroken path of threads from the fungal body to that cell.
     *
     * @param t the Tekton cell to check
     * @return true if the cell is continuously connected, false otherwise
     */
    public boolean fonalFolytonossagVizsgalat(Tekton t){
        // If the Tekton is where the fungus body is located, it's continuous
        if(t.getId() == this.tekton.getId()){
            return true;
        }

        for (List<GombaFonal> l : fonalLista) {
            // Check all thread paths
            if(l.size() == 0){
                return true;
            }

            if(l.size() == 1) {
                if(l.get(0).getCelTekton().getId() == t.getId()) {
                    return true;
                }
            }

            for (int i = 0; i < l.size() - 1; i++) {
                // Check if the path is continuous
                if(l.get(i).getCelTekton().getId() == l.get(i+1).getStartTekton().getId()) {
                    // Check if the target Tekton is in this continuous path
                    if(l.get(i).getCelTekton().getId() == t.getId() || l.get(i+1).getCelTekton().getId() == t.getId()) {
                        return true;
                    }
                } else {
                    System.out.println("Nem folytonos a hosszabítandó szakasz a GombaTesttel!");
                    return false;  // If a path is discontinuous, stop checking it
                }
            }
        }
        System.out.println("A keresett tekton nincs a listában");
        return false;
    }

    /**
     * Removes all fungal threads connected to a specific Tekton cell.
     * This process is called "absorption" and handles cleanup from multiple locations.
     *
     * @param t the Tekton cell whose connected threads should be absorbed
     */
    public void fonalFelszivodas(Tekton t) {
        List<GombaFonal> felszivandoFonalak = t.getKapcsolodoFonalak();

        // 1. Remove threads from connected Tektons
        for (GombaFonal gf : felszivandoFonalak) {
            Tekton t1 = gf.getStartTekton();
            Tekton t2 = gf.getCelTekton();

            if(t1.getId() == t.getId()){
                t2.removeKapcsolodoFonal(gf);   // Automatically checks if thread is protected
            } else {
                t1.removeKapcsolodoFonal(gf);
            }
        }

        // 2. Clear threads from the target Tekton
        t.clearKapcsolodoFonalak();     // Automatically checks if threads are protected

        // 3. Remove threads from this fungus
        for(GombaFonal gf : felszivandoFonalak){
            this.deleteFonal(gf);
        }
    }

    /**
     * Sets the central fungal body of this fungus.
     *
     * @param gt the new fungal body
     */
    public void setGombaTest(GombaTest gt) {
        gombaTest = gt;
    }

    /**
     * Deletes a specific fungal thread from this fungus.
     * Protected threads (connected to Tektons with defense) are not deleted.
     *
     * @param gf the thread to delete
     */
    public void deleteFonal(GombaFonal gf) {
        for (List<GombaFonal> l : fonalLista) {
            if(l.contains(gf)) {
                if(Gombasz.protectedSzures(l).contains(gf)) {
                    break;
                } else {
                    l.remove(gf);   // Protected threads are not deleted
                }
            }
        }
    }

    /**
     * Checks if a Tekton cell is a neighbor's neighbor of the fungal body.
     * Used for validating spore spreading at higher levels.
     *
     * @param celT the target Tekton cell to check
     * @return true if the cell is a neighbor's neighbor, false otherwise
     */
    private boolean szomszedSzomszedjaEllenorzes(Tekton celT){
        // Check if the target is a neighbor's neighbor of the fungal body
        List<Tekton> szomszedSzomszedai = new ArrayList<>();
        for (Tekton tSzomszed : this.tekton.getSzomszedosTektonok()) {  // The fungus's neighboring Tektons
            szomszedSzomszedai.addAll(tSzomszed.getSzomszedosTektonok());   // The neighbors' neighbors
        }

        return szomszedSzomszedai.contains(celT);
    }

    /**
     * Spreads spores from a fungal body to a target Tekton cell.
     * Higher-level fungal bodies can spread to more distant cells and produce more spores.
     * Spreading costs 3 spores from the stock.
     *
     * @param celTekton the target Tekton cell for spore spreading
     * @param gt the fungal body spreading the spores
     * @return true if spreading was successful, false otherwise
     */
    public boolean szor(Tekton celTekton, GombaTest gt) {
        List<Tekton> szomszedLista = celTekton.getSzomszedosTektonok();

        // Target validation - Check neighboring Tektons
        if(!this.tekton.getSzomszedosTektonok().contains(celTekton)){
            if(this.gombaTest.getSzint() == 3){ // Level 3 bodies can spread to neighbors' neighbors
                // Check if target is a neighbor's neighbor
                if(!szomszedSzomszedjaEllenorzes(celTekton)){
                    System.out.println("\tHiba: 3. SZintű GombaTest maximum csak a szomszéd szomszédjaira szórhat!");
                    return false;
                }
            } else {
                // Lower-level bodies can only spread to adjacent Tektons
                System.out.println("\tHiba: 1. és 2. szintű GombaTest csak szomszédos Tektonra szórhat!.");
                return false;
            }
        }

        int szint = gt.getSzint();
        int szorandoMennyiseg = gt.sporaSzorzo(szint);

        // Check if there are enough spores for spreading (costs 3)
        if(3 > this.gombaTest.getSporakeszlet()){
            System.out.println("\tHiba: Nincs elég Spóra a szóráshoz.");
            return false;
        }

        // If there's already a fungus on the target Tekton
        if(celTekton.getGomba() != null){
            // Add spores directly to the existing fungal body
            celTekton.getGomba().addToSporaKeszlet(szorandoMennyiseg);
            System.out.println("\tSzórás: Spórák GombaTesthez adódtak.");
        } else {
            // If no fungus exists, create spores on the Tekton
            for(int i = 0; i < szorandoMennyiseg; ++i){
                BaseSpora s = BaseSpora.generateRandomSpora();  // Each type has 1/6 probability
                celTekton.addSpora(s);
                System.out.println("\tSzórás: Spórák Tektonon létrejöttek.");
            }
        }
        this.gombaTest.addSzorasCount(1);
        this.gombaTest.decreaseSporakeszlet(3);
        return true;
    }

    /**
     * Adds spores directly to the fungal body's stock.
     *
     * @param val the number of spores to add
     */
    public void addToSporaKeszlet(int val){
        this.gombaTest.addToSporaKeszlet(val);
    }

    /**
     * Adds a new fungal thread to this fungus's network.
     * The new thread must meet various conditions:
     * - Connected Tektons must be neighbors
     * - No self-connections (loop edges)
     * - No cycles in thread paths
     * - Thread must maintain continuity with the fungal body
     *
     * @param ujGF the new thread to add
     * @return true if the thread was successfully added, false otherwise
     */
    public boolean addFonal(GombaFonal ujGF) {
        // Check if Tektons are neighbors
        if(!ujGF.getCelTekton().getSzomszedok().contains(ujGF.getStartTekton()) ||
                !ujGF.getStartTekton().getSzomszedok().contains(ujGF.getCelTekton())) {
            System.out.println("Nem szomszédos Tektonok nem összeköthetőek!");
            return false;
        }

        // Check for self-connections
        if(ujGF.getStartTekton().getId() == ujGF.getCelTekton().getId()) {
            System.out.println("Hurokél nem lerakható!");
            return false;
        }

        // Check for cycles
        for(List<GombaFonal> l : fonalLista) {
            List<Tekton> lista = new ArrayList<>();
            for(GombaFonal gf_i : l) {
                lista.add(gf_i.getStartTekton());
            }

            for(Tekton tekton : lista) {
                if(ujGF.getCelTekton().getId() == tekton.getId()) {
                    if(ujGF.getStartTekton().getId() != this.tekton.getId() || ujGF.getCelTekton().getId() != this.tekton.getId()) {
                        System.out.println("Egymás utáni fonalak nem alkothatnak kört!");
                        return false;
                    }
                }
            }
        }

        // End of validations --------------

        // Initial thread case
        if(fonalLista.isEmpty()) {
            ArrayList<GombaFonal> lista = new ArrayList<>();
            lista.add(ujGF);
            fonalLista.add(lista);
            return true;
        }

        // Kezdő gombából történő kiindulás
        if(ujGF.getStartTekton().getId() == this.tekton.getId()) {
            for (List<GombaFonal> l : fonalLista) {
                if (!l.isEmpty()) {
                    GombaFonal gf = l.get(0);
                    if(gf.getStartTekton().getId() == ujGF.getStartTekton().getId() && gf.getCelTekton().getId() == ujGF.getCelTekton().getId()) {
                        return false;
                    }
                }
            }

            List<GombaFonal> ujLista = new ArrayList<>();
            ujLista.add(ujGF);
            fonalLista.add(ujLista);
            return true;
        }

        // Add to ends of existing paths
        for(List<GombaFonal> l : fonalLista) {
            if(!l.isEmpty() && fonalFolytonossagVizsgalat(l)) {
                if(l.get(l.size()-1).getCelTekton().getId() == ujGF.getID()){
                    l.add(ujGF);
                    return true;
                }
            }
        }

        // Create new path extending from existing ones
        for(List<GombaFonal> l : fonalLista) {
            for (GombaFonal gf_i : l) {
                if(gf_i.getCelTekton().getId() == ujGF.getStartTekton().getId()) {
                    int elozoIdx = l.indexOf(gf_i);
                    List<GombaFonal> ujLista = new ArrayList<>();
                    for(int i = 0; i <= elozoIdx; i++){
                        ujLista.add(l.get(i));
                    }

                    ujLista.add(ujGF);

                    if(!fonalFolytonossagVizsgalat(ujLista)){
                        return false;
                    } else {
                        fonalLista.add(ujLista);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Decreases the fungal thread stock by one if available.
     *
     * @return true if the stock was successfully decreased, false if insufficient stock
     */
    public boolean decreaseFonalkeszlet() {
        if(fonalKeszlet > 0) {
            fonalKeszlet--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increases the fungal thread stock by a specific amount.
     *
     * @param val the number of threads to add to the stock
     */
    public void increaseFonalkeszlet(int val) {
        fonalKeszlet += val;
    }

    /**
     * Returns the Tekton cell where this fungus's central body is located.
     *
     * @return the location Tekton
     */
    public Tekton getTekton() {
        return tekton;
    }

    /**
     * Sets the Tekton cell where this fungus's central body is located.
     *
     * @param t the new location Tekton
     */
    public void setTekton(Tekton t) {
        tekton = t;
    }

    /**
     * Removes all discontinuous fungal threads from this fungus's network.
     * Protected threads are not removed.
     */
    public void nemFolytonosFonalTorles() {
        List<GombaFonal> nemFolytonos = fonalFolytonossagVizsgalat();
        System.out.println("Nem folytonosak: " + nemFolytonos.size());
        for (GombaFonal gf : nemFolytonos) {
            for (List<GombaFonal> l : fonalLista) {
                if(l.contains(gf)) {
                    if(gf.getStartTekton().isDefendFonalak() || gf.getCelTekton().isDefendFonalak()) {
                        break;
                    }
                    gf.getStartTekton().getKapcsolodoFonalak().remove(gf);
                    gf.getCelTekton().getKapcsolodoFonalak().remove(gf);
                    l.remove(gf);
                }
            }
        }
    }

    /**
     * Destroys this fungus, removing all threads, clearing its location,
     * and removing it from player control.
     * Protected threads are not removed.
     */
    public void elpusztul() {
        for(List<GombaFonal> l : fonalLista) {
            l.clear();
        }
        fonalLista.clear();
        tekton.setGomba(null);
        gombasz.getGombaLista().remove(this);
        System.out.println("Gomba Id" + this.getId() + "Elpusztult!");
    }

    /**
     * Returns the player that controls this fungus.
     *
     * @return the controlling Gombasz player
     */
    public Gombasz getGombasz(){
        return gombasz;
    }

    /**
     * Returns the unique ID of this fungus.
     *
     * @return the unique ID
     */
    public int getId(){
        return id;
    }
}