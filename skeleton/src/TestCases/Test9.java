package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test9} osztály a kilencedik szkeleton tesztesetet implementálja.
 * <p><b>9: Rovar Képességek Alaphelyzetbe Állítása Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Egy nem alaphelyzetű Rovar objektum alaphelyzetbe állításának tesztelése.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Nem alaphelyzetű Rovar létrehozása.</li>
 *     <li>Nem default képességekkel rendelkező Rovar alaphelyzetbe állítása.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Rovar} - A tesztelendő rovar osztály.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-21
 */
public class Test9 extends TestCase implements ITestCase {
    /** A tesztelendő rovar objektum. */
    Rovar r;

    /**
     * Létrehoz egy új {@code Test9} objektumot, amely inicializálja a Rovar példányt a teszteléshez.
     *
     * @param callTracer A híváskövetőt tartalmazó és logoló objektum.
     */
    public Test9(CallTracer callTracer) {
        super(callTracer);
        r = new Rovar();
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Rovar képességeit alaphelyzetbe állítjuk.
     */
    @Override
    public void runTest() {
        CallTracer.enter("kepessegekAlaphelyzetbe", "Rovar", "");
        r.kepessegekAlaphelyzetbe();
        CallTracer.exit("kepessegekAlaphelyzetbe", "");
    }
}
