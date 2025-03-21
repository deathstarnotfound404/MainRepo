package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test3} osztály a harmadik szkeleton tesztesetet implementálja.
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

public class Test3 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Field f;
    Gombasz gsz;
    TektonHatas th1;
    Tekton t1;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test3(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        gsz = new Gombasz();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("gombatestNovesztes", "Gombasz", "t1");
        gsz.gombatestNovesztes(t1);
        CallTracer.exit("gombatestNovesztes", "");
        CallTracer.enter("addPlayer", "Field", "gsz");
        f.addPlayer(gsz);
        CallTracer.exit("addPlayer", "");
    }
}
