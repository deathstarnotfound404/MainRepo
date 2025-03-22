package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test2} osztály a második szkeleton tesztesetet implementálja.
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

public class Test2 extends TestCase implements ITestCase{
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Field f;
    Rovarasz rsz;
    Rovar r;
    Tekton t1;
    TektonHatas th1;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test2(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        rsz = new Rovarasz();
        r = new Rovar();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);

    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("setHelyzet", "Rovar", "t1");
        r.setHelyzet(t1);
        CallTracer.exit("setHelyzet", "");
        CallTracer.enter("addRovar", "Rovarasz", "r, t1");
        rsz.addRovar(r, t1);
        CallTracer.exit("addRovar", "");
        CallTracer.enter("addPlayer", "Field", "rsz");
        f.addPlayer(rsz);
        CallTracer.exit("addPlayer", "");
    }
}
