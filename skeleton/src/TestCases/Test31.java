package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test31} osztály a 31. szkeleton tesztesetet implementálja.
 *
 * <p><b>31: Spóra Szórás Teszt - Nincs elég spórakészlet</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Nem lehetséges a spóraszórás, mert nincs elég spóra a készleten.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Szomszédos tekton kapott célnak, de nincs elég Spórája a GombaTestnek a művelet elvégzéséhez.</li>
 *     <li>A GombaTestnek szinttől függetlenül 3 spórára van szüksége a művelet elvégzéséhez.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a spóraszórást végzi.</li>
 *     <li>{@link Tekton} - Tekton.</li>
 *     <li>{@link Gomba} - Gomba.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test31 extends TestCase implements ITestCase {
    /** Teszt attribútumok */
    private TektonHatas th1;
    private TektonHatas th2;
    private Gombasz gsz;
    private Field f;
    private Tekton t1;
    private Tekton celTekton;
    private Gomba g;
    private GombaTest gt;

    /**
     * Létrehoz egy új {@code Test31} objektumot és inicializálja a tesztkörnyezetet.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test31(CallTracer callTracer) {
        super(callTracer);
        th1 = new TektonHatas();     //1;
        gsz = new Gombasz();        //2;
        f = new Field();            //3;
        t1 = new Tekton(th1);        //4;
        f.addTekton(t1);             //5;
        th2 = new TektonHatas();
        celTekton = new Tekton(th2);    //6
        th1.setTekton(t1);
        th2.setTekton(celTekton);
        f.addTekton(celTekton);     //7
        g = new Gomba(t1);          //8
        t1.setGomba(g);
        gsz.addGomba(g);            //9
        gt = new GombaTest(g, 0);  //10 Úgy állítjuk-be hogy ne legyen elég spórája a szórásra
        g.setGombaTest(gt);
        celTekton.addSzomszedosTekton(t1);  //11
        t1.addSzomszedosTekton(celTekton);  //12
    }

    /**
     * Végrehajtja a tesztesetet, amely ellenőrzi, hogy a spóraszórás nem történik meg elégtelen spórakészlet esetén.
     */
    @Override
    public void runTest() {
        CallTracer.enter("szoras", "Gombasz", "g, celTekton");
        if(!gsz.szoras(g, celTekton)) {
            CallTracer.exit("szoras", "false");
        } else {
            CallTracer.exit("szoras", "HIBA");
        }
    }
}
