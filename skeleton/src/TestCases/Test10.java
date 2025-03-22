package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;


/**
 * A {@code Test10} osztály a tizedik szkeleton tesztesetet implementálja.
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

public class Test10 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Tekton t;
    TektonHatas th;


    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test10(CallTracer callTracer) {
        super(callTracer);
        th = new TektonHatas();
        t = new Tekton(th);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("hatasKifejtes", "Tekton", "");
        String hatas = t.hatasKifejtes();
        if (hatas.equals("Base")) {
            CallTracer.exit("hatasKifejtes", hatas);
        } else {
            CallTracer.exit("hatasKifejtes", "HIBA");
        }
    }
}
