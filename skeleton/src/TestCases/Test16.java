package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test16} osztály a 16. szkeleton tesztesetet implementálja.
 *
 * <p><b>16: Tekton Kettétörés Teszt - nincs Rovar a tektonon</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A {@link Tekton} törés szimuláció, úgy, hogy nincs a Tektonon {@link Rovar}</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Fonalak felszívása a kettétörő Tektonon</li>
 *          <li>Fokszám és kapcsolódó fonalak csökkentése a fonallal kapcsolt Tektonokon.</li>Fokszám és kapcsolódó fonalak csökkentése a fonallal kapcsolt Tektonokon.
 *          <li>A Tektonon lévő Gomba vagy Spórák törlése.</li>
 *          <li>Két új tekton létrehozása</li>
 *          <li>A régi tekton szomszédai, a létrejövő két új szomszidaivá válnak</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektumok.</li>
 *     <li>{@link TektonHatas} - A Tektonon ható hatás objektum.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-22
 */

public class Test16 extends TestCase implements ITestCase {
    //Test attributes
    /** A Tektonokon ható tektonhatás*/
    private TektonHatas h;
    /** Az egyik Tekton*/
    private Tekton t1;
    /** A másik tekton*/
    private Tekton t2;
    /** A Rovar*/
    Rovar r;
    /** A Gombafonal ami a tektonok között húzódik*/
    Gombafonal gf;
    /** A Gomba ami az egyik tektonon helyezkedik el*/
    Gomba g;

    //Test init
    /**
     * Létrehoz egy új {@code Test16} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test16(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, ami a tektontörést szimulálja úgy, hogy nincsen rajta Rovar
     */
    @Override
    public void runTest() {
        CallTracer.enter("tektonTores", "Tekton", "");
        Rovar null_r = t1.tektonTores();
        if (null_r == null) {
            CallTracer.exit("tektonTores", "null");

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
        } else {
            CallTracer.exit("tektonTores", "HIBA");
        }
        reset();
    }

    private void reset() {
        h = new TektonHatas();      //1;
        t1= new Tekton(h);          //2
        t2= new Tekton(h);          //3
        r = new Rovar();            //4
        r.setHelyzet(t2);           //5
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
}
