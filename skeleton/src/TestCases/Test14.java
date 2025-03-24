package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test14} osztály a 14. szkeleton tesztesetet implementálja.
 *
 * <p><b>14: Tekton Hatás Kifejtés: GombatestGatloHatas teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A {@link Tekton} hatás kifejtésének tesztelése {@link GombaTestGatloHatas} objektummal.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Előzmények: {@code GombaTestGatloHatas} típusú hatással rendelkező {@code Tekton} létrehozása.</li>
 *     <li>Hatás beállítása a Tektonon.</li>
 *     <li>Hatás kifejtése a Tektonon.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link GombaTestGatloHatas} - A Tekton hatását befolyásoló gátló hatás.</li>
 *     <li>{@link TektonHatas} - Általános Tekton hatás.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test14 extends TestCase implements ITestCase {
    /** A GombatestGátló hatás objektum. */
    GombaTestGatloHatas h;

    /** A Tekton objektum, amelyre a hatás kifejtődik. */
    Tekton t1;

    /**
     * Létrehoz egy új {@code Test14} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test14(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Tekton gátló hatás kifejtését teszteljük.
     */
    @Override
    public void runTest() {
        CallTracer.enter("hatasKifejtes", "Tekton", "");
        String hatas = t1.hatasKifejtes();
        if (hatas.equals("GombaTestGatlo")) {
            CallTracer.exit("hatasKifejtes", hatas);
        } else {
            CallTracer.exit("hatasKifejtes", "HIBA");
        }
        reset();
    }

    private void reset(){
        h = new GombaTestGatloHatas();
        t1 = new Tekton(h);
        h.setTekton(t1);
    }
}
