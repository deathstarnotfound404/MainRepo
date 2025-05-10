package model;
import java.util.*;

/**
 * Abstract base class representing a spore in the game.
 * Spores have different effects when consumed by insects (Rovar).
 * Implements the IDestroyable interface for lifecycle management.
 */
public abstract class BaseSpora implements IDestroyable {
    /** The nutritional value of the spore */
    protected int tapanyag = 1;

    /** Unique identifier for the spore */
    protected int id;

    /** Random number generator for spore creation */
    private static final Random rnd = new Random();

    /**
     * Constructs a new spore with a unique ID.
     */
    protected BaseSpora() {
        // konstruktor
        id = Field.genID();
    }

    /**
     * Defines the effect this spore has when consumed by an insect.
     * To be implemented by specific spore types.
     *
     * @param r the insect that consumes the spore
     */
    public abstract void hatas(Rovar r);

    /**
     * Creates a random spore of one of the six possible types.
     *
     * @return a randomly generated spore
     */
    public static BaseSpora generateRandomSpora(){
        int valasztas = rnd.nextInt(6);     //6 féle spóra létezik

        switch (valasztas){
            case 0:
                return new Spora();

            case 1:
                return new KlonozoSpora();

            case 2:
                return new GyorsitoSpora();

            case 3:
                return new BenitoSpora();

            case 4:
                return new LassitoSpora();

            case 5:
                return new VagasGatloSpora();

            default:
                return new Spora();
        }
    }

    /**
     * Gets the nutritional value of the spore.
     *
     * @return the nutritional value
     */
    public int getTapanyag() {
        return tapanyag;
    }

    /**
     * Sets the nutritional value of the spore.
     *
     * @param val the new nutritional value
     */
    public void setTapanyag(int val) { this.tapanyag = val; }

    /**
     * Gets the unique identifier of the spore.
     *
     * @return the spore's ID
     */
    public int getId() { return id; }

    /**
     * Handles the destruction of the spore.
     * Implementation of the IDestroyable interface.
     */
    public void elpusztul() { }
}