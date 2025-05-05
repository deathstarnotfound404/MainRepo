package model;

/**
 * Represents a special type of spore that impairs an insect's cutting ability.
 * When consumed by an insect, this spore disables the insect's ability to cut
 * or destroy fungal threads in the environment, preventing it from damaging
 * the fungal network.
 * Extends the BaseSpora class.
 */
public class VagasGatloSpora extends BaseSpora {

    /**
     * Constructs a new VagasGatloSpora with default properties.
     * Inherits ID generation from the BaseSpora class.
     */
    public VagasGatloSpora() {
        super();
    }

    /**
     * Implements the effect this spore has when consumed by an insect.
     * Disables the insect's ability to cut fungal threads.
     *
     * @param r the insect that consumes this spore
     */
    @Override
    public void hatas(Rovar r) {
        r.setTudVagni(false);
    }
}