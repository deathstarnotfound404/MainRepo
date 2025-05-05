package model;

/**
 * Represents a special tekton effect that can restrict fungal thread connections.
 * This effect limits the tekton to having at most one connected thread when activated.
 * Extends the base TektonHatas class.
 */
public class FonalGatloHatas extends TektonHatas {

    /**
     * Constructs a new FonalGatloHatas with default properties.
     * Inherits from the TektonHatas class.
     */
    public FonalGatloHatas() {
        super();
    }

    /**
     * Implements the effect of this tekton action.
     * Restricts thread connections based on the tekton's current degree (number of connections).
     * If the tekton has no connections, the restriction is disabled.
     * If the tekton has one or more connections, it limits to a maximum of one thread connection.
     *
     * @return "FonalGatlo" if the restriction is activated, or "NincsFonalGatlo" if disabled
     */
    @Override
    public String hatas() {
        int fokszam = super.tekton.getFokszam();
        if(fokszam < 1) {
            super.tekton.setMaxEgyFonal(false);
            return "NincsFonalGatlo";
        } else {
            super.tekton.setMaxEgyFonal(true);
            return "FonalGatlo";
        }
    }
}