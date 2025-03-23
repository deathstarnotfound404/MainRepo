package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test17} osztály a 17. szkeleton tesztesetet implementálja.
 *
 * <p><b>17: Gomba Spóra Termelés Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszt ellenőrzi a Gombák spóra temelésekor hivatkozott hívási láncot.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Gombászon meghívjuk az összes GombaTest termelését</li>
 *          <li>A Gombákon egyesével meghívódik a termelés</li>
 *          <li>Döntés: A Gombatest Szintje:</li>
 *          <li>1. 1. szint</li>
 *          <li>2. 2. szint</li>
 *          <li>3. 3. szint</li>
 *          <li>A termelés szinttől függően növeli a GombaTest spórakészletét</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link Gombasz} - A teszt során használt Gombasz objektum.</li>
 *     <li>{@link Gomba} - A teszt során használt Gomba objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 *     <li>{@link GombaTest} - A teszt során használt GombaTest objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test17 extends TestCase implements ITestCase {
    //Test attributes
    /** A tesztben használt Gombász objektum*/
    Gombasz gsz;
    /** Az egyik Gomba*/
    Gomba g1;
    /** A másik Gomba*/
    Gomba g2;
    /** Az egyik Gombához tartozó Gombatest*/
    GombaTest gt1;
    /** A másik Gombához tartozó Gombatest*/
    GombaTest gt2;
    /** Az egyik Tekton*/
    Tekton t1;
    /** A másik Tekton*/
    Tekton t2;
    /** Az egyik TektonHatás*/
    TektonHatas th1;
    /** A másik TektonHatás*/
    TektonHatas th2;

    //Test init
    /**
     * Létrehoz egy új {@code Test17} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test17(CallTracer callTracer) {
        super(callTracer);
        gsz = new Gombasz();
        th1 = new TektonHatas();
        th2 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        g1 = new Gomba(t1);
        g2 = new Gomba(t2);
        gt1 = new GombaTest(g1, 3);
        gt2 = new GombaTest(g2, 3);
        g1.setGombaTest(gt1);
        g2.setGombaTest(gt2);
        gsz.addGomba(g1);
        gsz.addGomba(g2);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amelyben a Gombák spóratermelését ellenőrizzük
     */
    @Override
    public void runTest() {
        CallTracer.enter("sporaTermelesAll", "Gombasz", "");
        gsz.sporaTermelesAll();
        CallTracer.exit("sporaTermelesAll", "");
    }
}
