package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test9} osztály a kilencedik szkeleton tesztesetet implementálja.
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

public class Test9 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Rovar r;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test9(CallTracer callTracer) {
        super(callTracer);
        r = new Rovar();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("kepessegekAlaphelyzetbe", "Rovar", "");
        r.kepessegekAlaphelyzetbe();
        CallTracer.exit("kepessegekAlaphelyzetbe", "");
    }
}
