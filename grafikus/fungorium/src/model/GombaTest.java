package model;

/**
 * Represents a fungal body in the game world.
 * The GombaTest (fungal body) is the central part of a fungus that produces and stores spores,
 * manages levels, and handles growth mechanics. It's associated with a parent Gomba (fungus)
 * and maintains resources needed for fungal actions.
 * Implements the IDestroyable interface to handle its destruction.
 */
public class GombaTest implements IDestroyable {
    /** Unique identifier for this fungal body */
    private int id;

    /** Current stock of spores available to the fungus */
    private int sporaKeszlet = 0;

    /** Counter tracking how many times spores have been dispersed */
    private int szorasCount = 0;

    /** Current level of the fungal body (1-3) */
    private int szint = 1;

    /** The parent fungus of this fungal body */
    private Gomba alapGomba;

    /**
     * Constructs a new fungal body with a parent fungus and initial spore count.
     *
     * @param g the parent fungus
     * @param kezdoSporaSzam initial number of spores
     */
    public GombaTest(Gomba g, int kezdoSporaSzam) {
        id = Field.genID();
        alapGomba = g;
        sporaKeszlet = kezdoSporaSzam;
    }

    /**
     * Returns the current spore stock.
     *
     * @return the number of spores in stock
     */
    public int getSporakeszlet() {
        return sporaKeszlet;
    }

    /**
     * Returns the count of spore dispersal actions.
     *
     * @return the number of times spores have been dispersed
     */
    public int getSzorasCount() {
        return szorasCount;
    }

    /**
     * Increases the spore dispersal counter.
     *
     * @param val the amount to increment the counter by
     */
    public void addSzorasCount(int val) {
        this.szorasCount += val;
    }

    /**
     * Adds spores to the stock based on the current level of the fungal body.
     * Higher levels produce more spores.
     */
    public void addToSporaKeszletTermelessel() {
        switch (szint) {
            case 1:
                sporaKeszlet += 2;
                break;

            case 2:
                sporaKeszlet += 3;
                break;

            case 3:
                sporaKeszlet += 4;
                break;

            default:
                break;
        }
    }

    /**
     * Adds a specific number of spores to the stock.
     *
     * @param val the number of spores to add
     */
    public void addToSporaKeszlet(int val) {
        sporaKeszlet += val;
    }

    /**
     * Calculates how many spores can be dispersed based on the level.
     * Higher levels allow more spores to be dispersed at once.
     *
     * @param szint the level to calculate for
     * @return the number of spores that can be dispersed
     */
    public int sporaSzorzo(int szint) {
        int szorandoMennyiseg = 0;
        switch (szint){
            case 1:
                szorandoMennyiseg = 1;
                break;

            case 2:
                szorandoMennyiseg = 2;
                break;

            case 3:
                szorandoMennyiseg = 3;
                break;
        }

        return szorandoMennyiseg;
    }

    /**
     * Returns the current level of the fungal body.
     *
     * @return the level (1-3)
     */
    public int getSzint() {
        return szint;
    }

    /**
     * Sets the level of the fungal body within valid bounds (1-3).
     *
     * @param val the new level to set
     */
    public void setSzint(int val) {
        if(val <= 3 && val >= 1) {
            szint = val;
        }
    }

    /**
     * Handles level progression based on spore dispersal count.
     * The fungal body advances a level at specific thresholds (3 and 6),
     * and causes the parent fungus to die after 9 dispersals.
     */
    public void szintlepes() {
        if(szorasCount == 9) {
            alapGomba.elpusztul();
        } else {
            if(this.szorasCount == 3 || this.szorasCount == 6) {
                szint++;
            }
        }
    }

    /**
     * Returns the parent fungus of this fungal body.
     *
     * @return the parent fungus
     */
    public Gomba getAlapGomba() {
        return alapGomba;
    }

    /**
     * Sets the parent fungus of this fungal body.
     *
     * @param g the parent fungus to set
     */
    public void setAlapGomba(Gomba g) {
        alapGomba = g;
    }

    /**
     * Increases the spore stock by one.
     */
    public void increaseSporakeszlet() {
        sporaKeszlet++;
    }

    /**
     * Decreases the spore stock by a specific amount if sufficient spores are available.
     *
     * @param val the number of spores to remove
     * @return true if the stock was successfully decreased, false if insufficient spores
     */
    public boolean decreaseSporakeszlet(int val) {
        if(sporaKeszlet >= val) {
            sporaKeszlet -= val;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the unique ID of this fungal body.
     *
     * @return the unique ID
     */
    public int getId() {
        return id;
    }

    /**
     * Destroys this fungal body.
     * Currently a placeholder method to be implemented.
     */
    @Override
    public void elpusztul() { }

    @Override
    public String toString() {
        return "GombaTest #" + this.id;
    }
}