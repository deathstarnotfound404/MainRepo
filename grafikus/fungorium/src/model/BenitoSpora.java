package model;

/**
 * Represents a special type of spore that negatively impacts an insect's ability to consume.
 * When consumed by an insect, this spore disables the insect's ability to eat at maximum capacity
 * and sets its eating efficiency to zero.
 */
public class BenitoSpora extends BaseSpora {

    /**
     * Constructs a new BenitoSpora with default properties.
     * Inherits ID generation from the BaseSpora class.
     */
    public BenitoSpora() {
        super();
    }

    /**
     * Implements the effect this spore has when consumed by an insect.
     * Disables the insect's maximum consumption capability and sets
     * its eating efficiency to zero.
     *
     * @param r the insect that consumes this spore
     */
    @Override
    public void hatas(Rovar r) {
        r.setMaxFogyasztas(false);
        r.setEvesHatekonysag(0);
    }
}