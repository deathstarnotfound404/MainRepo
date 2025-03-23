package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test10} osztály a tizedik szkeleton tesztesetet implementálja.
 * <p><b>10: Tekton Hatás Kifejtés: TektonHatas teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A Tekton hatásának kifejtését teszteli egy TektonHatas objektummal.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>TektonHatas típusú hatással rendelkező Tekton létrehozása</li>
 *     <li>Hatás kifejtése a Tektonon.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A tesztelt objektum, amely a Tekton hatását tartalmazza.</li>
 *     <li>{@link TektonHatas} - A Tektonhoz kapcsolódó hatás osztálya.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-21
 */
public class Test10 extends TestCase implements ITestCase {
    // Teszt attribútumok
    /** A Tekton objektum. */
    Tekton t;

    /** A Tekton hatását kezelő objektum. */
    TektonHatas th;


    // Teszt inicializálás
    /**
     * Létrehoz egy új {@code Test10} objektumot, amely inicializálja a Tekton és TektonHatas objektumokat.
     *
     * @param callTracer A híváskövetőt és logolást tartalmazó objektum.
     */
    public Test10(CallTracer callTracer) {
        super(callTracer);
        th = new TektonHatas();
        t = new Tekton(th);
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Tekton hatását ellenőrizzük.
     */
    @Override
    public void runTest() {
        CallTracer.enter("hatasKifejtes", "Tekton", "");
        String hatas = t.hatasKifejtes();
        if (hatas.equals("Base")) {
            CallTracer.exit("hatasKifejtes", hatas);
        } else {
            CallTracer.exit("hatasKifejtes", "HIBA");
        }
    }
}
