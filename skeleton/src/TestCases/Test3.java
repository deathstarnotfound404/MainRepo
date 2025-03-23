package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test3} osztály a harmadik szkeleton tesztesetet implementálja.
 *
 * <p>Use-case neve: <b>3: Játék Indítás Teszt - Gombász inicializálás</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A Gombász létrehozza és lehelyezi az első gombáját egy Tektonra.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Gombász létrehozása</li>
 *     <li>Gombász Fieldhez rendelése</li>
 *     <li>Gomba létrehozása</li>
 *     <li>GombaTest növesztése</li>
 *     <li>Gombatest Gombászhoz rendelése</li>
 *     <li>GombaTest Gombájának beállítása</li>
 *     <li>Gomba beállítása a Tektonon</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Field} - A játéktér, amelyen a gombász tevékenykedik.</li>
 *     <li>{@link Gombasz} - A gombászt reprezentáló osztály.</li>
 *     <li>{@link Tekton} - A játékban szereplő Tekton.</li>
 *     <li>{@link TektonHatas} - A Tektonhoz kapcsolódó hatások osztálya.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 *  * @version 1.0
 *  * @since 2025-03-21
**/

public class Test3 extends TestCase implements ITestCase {
    // Teszt attribútumok
    /** A játékteret reprezentáló mező. */
    Field f;

    /** A Gombász játékos. */
    Gombasz gsz;

    /** A Tektonhoz tartozó hatás. */
    TektonHatas th1;

    /** Tekton. */
    Tekton t1;

    // Teszt inicializálás
    /**
     * Létrehoz egy új {@code Test3} objektumot, amely előkészíti a Field-et, Gombászt és a Tektonokat a teszthez.
     *
     * @param callTracer  a hívásokat követő és logoló objektum.
     */
    public Test3(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        gsz = new Gombasz();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Gombász növeszti az első gombáját egy Tektonra.
     */
    @Override
    public void runTest() {
        CallTracer.enter("gombatestNovesztes", "Gombasz", "t1");
        gsz.gombatestNovesztes(t1);
        CallTracer.exit("gombatestNovesztes", "");
        CallTracer.enter("addPlayer", "Field", "gsz");
        f.addPlayer(gsz);
        CallTracer.exit("addPlayer", "");
    }
}
