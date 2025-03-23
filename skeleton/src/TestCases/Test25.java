package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test25} osztály a 25. szkeleton tesztesetet implementálja.
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

public class Test25 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */

    TektonHatas th1;
    TektonHatas th2;
    TektonHatas th3;
    TektonHatas th4;
    Tekton t1;
    Tekton t2;
    Tekton t3;
    Tekton t4;
    Gombafonal gf1;
    Gombafonal gf2;
    Gomba g;
    GombaTest gt;


    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test25(CallTracer callTracer) {
        super(callTracer);
        th1 = new TektonHatas();
        th2 = new TektonHatas();
        th3 = new TektonHatas();
        th4 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        t3 = new Tekton(th3);
        t4 = new Tekton(th4);
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
        t2.addSzomszedosTekton(t3);
        t3.addSzomszedosTekton(t2);
        t3.addSzomszedosTekton(t4);
        t4.addSzomszedosTekton(t3);
        g =new Gomba(t1);
        gt = new GombaTest(g, 3);
        gf1 = new Gombafonal(t1, t2);
        gf2 = new Gombafonal(t3, t4);
        gf1.setAlapGomba(g);
        gf2.setAlapGomba(g);
        t1.addKapcsolodoFonalak(gf1);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf1);
        t2.increaseFokszam();
        t3.addKapcsolodoFonalak(gf2);
        t3.increaseFokszam();
        t4.addKapcsolodoFonalak(gf2);
        t4.increaseFokszam();
        g.setGombaTest(gt);
        gt.setAlapGomba(g);
        g.addFonal(gf1);
        g.addFonal(gf2);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("fonalFolytonossagVizsgalat", "Gomba", "");
        List<Gombafonal> fonalLista = g.fonalFolytonossagVizsgalat();
        CallTracer.exit("fonalFolytonossagVizsgalat", "listOfDisconnectedFonalak");
    }
}
