package TestCases;
import FungoriumClasses.*;
import CallTracer.CallTracer;

/**
 * A {@code Test15} osztály a 15. szkeleton tesztesetet implementálja.
 *
 * <p><b>15: Tekton Kettétörés Teszt - van Rovar a tektonon</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * {@link Tekton} törés szimuláció, úgy, hogy van a Tektonon {@link Rovar}.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Fonalak felszívása a kettétörő Tektonon</li>
 *          <li>Fokszám és kapcsolódó fonalak csökkentése a fonallal kapcsolt Tektonokon.</li>
 *          <li>A Tektonon lévő Gomba vagy Spórák törlése.</li>
 *          <li>Két új tekton létrehozása</li>
 *          <li>A régi tekton szomszédai, a létrejövő két új szomszédaivá válnak</li>
 *          <li>A kezdeti Tektonon lévő Rovar áthelyezése az egyik új Tektonra.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektumok.</li>
 *     <li>{@link TektonHatas} - A Tektonra hatása.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 *     <li>{@link Gombafonal} - A teszt során használt Gombafonal objektum.</li>
 *     <li>{@link Gomba} - A teszt során használt Gomba objektum.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-22
 */

public class Test15 extends TestCase implements ITestCase {
    //Test attributes
    /** A TektonHatas típusú hatás*/
    private TektonHatas h;
    /** Az egyik Tekton*/
    private Tekton t1;
    /** A másik Tekton*/
    private Tekton t2;
    /** A teszt során használt Rovar*/
    Rovar r;
    /** A Gombafonal ami a tektonokhoz kapcsolódik*/
    Gombafonal gf;
    /** A Gomba objektum amit e teszt során használunk*/
    Gomba g;

    //Test init
    /**
     * Létrehoz egy új {@code Test15} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test15(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely során a tekton kettétörést teszteljük, úgy hogy van rajta Rovar.
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
        reset();
    }
    private void reset() {
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
}
