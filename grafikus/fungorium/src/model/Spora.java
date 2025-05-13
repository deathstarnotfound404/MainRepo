package model;

/**
 * Represents a standard spore in the fungal ecosystem.
 * When consumed by an insect, this regular spore has a moderate effect,
 * setting the insect's eating efficiency to 50% and disabling its maximum
 * consumption capability. This is a basic implementation of the BaseSpora class.
 */
public class Spora extends BaseSpora {

    /**
     * Constructs a new standard Spora with default properties.
     * Inherits ID generation from the BaseSpora class.
     */
    public Spora() {
        super();
    }

    /**
     * Implements the effect this spore has when consumed by an insect.
     * Sets the insect's eating efficiency to 50% (0.5) and disables
     * its maximum consumption capability.
     *
     * @param r the insect that consumes this spore
     */
    @Override
    public void hatas(Rovar r) {
        r.setMaxFogyasztas(false);
        r.setEvesHatekonysag(0.5);
    }
}