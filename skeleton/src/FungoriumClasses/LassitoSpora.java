package FungoriumClasses;
import CallTracer.*;

/**
 * A {@code LassitoSpora} osztály a lassító hatású spórát reprezentálja, amely csökkenti a rovarok fogyasztási képességét.
 *
 * @author Pünkösti Györk
 * @version 1.0
 * @since 2025-03-19
 */
public class LassitoSpora extends BaseSpora {
    /**
     * Létrehoz egy új {@code LassitoSpora} objektumot.
     */
    public LassitoSpora() {
        super();
    }

    /**
     * A spóra hatásának alkalmazása egy rovarra.
     * A spóra csökkenti a rovar maximális fogyasztási képességét és evési hatékonyságát.
     *
     * @param r A rovar, amelyre a spóra hatást gyakorol.
     */
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setMaxFogyasztas", "Rovar", "false");
        r.setMaxFogyasztas(false);
        CallTracer.exit("setMaxFogyasztas", "");
        CallTracer.enter("setEvesHatekonysag", "Rovar", "0.25");
        r.setEvesHatekonysag(0.25);
        CallTracer.exit("setEvesHatekonysag", "");
    }
}
