package FungoriumClasses;
import CallTracer.*;

/**
 * A {@code GyorsitoSpora} osztály egy speciális spóra, amely hatással van a rovarokra.
 * Hatására a rovarnak maximális fogyasztása lesz.
 *
 * @author Pünkösti Györk
 * @version 1.0
 * @since 2025-03-19
 */
public class GyorsitoSpora extends BaseSpora {
    /**
     * Létrehoz egy új {@code GyorsitoSpora} objektumot.
     */
    public GyorsitoSpora() {
        super();
    }

    /**
     * A spóra hatásának alkalmazása egy adott rovarra.
     *
     * @param r Az érintett {@code Rovar} objektum.
     */
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setMaxFogyasztas", "Rovar", "true");
        r.setMaxFogyasztas(true);
        CallTracer.exit("setMaxFogyasztas", "");
    }
}
