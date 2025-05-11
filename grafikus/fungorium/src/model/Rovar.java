package model;

import java.util.List;

/**
 * Represents an insect in the game world.
 * Insects can move between Tekton cells, consume spores, cut fungal threads,
 * and clone themselves. They are controlled by Rovarasz (Entomologist) players
 * and collect nutrients as a core gameplay mechanic.
 * Implements the IDestroyable interface to handle its destruction.
 */
public class Rovar implements IDestroyable {
    /** Current location of the insect */
    private Tekton helyzet;

    /** Amount of nutrients collected by this insect */
    private int tapanyag = 0;

    /** Efficiency of consuming spores (0.0 to 1.0) */
    private double evesHatekonysag = 0.5;

    /** Flag indicating if the insect can cut fungal threads */
    private boolean tudVagni = true;

    /** Flag indicating if the insect can consume all available spores */
    private boolean maxFogyasztas = false;

    private boolean aktivHatas = false;

    /** The player that controls this insect */
    private Rovarasz rovarasz;

    /** Unique identifier for this insect */
    private int id;

    /**
     * Constructs a new insect with a unique ID.
     */
    public Rovar() {
        id = Field.genID();
    }

    public Rovar deepCopy(){
        Rovar rovar = new Rovar();
        rovar.id = this.id;
        rovar.setHelyzet(helyzet);
        rovar.setTapanyag(tapanyag);
        rovar.evesHatekonysag = evesHatekonysag;
        rovar.tudVagni = tudVagni;
        rovar.rovarasz = rovarasz;
        return rovar;
    }

    /**
     * Adds nutrients to the insect's collection.
     *
     * @param val amount of nutrients to add
     */
    public void addTapanyag(int val) {
        tapanyag += val;
    }

    /**
     * Returns the eating efficiency of this insect.
     *
     * @return the eating efficiency (0.0 to 1.0)
     */
    public double getEvesHatekonysag() {
        return evesHatekonysag;
    }

    /**
     * Returns the current location of this insect.
     *
     * @return the Tekton where this insect is located
     */
    public Tekton getHelyzet() {
        return helyzet;
    }

    /**
     * Returns the unique ID of this insect.
     *
     * @return the ID
     */
    public int getID() {
        return id;
    }

    /**
     * Returns the amount of nutrients collected by this insect.
     *
     * @return the amount of collected nutrients
     */
    public int getTapanyag() {
        return tapanyag;
    }

    /**
     * Checks if this insect can cut fungal threads.
     *
     * @return true if the insect can cut threads, false otherwise
     */
    public boolean getTudVagni() {
        return tudVagni;
    }

    /**
     * Resets all abilities of this insect to their default values.
     * Sets eating efficiency to 100%, enables thread cutting,
     * and disables maximum consumption.
     */
    public void kepessegekAlaphelyzetbe() {
        evesHatekonysag = 0.5;
        tudVagni = true;
        maxFogyasztas = false;
        aktivHatas = false;
        System.out.println("\nRovar: r" + id + " -> Kepessegek Alaphelyzetbe állítása");
    }

    /**
     * Finds an empty neighboring Tekton cell.
     *
     * @return an empty neighboring Tekton, or null if none is available
     */
    private Tekton getUresSzomszedosTekton() {
        List<Tekton> szabadHelyek = helyzet.getSzomszedosTektonok();
        Tekton szabadTekton = null;
        for (Tekton t : szabadHelyek) {
            if(!t.vanRovarATektonon()) {
                szabadTekton = t;
                break;
            }
        }
        return szabadTekton;
    }

    /**
     * Creates a clone of this insect in an adjacent empty Tekton cell.
     *
     * @return the cloned insect, or null if no suitable location was found
     */
    public Rovar klonozas() {
        Tekton szabadTekton = getUresSzomszedosTekton();
        if(szabadTekton != null) {
            Rovar rovarKlon = new Rovar();
            rovarKlon.helyzet = szabadTekton;
            rovarKlon.rovarasz = this.rovarasz;
            this.rovarasz.addRovar(rovarKlon, szabadTekton);
            return rovarKlon;
        } else {
            return null;
        }
    }

    /**
     * Moves the insect to a target Tekton cell.
     * Movement is only possible if the target is a neighbor, connected by fungal threads,
     * and not already occupied by another insect.
     *
     * @param celTekton the destination Tekton
     * @return true if the movement was successful, false otherwise
     */
    public boolean lep(Tekton celTekton) {
        List<Tekton> szomszedLista = this.helyzet.getSzomszedosTektonok();
        // Can't move to the same location
        if(celTekton.getId() == this.id) {
            System.out.println("Hiba: Nem léphet ugyan oda, ahol van!");
            return false;
        }

        // If the two Tektons are not neighbors
        if(!szomszedLista.contains(celTekton) && !celTekton.getSzomszedok().contains(this.helyzet)) {
            System.out.println("Hiba: A kiválasztott két tekton nem szomszédos!");
            return false;
        }

        // Check if the Tektons are connected by fungal threads
        if(!Tekton.ketTektonFonallalOsszekotott(this.helyzet, celTekton)) {
            System.out.println("Hiba: A kiválasztott két tekton nincs fonalakkal összekötve!");
            return false;
        }

        if(celTekton.vanRovarATektonon() || celTekton.getRovar() != null) {
            System.out.println("Hiba: Már van Rovar a tekton!");
            return false;
        }

        this.helyzet.setRovar(null);    // Visit count is incremented here
        this.setHelyzet(celTekton);
        celTekton.setRovar(this);
        System.out.println("[moveRovar] Lépés sikeres!");
        return true;
    }

    /**
     * Checks if this insect can consume all available spores.
     *
     * @return true if maximum consumption is enabled, false otherwise
     */
    public boolean isMaxFogyasztas() {
        return maxFogyasztas;
    }

    /**
     * Returns the player that controls this insect.
     *
     * @return the controlling Rovarasz player
     */
    public Rovarasz getRovarasz() {
        return rovarasz;
    }

    /**
     * Sets the current location of this insect.
     *
     * @param t the new location for this insect
     */
    public void setHelyzet(Tekton t) {
        helyzet = t;
    }

    /**
     * Sets the eating efficiency of this insect.
     *
     * @param val the new eating efficiency (0.0 to 1.0)
     */
    public void setEvesHatekonysag(double val) {
        evesHatekonysag = val;
    }

    /**
     * Sets whether this insect can consume all available spores.
     *
     * @param val true to enable maximum consumption, false to disable it
     */
    public void setMaxFogyasztas(boolean val) {
        maxFogyasztas = val;
    }

    /**
     * Sets the player that controls this insect.
     *
     * @param rsz the Rovarasz player to control this insect
     */
    public void setRovarasz(Rovarasz rsz) {
        rovarasz = rsz;
    }

    /**
     * Sets the amount of nutrients collected by this insect.
     *
     * @param val the new amount of nutrients
     */
    public void setTapanyag(int val) {
        tapanyag = val;
    }

    /**
     * Sets whether this insect can cut fungal threads.
     *
     * @param val true to enable thread cutting, false to disable it
     */
    public void setTudVagni(boolean val) {
        tudVagni = val;
    }

    /**
     * Calculates how many spores this insect can consume based on its eating efficiency.
     *
     * @return the number of spores that can be consumed
     */
    private int elfogyaszthatoMennyisieg() {
        if(maxFogyasztas || evesHatekonysag == 1) {
            return this.helyzet.getSporaLista().size();
        }

        if(evesHatekonysag == 0) {
            return 0;
        }

        if(evesHatekonysag == 0.5) {
            int size = this.helyzet.getSporaLista().size();
            if(size == 1) {
                return 1;
            } else {
                return size/2;
            }
        }

        if(evesHatekonysag == 0.25) {
            int size = this.helyzet.getSporaLista().size();
            if(size == 1) {
                return 1;
            } else {
                return size/4;
            }
        }

        return 0;
    }

    /**
     * Consumes spores from the current location.
     * The number of consumed spores depends on the eating efficiency.
     * The last consumed spore applies its effect to this insect.
     *
     * @return true if spores were consumed, false otherwise
     */
    public boolean sporaEves() {
        List<BaseSpora> sporaLista = this.helyzet.getSporaLista();

        int elfogyaszthatoVal = elfogyaszthatoMennyisieg();

        if(sporaLista != null && sporaLista.size() > 0 && elfogyaszthatoVal > 0) {
            this.addTapanyag(elfogyaszthatoVal);    // Add nutrients
            BaseSpora last = sporaLista.get(elfogyaszthatoVal-1);   // The last eaten spore's effect applies
            if(!aktivHatas) {
                aktivHatas = true;
                last.hatas(this);   // Apply spore effect to this insect
            }
            this.helyzet.getSporaLista().subList(0, elfogyaszthatoVal).clear(); // Remove consumed spores

            return true;
        } else {
            return false;
        }
    }

    /**
     * Cuts a fungal thread if the insect has cutting ability.
     * This removes the thread from both connected Tektons and its parent fungus.
     *
     * @param gf the fungal thread to cut
     * @return true if the thread was cut, false otherwise
     */
    public boolean vag(GombaFonal gf) {
        if(this.tudVagni && gf!=null) {
            if(gf.getStartTekton().isDefendFonalak() || gf.getCelTekton().isDefendFonalak()) {
                System.out.println("[DefendTektonHatas]: Vágás sikertelen!");
                return false;
            }
            Tekton t1 = gf.getStartTekton();
            Tekton t2 = gf.getCelTekton();
            Gomba alapGomba = gf.getAlapGomba();

            t1.removeKapcsolodoFonal(gf);
            t2.removeKapcsolodoFonal(gf);

            alapGomba.deleteFonal(gf);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Destroys this insect.
     * Removes it from its current location and from player control.
     */
    @Override
    public void elpusztul() {
        this.helyzet.setRovar(null);
        this.rovarasz.getRovarLista().remove(this);
    }

    /**
     * Returns the unique identifier of this insect.
     *
     * @return the unique ID
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rovar #" + this.id;
    }
}