package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test31} osztály a 31. szkeleton tesztesetet implementálja.
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

public class Test31 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    private TektonHatas th1;
    private TektonHatas th2;
    private Gombasz gsz;
    private Field f;
    private Tekton t1;
    private Tekton celTekton;
    private Gomba g;
    private GombaTest gt;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test31(CallTracer callTracer) {
        super(callTracer);
        th1 = new TektonHatas();     //1;
        gsz = new Gombasz();        //2;
        f = new Field();            //3;
        t1 = new Tekton(th1);        //4;
        f.addTekton(t1);             //5;
        th2 = new TektonHatas();
        celTekton = new Tekton(th2);    //6
        th1.setTekton(t1);
        th2.setTekton(celTekton);
        f.addTekton(celTekton);     //7
        g = new Gomba(t1);          //8
        t1.setGomba(g);
        gsz.addGomba(g);            //9
        gt = new GombaTest(g, 0);  //10 Úgy állítjuk-be hogy ne legyen elég spórája a szórásra
        g.setGombaTest(gt);
        celTekton.addSzomszedosTekton(t1);  //11
        t1.addSzomszedosTekton(celTekton);  //12
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("szoras", "Gombasz", "g, celTekton");
        if(!gsz.szoras(g, celTekton)) {
            CallTracer.exit("szoras", "false");
        } else {
            CallTracer.exit("szoras", "HIBA");
        }
    }
}
