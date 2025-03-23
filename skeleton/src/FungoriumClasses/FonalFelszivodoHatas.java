package FungoriumClasses;
import CallTracer.CallTracer;

/**
 * A {@code FonalFelszivodoHatas} osztály egy speciális {@link TektonHatas}, amely a tektonhoz tartozó fonalak felszívódását okozza.
 *
 * <p>Amikor a hatás aktiválódik, az adott tekton összes fonala felszívódik.</p>
 *
 * <p>Kapcsolódó osztályok:</p>
 * <ul>
 *     <li>{@link TektonHatas} - Az osztály, amelyből ez az osztály származik.</li>
 *     <li>{@link Tekton} - Az osztály, amelyen a hatás végrehajtódik.</li>
 *     <li>{@link CallTracer} - A híváskövetési mechanizmust biztosító osztály.</li>
 * </ul>
 *
 * @author NAME
 * @version 1.0
 * @since 2025-03-19
 */
public class FonalFelszivodoHatas extends TektonHatas{

    /**
     * Létrehoz egy új {@code FonalFelszivodoHatas} objektumot.
     */
    public FonalFelszivodoHatas() {

    }

    /**
     * Aktiválja a hatást, amely során a tektonhoz tartozó fonalak felszívódnak.
     *
     * <p>A metódus először lekéri a hatáshoz tartozó {@link Tekton} objektumot,
     * majd meghívja rajta a {@code fonalakFelszivasa()} metódust.</p>
     *
     * @return Egy szöveges visszajelzés arról, hogy a fonalak felszívódtak, ugyanis ezt a controller tudtára kell hozni.
     */
    public String hatas() {
        Tekton t1 = this.getTekton();
        CallTracer.enter("fonalakFelszivasa", "Tekton", "");
        t1.fonalakFelszivasa();
        CallTracer.exit("fonalakFelszivasa", "");
        return "Felszivas";
    }
}
