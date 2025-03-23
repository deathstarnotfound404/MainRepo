package FungoriumClasses;
import CallTracer.*;

/**
 * A VagasGatloSpora osztály egy speciális spórát reprezentál, amely
 * megakadályozza a rovarok vágási képességét.
 * A BaseSpora osztályból származik, és felülírja a hatás metódust.
 * @author Pünkösti Györk
 * @version 1.0
 * @since 2025-03-19
 */
public class VagasGatloSpora extends BaseSpora {
    /**
     * Létrehoz egy új VagasGatloSpora objektumot.
     */
    public VagasGatloSpora() {
        super();
    }

    /**
     * A spóra hatása a rovarra: letiltja a vágási képességét.
     *
     * @param r A rovar, amelyre a hatás kifejtődik.
     */
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setTudVagni", "Rovar", "false");
        r.setTudVagni(false);
        CallTracer.exit("setTudVagni", "");
    }
}
