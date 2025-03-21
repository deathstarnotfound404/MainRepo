package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test23} osztály a 23. szkeleton tesztesetet implementálja.
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

public class Test23 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Field f;
    Rovarasz rsz;
    Rovar r;
    Gombasz gsz;
    TektonHatas th;
    Tekton t;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test23(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        rsz = new Rovarasz();
        r = new Rovar();
        gsz = new Gombasz();
        th = new TektonHatas();
        t = new Tekton(th);
        rsz.addRovar(r, t);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("kiertekeles", "Field", "");
        f.kiertekeles();
        CallTracer.exit("kiertekeles", "gyoztesJatekosok");
    }
}
