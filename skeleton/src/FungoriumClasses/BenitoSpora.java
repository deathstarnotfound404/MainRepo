package FungoriumClasses;
import CallTracer.*;

/**
 * A {@code BenitoSpora} osztály a bénító spórát reprezentálja és a bénító képességet implementálja.
 *
 * <p>Az osztály a {@link BaseSpora} leszármazottja, és felülírja annak {@code hatas()} metódusát,
 * amely a rovar fogyasztásának képességeit nullára csökkenti.</p>
 *
 * @author NAME
 * @version 1.0
 * @since 2025-03-19
 */
public class BenitoSpora extends BaseSpora {
    /**
     * Létrehoz egy új {@code BenitoSpora} objektumot.
     */
    public BenitoSpora() {
        super();
    }

    /**
     * A spóra hatását fejti ki egy adott rovarra.
     *
     * <p>Amikor egy rovar érintkezik ezzel a spórával, a következő hatások lépnek életbe:</p>
     * <ul>
     *   <li>A rovar maximális fogyasztási képessége letiltásra kerül.</li>
     *   <li>A rovar fogyasztási hatékonysága nullára csökken.</li>
     * </ul>
     *
     * <p>A metódus hívásait a {@link CallTracer} osztály naplózza.</p>
     *
     * @param r A rovar, amelyet a spóra befolyásol.
     */
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setMaxFogyasztas", "Rovar", "false");
        r.setMaxFogyasztas(false);
        CallTracer.exit("setMaxFogyasztas", "");
        CallTracer.enter("setEvesHatekonysag", "Rovar", "0");
        r.setEvesHatekonysag(0);
        CallTracer.exit("setEvesHatekonysag", "");
    }
}
