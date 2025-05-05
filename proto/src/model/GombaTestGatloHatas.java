package model;

/**
 * Represents a special tekton effect that prevents the creation of fungal bodies.
 * When activated, this effect marks the tekton as having a fungal body and enables
 * fungal body restriction, preventing additional fungi from being established there.
 * Extends the base TektonHatas class.
 */
public class GombaTestGatloHatas extends TektonHatas {

    /**
     * Constructs a new GombaTestGatloHatas with default properties.
     * Inherits from the TektonHatas class.
     */
    public GombaTestGatloHatas() {
        super();
    }

    /**
     * Implements the effect of this tekton action.
     * Marks the tekton as having a fungal body and enables fungal body restriction,
     * which prevents new fungi from being established on this tekton.
     *
     * @return a string identifier "GombaTestGatlo" representing this effect
     */
    @Override
    public String hatas() {
        super.tekton.setVanGombaTest(true);
        super.tekton.setGtGatlo(true);
        return "GombaTestGatlo";
    }
}