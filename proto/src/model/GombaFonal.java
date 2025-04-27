package model;

/**
 * Represents a fungal thread in the game world.
 * GombaFonal (fungal thread) connects two Tekton cells and acts as a pathway for
 * insects to move between cells. Threads are produced by fungi and can be cut by
 * insects. Threads must maintain continuity with their parent fungus to remain viable.
 * Implements the IDestroyable interface to handle its destruction.
 */
public class GombaFonal implements IDestroyable {
    /** Unique identifier for this fungal thread */
    private int id;

    /** The origin Tekton of this thread */
    private Tekton startTekton;

    /** The destination Tekton of this thread */
    private Tekton celTekton;

    /** Flag indicating if this thread can be destroyed */
    private boolean isDestroyable = true;

    /** Flag indicating if this thread has been eaten by an insect */
    private boolean isElragott = false;

    /** The parent fungus of this thread */
    private Gomba alapGomba;

    /**
     * Constructs a new fungal thread connecting two Tekton cells.
     *
     * @param alapgomba the parent fungus that creates this thread
     * @param startTekton the origin Tekton cell
     * @param celTekton the destination Tekton cell
     */
    public GombaFonal(Gomba alapgomba, Tekton startTekton, Tekton celTekton) {
        id = Field.genID();
        this.alapGomba = alapgomba;
        this.startTekton = startTekton;
        this.celTekton = celTekton;
    }

    /**
     * Returns the unique ID of this thread.
     *
     * @return the unique ID
     */
    public int getID(){
        return id;
    }

    /**
     * Returns the origin Tekton of this thread.
     *
     * @return the origin Tekton
     */
    public Tekton getStartTekton() {
        return startTekton;
    }

    /**
     * Returns the destination Tekton of this thread.
     *
     * @return the destination Tekton
     */
    public Tekton getCelTekton() {
        return celTekton;
    }

    /**
     * Returns the parent fungus of this thread.
     *
     * @return the parent fungus
     */
    public Gomba getAlapGomba() {
        return alapGomba;
    }

    /**
     * Checks if this thread is connected to its parent fungus.
     * A thread must maintain continuity with its parent to remain viable.
     *
     * @return true if connected to the parent fungus, false otherwise
     */
    public boolean connectedToAlapGomba() {
        //TODO - ellenőrizni a fonalfolytonossagVizsgalat(gf) függvényt
        return alapGomba.fonalFolytonossagVizsgalat(this);
    }

    /**
     * Sets whether this thread can be destroyed.
     *
     * @param value true if the thread can be destroyed, false otherwise
     */
    public void setDestroyable(boolean value) {
        this.isDestroyable = value;
    }

    /**
     * Checks if this thread can be destroyed.
     *
     * @return true if the thread can be destroyed, false otherwise
     */
    public boolean IsDestroyable() {
        return this.isDestroyable;
    }

    /**
     * Sets whether this thread has been eaten by an insect.
     *
     * @param value true if the thread has been eaten, false otherwise
     */
    public void setElragott(boolean value) {
        this.isElragott = value;
    }

    /**
     * Checks if this thread has been eaten by an insect.
     *
     * @return true if the thread has been eaten, false otherwise
     */
    public boolean IsElragott() {
        return this.isElragott;
    }

    /**
     * Handles the interaction when an insect tries to eat this thread.
     * An insect can only eat a thread if its eating efficiency is 0 (paralyzed).
     * This will trigger a fungal body growth at the insect's location.
     *
     * @param r the insect attempting to eat this thread
     * @return true if the insect successfully eats the thread, false otherwise
     */
    public boolean rovarEves(Rovar r) {
        if(r.getEvesHatekonysag()!=0) {
            return false;
        } else {
            Tekton rovarHelyzet = r.getHelyzet();
            return this.getAlapGomba().getGombasz().gombatestNovesztes(rovarHelyzet, true);
        }
    }

    /**
     * Destroys this fungal thread.
     * Currently a placeholder method to be implemented.
     */
    public void elpusztul() {}
}