package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test24} osztály a 24. szkeleton tesztesetet implementálja.
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

public class Test24 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Rovarasz rsz;
    Rovar r;
    Gombafonal gf;
    Tekton t1;
    Tekton t2;
    TektonHatas th1;
    TektonHatas th2;
    Gomba g;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test24(CallTracer callTracer) {
        super(callTracer);
        rsz = new Rovarasz();
        r = new Rovar();
        th1 = new TektonHatas();
        th2 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        gf = new Gombafonal(t1, t2);
        g = new Gomba(t1);
        rsz.addRovar(r, t2);
        r.setHelyzet(t2);
        t2.setRovar(r);
        gf.setAlapGomba(g);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("fonalVagas", "Rovarasz", "r, gf");
        rsz.fonalVagas(r,gf);
        CallTracer.exit("fonalVagas", "");
    }
}
