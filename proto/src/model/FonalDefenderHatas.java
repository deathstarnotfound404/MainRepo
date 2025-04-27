package model;

/**
 * Represents a special tekton effect that protects connected fungal threads from being cut.
 * When activated, this effect enables thread defense for the tekton it's applied to.
 * Extends the base TektonHatas class.
 */
public class FonalDefenderHatas extends TektonHatas {

    /**
     * Constructs a new FonalDefenderHatas with default properties.
     * Inherits from the TektonHatas class.
     */
    public FonalDefenderHatas() {
        super();
    }

    /**
     * Implements the effect of this tekton action.
     * Activates protection for all threads connected to this tekton,
     * preventing them from being cut by insects.
     *
     * @return a string identifier "DefenderHatas" representing this effect
     */
    @Override
    public String hatas() {
        super.tekton.setDefendFonalak(true);
        return "DefenderHatas";
    }
}