package model;
import java.util.*;

/**
 * Represents the base class for special effects that can be associated with a Tekton.
 * A Tekton (cell/field) in the game can have different effects that influence game mechanics.
 * This class provides the foundation for various effect types and includes functionality
 * for generating random effects.
 */
public class TektonHatas {
    /** The Tekton associated with this effect */
    protected Tekton tekton;

    /** Random number generator for effect creation */
    private static final Random rnd = new Random();

    /** Flag indicating if the effect depends on specific events to trigger */
    protected boolean hatasEsemenyfuggo = false;

    /**
     * Constructs a default TektonHatas with no event dependency.
     */
    public TektonHatas() {
        hatasEsemenyfuggo = false;
    }

    /**
     * Applies the effect and returns a descriptor of the applied effect.
     * Base implementation returns a simple identifier.
     *
     * @return String describing the applied effect
     */
    public String hatas() {
        return "Base";
    }

    /**
     * Associates this effect with a specific Tekton.
     *
     * @param t the Tekton to associate with this effect
     */
    public void setTekton(Tekton t) {
        this.tekton = t;
    }

    /**
     * Returns the Tekton associated with this effect.
     *
     * @return the associated Tekton
     */
    public Tekton getTekton() {
        return tekton;
    }

    /**
     * Generates a random TektonHatas effect.
     * Possible effects include:
     * - Default effect (no special behavior)
     * - FonalDefenderHatas (protects threads)
     * - FonalFelszivodoHatas (absorbs threads)
     * - FonalGatloHatas (inhibits thread creation)
     * - GombaTestGatloHatas (prevents fungal growth)
     *
     * @return a randomly selected TektonHatas subclass instance
     */
    public static TektonHatas generateRandomTektonHatas(){
        //int valasztas = rnd.nextInt(5);     //5 féle hatas létezik
        int valasztas = Math.abs(rnd.nextInt()) % 5;
        
        switch (valasztas){
            case 0:
                return new TektonHatas();

            case 1:
                return new FonalDefenderHatas();

            case 2:
                return new FonalFelszivodoHatas();

            case 3:
                return new FonalGatloHatas();

            case 4:
                return new GombaTestGatloHatas();

            default:
                return new TektonHatas();
        }
    }

    /**
     * Indicates whether this effect is triggered by specific events.
     *
     * @return true if the effect depends on events, false otherwise
     */
    public boolean isHatasEsemenyfuggo(){
        return hatasEsemenyfuggo;
    }
}