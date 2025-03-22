package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.FonalGatloHatas;
import FungoriumClasses.Tekton;

import java.util.Arrays;

/**
 * A {@code Test13} osztály a 13. szkeleton tesztesetet implementálja.
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

public class Test13 extends TestCase implements ITestCase{
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    FonalGatloHatas h;
    Tekton t1;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test13(CallTracer callTracer) {
        super(callTracer);
        h = new FonalGatloHatas();
        t1 = new Tekton(h);
        h.setTekton(t1);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        int choice = makeDecision("Mekkora a t1-es tekton fonal fokszáma?", Arrays.asList("fonalFokszam > 0", "fonalFokszam == 0"));
        switch (choice) {
            case 1:
                t1.setFokszam(3);
                CallTracer.enter("hatasKifejtes", "Tekton", "");
                String hatas1 = t1.hatasKifejtes();
                if (hatas1.equals("FonalGatlo")) {
                    CallTracer.exit("hatasKifejtes", hatas1);
                } else {
                    CallTracer.exit("hatasKifejtes", "HIBA");
                }
                break;
            case 2:
                t1.setFokszam(0);
                CallTracer.enter("hatasKifejtes", "Tekton", "");
                String hatas2 = t1.hatasKifejtes();
                if (hatas2.equals("NincsFonalGatlo")) {
                    CallTracer.exit("hatasKifejtes", hatas2);
                } else {
                    CallTracer.exit("hatasKifejtes", "HIBA");
                }
                break;
            default:
                break;
        }
    }
}
