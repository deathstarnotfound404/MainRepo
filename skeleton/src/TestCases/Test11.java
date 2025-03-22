package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test11} osztály a 11. szkeleton tesztesetet implementálja.
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

public class Test11 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    Tekton t1;
    FonalFelszivodoHatas h1;
    TektonHatas h2;
    Gombafonal gf;
    Gomba g;
    Tekton t2;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test11(CallTracer callTracer) {
        super(callTracer);
        h1 = new FonalFelszivodoHatas();
        t1 = new Tekton(h1);
        h1.setTekton(t1);
        g = new Gomba(t1);
        t1.setGomba(g);

        h2 = new TektonHatas();
        t2 = new Tekton(h2);
        h2.setTekton(t2);

        gf = new Gombafonal(t1, t2);
        gf.setAlapGomba(g);
        t1.addKapcsolodoFonalak(gf);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf);
        t2.increaseFokszam();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("hatasKifejtes", "Tekton", "");
        String hatas = t1.hatasKifejtes();
        if (hatas.equals("Felszivas")) {
            CallTracer.exit("hatasKifejtes", hatas);
        } else {
            CallTracer.exit("hatasKifejtes", "HIBA");
        }
    }
}
