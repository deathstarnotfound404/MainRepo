package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test6} osztály a hatodik szkeleton tesztesetet implementálja.
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

public class Test6 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    Gombasz gsz;
    Gomba g;
    GombaTest gt;
    TektonHatas th2;
    Tekton t2;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test6(CallTracer callTracer) {
        super(callTracer);
        gsz = new Gombasz();
        th2 = new TektonHatas();
        t2 = new Tekton(th2);
        g = new Gomba(t2);
        gt = new GombaTest(g, 3);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("getSzorasCount", "GombaTest", "");
        int szorasCount = gt.getSzorasCount();
        CallTracer.exit("getSzorasCount", "szorasCount");
        int choice = makeDecision("Hármas szinten van-e a gomba?", Arrays.asList("Nem", "Igen"));
        switch (choice) {
            case 1: //if(szorasCount % 3 == 0 && szorasCount != 9)
                CallTracer.enter("szintlepes", "GombaTest", "szorasCount");
                gt.szintlepes(szorasCount);
                CallTracer.exit("szintlepes", "");
                break;

            case 2://if(szorasCount == 9)
                CallTracer.enter("szintlepes", "GombaTest", "szorasCount");
                gt.szintlepes(szorasCount);
                CallTracer.exit("szintlepes", "");
                CallTracer.enter("elpusztul", "Gomba", "");
                g.elpusztul();
                CallTracer.exit("elpusztul", "");
                CallTracer.enter("elpusztul", "GombaTest", "");
                gt.elpusztul();
                CallTracer.exit("elpusztul", "");
                break;

            default:
                break;
        }
    }
}
