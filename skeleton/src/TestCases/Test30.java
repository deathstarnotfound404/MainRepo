package TestCases;
import CallTracer.*;
import FungoriumClasses.*;

/**
 * A {@code Test30} osztály a 30. szkeleton tesztesetet implementálja.
 *
 * <p><b>30: Spóra Szórás Teszt - Nem szomszédos Tektonok</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Nem lehetséges a spóraszórás, mert nem szomszédos a céltekton.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Nem szomszédos tekton célként történő kiválasztása.</li>
 *     <li>A Gombatest szintjétől függően különböző helyzetek lépnek fel:</li>
 *     <ul>
 *         <li>1. szintű Gombatest: céltekton nem közvetlen szomszéd.</li>
 *         <li>2. szintű Gombatest: céltekton nem közvetlen szomszéd.</li>
 *         <li>3. szintű Gombatest: céltekton nem szomszéd vagy annak szomszédja.</li>
 *     </ul>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a spóraszórást végzi.</li>
 *     <li>{@link Tekton} - Tekton.</li>
 *     <li>{@link Gomba} - A gomba, amelynek a szórását vizsgáljuk.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test30 extends TestCase implements ITestCase {
    /** Teszt attributumok. */
    private TektonHatas th1;
    private TektonHatas th2;
    private Gombasz gsz;
    private Field f;
    private Tekton t1;
    private Tekton celTekton;
    private Gomba g;
    private GombaTest gt;

    /**
     * Létrehoz egy új {@code Test30} objektumot és inicializálja a tesztkörnyezetet.
     * Fontos, hogy nem állítjuk be szomszédoknak a tektonokat
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test30(CallTracer callTracer) {
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
        gt = new GombaTest(g, 5);  //10
        gt.setAlapGomba(g);
        g.setGombaTest(gt);
    }

    /**
     * Végrehajtja a tesztesetet, amely a spóraszórást ellenőrzi.
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
