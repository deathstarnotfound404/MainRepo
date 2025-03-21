package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test17} osztály a 17. szkeleton tesztesetet implementálja.
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

public class Test17 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Gombasz gsz;
    Gomba g1;
    Gomba g2;
    GombaTest gt1;
    GombaTest gt2;
    Tekton t1;
    Tekton t2;
    TektonHatas th1;
    TektonHatas th2;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test17(CallTracer callTracer) {
        super(callTracer);
        gsz = new Gombasz();
        th1 = new TektonHatas();
        th2 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        g1 = new Gomba(t1);
        g2 = new Gomba(t2);
        gt1 = new GombaTest(g1, 3);
        gt2 = new GombaTest(g2, 3);
        g1.setGombaTest(gt1);
        g2.setGombaTest(gt2);
        gsz.addGomba(g1);
        gsz.addGomba(g2);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("sporaTermelesAll", "Gombasz", "");
        gsz.sporaTermelesAll();
        CallTracer.exit("sporaTermelesAll", "");
    }
}
