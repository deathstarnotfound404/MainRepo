package model;

/**
 * Represents a special type of spore that positively impacts an insect's ability to consume.
 * When consumed by an insect, this spore enhances the insect's eating capabilities by
 * enabling maximum consumption capacity and setting its eating efficiency to maximum (1).
 * This has the opposite effect of BenitoSpora.
 */
public class GyorsitoSpora extends BaseSpora {

    /**
     * Constructs a new GyorsitoSpora with default properties.
     * Inherits ID generation from the BaseSpora class.
     */
    public GyorsitoSpora() {
        super();
    }

    /**
     * Implements the effect this spore has when consumed by an insect.
     * Enables the insect's maximum consumption capability and sets
     * its eating efficiency to the highest level (1).
     *
     * @param r the insect that consumes this spore
     */
    @Override
    public void hatas(Rovar r) {
        r.setMaxFogyasztas(true);
        r.setEvesHatekonysag(1);
    }
}