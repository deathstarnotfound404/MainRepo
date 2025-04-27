package model;

/**
 * Represents a special type of spore that negatively impacts an insect's ability to consume.
 * When consumed by an insect, this spore reduces the insect's eating capabilities by
 * setting its eating efficiency to a low value (0.25) and disabling maximum consumption capacity.
 * This has the opposite effect of GyorsitoSpora.
 */
public class LassitoSpora extends BaseSpora {

    /**
     * Constructs a new LassitoSpora with default properties.
     * Inherits ID generation from the BaseSpora class.
     */
    public LassitoSpora() {
        super();
    }

    /**
     * Implements the effect this spore has when consumed by an insect.
     * Reduces the insect's eating efficiency to 25% and disables
     * its maximum consumption capability.
     *
     * @param r the insect that consumes this spore
     */
    @Override
    public void hatas(Rovar r) {
        r.setEvesHatekonysag(0.25);
        r.setMaxFogyasztas(false);
    }
}