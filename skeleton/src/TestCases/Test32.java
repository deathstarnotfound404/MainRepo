package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test32} osztály a 32. szkeleton tesztesetet implementálja.
 *
 * <p><b>32: Spóra Szórás Teszt - Cél Tektonon már van GombaTest</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A spóraszórás során a céltektonon már van egy gombatest.
 * A céltektonon levő gombatest megkapja a spórákat.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>A spóraszórás sikeres elvégzése.</li>
 *     <li>Az érkező spóra objektumokat a listából átveszi a GombaTest.</li>
 *     <li>A Spórákat a művelet elpusztítja a GombaTestekbe történő átvezetést követően.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a spóraszórást végzi.</li>
 *     <li>{@link Tekton} - Tekton.</li>
 *     <li>{@link Gomba} - A gomba, amelynek spóráit vizsgáljuk.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-22
 */
public class Test32 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    private TektonHatas th1;
    private TektonHatas th2;
    private Gombasz gsz;
    private Field f;
    private Tekton t1;
    private Tekton celTekton;
    private Gomba g;
    private GombaTest gt;
    private Gomba g2;
    private GombaTest gt2;

    /**
     * Létrehoz egy új {@code Test32} objektumot és inicializálja a tesztkörnyezetet.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test32(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely a spóraszórást ellenőrzi.
     */
    @Override
    public void runTest() {
        CallTracer.enter("szoras", "Gombasz", "g, celTekton");
        if(gsz.szoras(g, celTekton)) {
            CallTracer.exit("szoras", "true");
        } else {
            CallTracer.exit("szoras", "HIBA");
        }
        reset();
    }

    /**
     * Visszaállítja a tesztkörnyezetet az alapállapotba.
     */
    public void reset() {
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
        gt = new GombaTest(g, 5);  //10
        celTekton.addSzomszedosTekton(t1);  //11
        t1.addSzomszedosTekton(celTekton);  //12
        gt.setAlapGomba(g);
        g.setGombaTest(gt);
        //----
        //Második gomba és gombatest beállítása a céltektonon
        g2 = new Gomba(celTekton);
        celTekton.setGomba(g2);
        gt2 = new GombaTest(g2, 5);
        gt2.setAlapGomba(g2);
        g2.setGombaTest(gt2);
    }
}
