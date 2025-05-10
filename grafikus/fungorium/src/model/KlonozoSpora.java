package model;

/**
 * Represents a special type of spore that can cause an insect to clone itself.
 * When consumed by an insect, this spore triggers the cloning process of the insect,
 * potentially creating a duplicate insect in the game environment.
 * Extends the BaseSpora class.
 */
public class KlonozoSpora extends BaseSpora {

    /**
     * Constructs a new KlonozoSpora with default properties.
     * Inherits ID generation from the BaseSpora class.
     */
    public KlonozoSpora() {
        super();
    }

    /**
     * Implements the effect this spore has when consumed by an insect.
     * Triggers the insect's cloning mechanism, which may create a duplicate insect.
     * The success of cloning depends on the implementation in the Rovar class.
     *
     * @param r the insect that consumes this spore
     */
    @Override
    public void hatas(Rovar r) {
        //Ha null-t ad vissza akkor sikertelen, ha r rovart akkor sikeres
        r.klonozas();
    }
}