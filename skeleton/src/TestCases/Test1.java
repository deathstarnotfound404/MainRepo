package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;


/**
 * A {@code Test1} osztály az első szkeleton tesztesetet implementálja.
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

public class Test1 extends TestCase implements ITestCase{
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Field f;
    Tekton t1, t2, t3, t4;
    FonalFelszivodoHatas th1;
    FonalGatloHatas th2;
    GombaTestGatloHatas th3;
    TektonHatas th4;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test1(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        th1 = new FonalFelszivodoHatas();
        th2 = new FonalGatloHatas();
        th3 = new GombaTestGatloHatas();
        th4 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        t3 = new Tekton(th3);
        t4 = new Tekton(th4);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("addTekton", "Field", "t1");
        f.addTekton(t1);
        CallTracer.exit("addTekton", "");
        CallTracer.enter("addTekton", "Field", "t2");
        f.addTekton(t2);
        CallTracer.exit("addTekton", "");
        CallTracer.enter("addTekton", "Field", "t3");
        f.addTekton(t3);
        CallTracer.exit("addTekton", "");
        CallTracer.enter("addTekton", "Field", "t4");
        f.addTekton(t4);
        CallTracer.exit("addTekton", "");

        CallTracer.enter("setAllTektonSzomszed", "Field", "");
        f.setAllTektonSzomszed();
        CallTracer.exit("setAllTektonSzomszed", "");
    }
}
