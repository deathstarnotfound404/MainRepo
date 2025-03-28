package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test33} osztály a 33. szkeleton tesztesetet implementálja.
 *
 * <p><b>33: Gombafonal Irányítás Teszt - Cél Tektonon van Spóra</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Ha a céltektonon van elszórt Spóra, akkor a Gombász az első lerakott fonalat
 * meghosszabbíthatja még 1 fonallal, a fonalkészlet felhasználása nélkül (növekedés gyorsítás).</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Gombafonal létrehozása.</li>
 *     <li>Gombafonal felvétel a Tektonokon és fokszámok növelése.</li>
 *     <li>Fonal felvétele a Gombán.</li>
 *     <li>A Gombász fonalkészletét ekkor NEM csökkentjük.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a gombafonal irányítását végzi.</li>
 *     <li>{@link Tekton} - Tekton.</li>
 *     <li>{@link Gomba} - A gomba, amelynek gombafonalait vizsgáljuk.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test33 extends TestCase implements ITestCase {
    /** Tetszt attribútumok. */
    /** Gombászt reprezentáló objektum. */
    Gombasz gsz;
    /** Tekton hatások az egyes Tektonokhoz. */
    TektonHatas th1;
    TektonHatas th2;
    TektonHatas th3;
    /** A tesztben szereplő Tektonok. */
    Tekton t1;
    Tekton t2;
    Tekton t3;
    /** A tesztben szereplő Gomba. */
    Gomba g;
    /** Gombafonal az irányítás teszteléséhez. */
    Gombafonal gf;

    /**
     * Létrehoz egy új {@code Test33} objektumot és inicializálja a tesztkörnyezetet.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test33(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely a gombafonal irányítását ellenőrzi.
     */
    @Override
    public void runTest() {
        CallTracer.enter("gombafonalIranyitas", "Gombasz", "g, t1, t2");
        if (gsz.gombafonalIranyitas(g, t1, t2, false)) {    //előre tudjuk, hogy t2-n van spóra, ezért állítjuk true-ra
            CallTracer.exit("gombafonalIranyitas", "true");
        } else {
            CallTracer.exit("gombafonalIranyitas", "HIBA");
        }

        CallTracer.enter("gombafonalIranyitas", "Gombasz", "t2, t3");
        if (gsz.gombafonalIranyitas(g, t2, t3, true)) {    //előre tudjuk, hogy t2-n van spóra, ezért állítjuk true-ra
            CallTracer.exit("gombafonalIranyitas", "true");
        } else {
            CallTracer.exit("gombafonalIranyitas", "HIBA");
        }

        reset();
    }

    private void reset(){
        gsz = new Gombasz();            //1
        th1 = new TektonHatas();        //2
        th2 = new TektonHatas();        //3
        th3 = new TektonHatas();
        t1 = new Tekton(th1);           //4
        t2 = new Tekton(th2);           //5
        t3 = new Tekton(th3);
        g = new Gomba(t1);              //6
        t1.addSzomszedosTekton(t2);
        t1.setGomba(g);
        t2.addSzomszedosTekton(t1);
        th1.setTekton(t1);
        th2.setTekton(t2);
        th3.setTekton(t3);
        t3.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t3);
        gsz.addGomba(g);
        GombaTest gt = new GombaTest(g, 5);
        gt.setAlapGomba(g);
        g.setGombaTest(gt);
    }
}
