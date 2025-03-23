package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test25} osztály a 25. szkeleton tesztesetet implementálja.
 *
 * <p><b>25: Gombafonal Folytonosság Megszakadása Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Ha tekton törés, felszívódás, vagy fonal vágás hatására egy összefüggő fonal sorozat megszakad, akkor a Gombászának ezt újra folytonossá kell tennie, különben az elszakadt rész törlődik.
 * ConnectedToAlapGomba: A föggvény ellenőrzi, hogy az adott Gombafonal összeköttetésben áll-e az alap gombájával.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Folytonosság ellenőrzése</li>
 *          <li>Gombafonal listájának ellenőrzése</li>
 *          <li>Ebben az esetben nem folytonos a fonal szakasz (T3 és T4 között nincs Gombafonal).</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 *     <li>{@link Gombafonal} - A teszt során használt Gombafonal objektum.</li>
 *     <li>{@link Gomba} - A teszt során használt Gomba objektum.</li>
 *     <li>{@link GombaTest} - A teszt során használt GombaTest objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test25 extends TestCase implements ITestCase {
    //Test attributes
    /** A teszt során használt első TektonHatas*/
    TektonHatas th1;
    /** A teszt során használt második TektonHatas*/
    TektonHatas th2;
    /** A teszt során használt harmadik TektonHatas*/
    TektonHatas th3;
    /** A teszt során használt negyedik TektonHatas*/
    TektonHatas th4;
    /** A teszt során használt első Tekton*/
    Tekton t1;
    /** A teszt során használt második Tekton*/
    Tekton t2;
    /** A teszt során használt harmadik Tekton*/
    Tekton t3;
    /** A teszt során használt negyedik Tekton*/
    Tekton t4;
    /** A teszt során használt első Gombafonal*/
    Gombafonal gf1;
    /** A teszt során használt második Gombafonal*/
    Gombafonal gf2;
    /** A teszt során használt Gomba*/
    Gomba g;
    /** A teszt során használt Gombatest*/
    GombaTest gt;


    //Test init
    /**
     * Létrehoz egy új {@code Test25} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
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
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a Gombafonal folytonosság megszakadását teszteli
     */
    @Override
    public void runTest() {
        CallTracer.enter("fonalFolytonossagVizsgalat", "Gomba", "");
        List<Gombafonal> fonalLista = g.fonalFolytonossagVizsgalat();
        CallTracer.exit("fonalFolytonossagVizsgalat", "listOfDisconnectedFonalak");
    }
}
