package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.GombaTestGatloHatas;
import FungoriumClasses.Tekton;
import FungoriumClasses.TektonHatas;

/**
 * A {@code Test14} osztály a 14. szkeleton tesztesetet implementálja.
 *
 * <p>Az osztály tartalmazza a ... (attribútumok felsorolása).
 * Lehetőséget biztosít az /num/. teszteset végrehajtására ami ... (mit csinál).</p>
 *
 * <p>Kapcsolódó osztályok:
 * {@link Osztaly} - ... (funkcio: pl: EGy gombafonalhoz tartozó start és céltekton).</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2025-03-18
 */

public class Test14 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    GombaTestGatloHatas h;
    Tekton t1;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test14(CallTracer callTracer) {
        super(callTracer);
        h = new GombaTestGatloHatas();
        t1 = new Tekton(h);
        h.setTekton(t1);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
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
    }
}
