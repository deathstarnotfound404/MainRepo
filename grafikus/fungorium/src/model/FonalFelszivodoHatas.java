package model;

/**
 * Represents a special tekton effect that can absorb fungal threads.
 * When activated under specific conditions (every 5th visit), this effect
 * triggers the absorption of all threads connected to the tekton.
 * Extends the base TektonHatas class.
 */
public class FonalFelszivodoHatas extends TektonHatas {

    /**
     * Constructs a new FonalFelszivodoHatas with default properties.
     * Sets this effect to be event-dependent, meaning it only activates
     * under certain conditions.
     */
    public FonalFelszivodoHatas() {
        super();
        hatasEsemenyfuggo = true;
    }

    /**
     * Implements the effect of this tekton action.
     * Absorbs all threads connected to this tekton when the visit count
     * is a multiple of 5 (excluding zero visits).
     *
     * @return a string identifier "Felszivas" when absorption happens,
     *         or "NincsFonalFelszivas" when conditions are not met
     */
    @Override
    public String hatas() {
        //Fonalak felszívása az adott tektonról
        if(super.tekton.getLatogatottsag()%5 == 0 && super.tekton.getLatogatottsag() != 0) {
            super.tekton.fonalakFelszivasa();
            return "Felszivas";
        } else {
            return "NincsFonalFelszivas";
        }
    }
}