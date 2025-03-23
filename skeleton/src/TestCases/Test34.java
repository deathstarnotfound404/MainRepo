package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test34} osztály a 34. szkeleton tesztesetet implementálja.
 *
 * <p><b>34: Gombafonal Irányítás Teszt - Fonal nem lerakható</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset a gombász fonal lerakásának azon esetét vizsgálja, amikor nem tud lerakni.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Az ellenőrzés során vagy nincs elég fonal a Gombász fonal készletében, vagy a megadott start és cél tektonok nem szomszédosak.</li>
 *     <li>Döntés: Nincs elég fonal vagy nem szomszédosak a Tektonok:</li>
 *         <ol>
 *             <li>Nincs elég fonal</li>
 *             <li>Nem szomszédos Tektonok</li>
 *         </ol>
 *     <li>Ha egyik feltétel sem teljesül, akkor a művelet nem hajtódik végre.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a gombafonal irányítását végzi.</li>
 *     <li>{@link Tekton} - A tesztben szereplő környezeti elem.</li>
 * </ul>
 *
 * @author Your Name
 * @version 1.0
 * @since 2025-03-18
 */
public class Test34 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    Gombasz gsz;
    TektonHatas th1;
    TektonHatas th2;
    Tekton t1;
    Tekton t2;
    Gomba g;
    Gombafonal gf;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test34(CallTracer callTracer) {
        super(callTracer);
        gsz = new Gombasz();            //1
        th1 = new TektonHatas();        //2
        th2 = new TektonHatas();        //3
        t1 = new Tekton(th1);           //4
        t2 = new Tekton(th2);           //5
        g = new Gomba(t1);              //6
        t1.setGomba(g);
        th1.setTekton(t1);
        th2.setTekton(t2);
        gsz.addGomba(g);
        GombaTest gt = new GombaTest(g, 5);
        gt.setAlapGomba(g);
        g.setGombaTest(gt);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("gombafonalIranyitas", "Gombasz", "g, t1, t2");
        if (!gsz.gombafonalIranyitas(g, t1, t2, false)) {
            CallTracer.exit("gombafonalIranyitas", "false");
        } else {
            CallTracer.exit("gombafonalIranyitas", "HIBA");
        }

    }
}
