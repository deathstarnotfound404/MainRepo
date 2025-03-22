package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test26} osztály a 26. szkeleton tesztesetet implementálja.
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

public class Test26 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    private TektonHatas th1;
    private TektonHatas th2;
    private Rovarasz rsz;
    private Rovar r;
    private Tekton t1;
    private Tekton t2;
    private Gombafonal gf;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test26(CallTracer callTracer) {
        super(callTracer);
        th1 = new TektonHatas(); //1;
        th2 = new TektonHatas();
        rsz = new Rovarasz();   //2
        r = new Rovar();        //3
        t1 = new Tekton(th2);   //4
        t2 = new Tekton(th1);   //5
        th1.setTekton(t1);
        th2.setTekton(t2);
        gf = new Gombafonal(t1, t2);    //6
        rsz.addRovar(r, t1);    //7 - de kommunikációs diagrammhoz képest változott
        t1.addKapcsolodoFonalak(gf);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf);
        t2.increaseFokszam();
        t1.setRovar(r);  //12    implicit állítja a 13-at   Kommunikációsokon pont fordítva vannak felvéve a rovar és üres tektonok
        t2.setRovar(null); //14        implicit állitja a 15-öt
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("rovarIranyitas", "Rovarasz", "r, t2");
        if(rsz.rovarIranyitas(r, t2)) {
            CallTracer.exit("rovarIranyitas", "true");
        } else {
            CallTracer.exit("rovarIranyitas", "false");
        }
    }
}
