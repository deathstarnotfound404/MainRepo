package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test6} osztály a hatodik szkeleton tesztesetet implementálja.
 * <p><b>6: Gombatest Szintlépés Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A gombatest szintlépésének ellenőrzése.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Előzmények: Gombatest 1. szintű, valamint spóra szórás 3-mal osztható</li>
 *     <li>Gomba spórát szór</li>
 *     <li>GombaTest szintlépése megtörténik</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A gombászt reprezentáló osztály.</li>
 *     <li>{@link Gomba} - A gomba objektum, amit a gombász irányít.</li>
 *     <li>{@link GombaTest} - A gomba növekedési és fejlődési fázisait kezelő osztály.</li>
 *     <li>{@link Tekton} - A gomba elhelyezkedését reprezentáló objektum.</li>
 *     <li>{@link TektonHatas} - A Tektonhoz kapcsolódó hatások osztálya.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-21
 */
public class Test6 extends TestCase implements ITestCase {
    // Teszt attribútumok
    /** A gombászt reprezentáló objektum. */
    Gombasz gsz;

    /** A gomba. */
    Gomba g;

    /** A gomba növekedéséért felelős GombaTest objektum. */
    GombaTest gt;

    /** A Tektonhoz tartozó hatás. */
    TektonHatas th2;

    /** Tekton. */
    Tekton t2;

    // Teszt inicializálás
    /**
     * Létrehoz egy új {@code Test6} objektumot, amely előkészíti a gombát és a gombászt a teszthez.
     *
     * @param callTracer A híváskövetőt tartalmazó objektum.
     */
    public Test6(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Gombatest szintlépését ellenőrizzük.
     */
    @Override
    public void runTest() {
        CallTracer.enter("getSzorasCount", "GombaTest", "");
        int szorasCount = gt.getSzorasCount();
        CallTracer.exit("getSzorasCount", "szorasCount");
        int choice = makeDecision("Hármas szinten van-e a gomba?", Arrays.asList("Nem", "Igen"));
        switch (choice) {
            case 1: //if(szorasCount % 3 == 0 && szorasCount != 9)
                CallTracer.enter("szintlepes", "GombaTest", "szorasCount");
                gt.szintlepes(szorasCount);
                CallTracer.exit("szintlepes", "");
                break;

            case 2://if(szorasCount == 9)
                CallTracer.enter("szintlepes", "GombaTest", "szorasCount");
                gt.szintlepes(szorasCount);
                CallTracer.exit("szintlepes", "");
                CallTracer.enter("elpusztul", "Gomba", "");
                g.elpusztul();
                CallTracer.exit("elpusztul", "");
                CallTracer.enter("elpusztul", "GombaTest", "");
                gt.elpusztul();
                CallTracer.exit("elpusztul", "");
                break;

            default:
                break;
        }
        reset();
    }

    private void reset () {
        gsz = new Gombasz();
        th2 = new TektonHatas();
        t2 = new Tekton(th2);
        g = new Gomba(t2);
        gt = new GombaTest(g, 3);
    }
}
