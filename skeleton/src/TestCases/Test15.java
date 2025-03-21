package TestCases;
import FungoriumClasses.*;
import CallTracer.CallTracer;

/**
 * A {@code Test15} osztály a 15. szkeleton tesztesetet implementálja. Tekton Kettétörés Teszt - van Rovar a tektonon
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */

public class Test15 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    private TektonHatas h;
    private Tekton t1;
    private Tekton t2;
    Rovar r;
    Gombafonal gf;
    Gomba g;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test15(CallTracer callTracer) {
        super(callTracer);
        h = new TektonHatas();      //1;
        t1= new Tekton(h);          //2
        t2= new Tekton(h);          //3
        r = new Rovar();            //4
        r.setHelyzet(t1);           //5
        gf = new Gombafonal(t1, t2);    //6
        t1.addKapcsolodoFonalak(gf);    //7
        t1.increaseFokszam();           //8
        t2.addKapcsolodoFonalak(gf);    //9
        t2.increaseFokszam();           //10
        g = new Gomba(t1);              //11
        g.addFonal(gf);                 //12
        gf.setAlapGomba(g);
        t1.setGomba(g);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("tektonTores", "Tekton", "");
        Rovar null_r = t1.tektonTores();

        if (null_r != null) {
            CallTracer.exit("tektonTores", "tektononLevoRovar");

            CallTracer.enter("elpusztul", "Tekton:t1", "");
            this.t1.elpusztul();
            CallTracer.exit("elpusztul()", "");

            CallTracer.enter("Tekton", "Tekton:t3", "h");
            Tekton t3 = new Tekton(h);
            CallTracer.exit("Tekton()", "");

            CallTracer.enter("Tekton", "Tekton:t4", "h");
            Tekton t4 = new Tekton(h);
            CallTracer.exit("Tekton()", "");

            CallTracer.enter("addSzomszedosTekton", "Tekton:t3", "t4");
            t3.addSzomszedosTekton(t4);
            CallTracer.exit("addSzomszedosTekton", "");
            CallTracer.enter("addSzomszedosTekton", "Tekton", "t3");
            t4.addSzomszedosTekton(t3);
            CallTracer.exit("addSzomszedosTekton", "");
            CallTracer.enter("setHelyzet", "Rovar", "t3");
            null_r.setHelyzet(t3);      //Ami az rí
            CallTracer.exit("setHelyzet", "");
        } else {
            CallTracer.exit("tektonTores", "HIBA");
        }
    }
}
