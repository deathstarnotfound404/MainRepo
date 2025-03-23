package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;


/**
 * A {@code Test29} osztály a 29. szkeleton tesztesetet implementálja, amely a spóra szórás tesztelésére szolgál.
 *
 * <p><b>29: Spóra Szórás Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszt során egy gombatestből spórát szórunk egy szomszédos tektonra. A művelet sikeressége a gombatest szintjétől és a rendelkezésre álló spórák mennyiségétől függ.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>A céltekton egy szomszédos tekton.</li>
 *     <li>A gombatest rendelkezik elegendő spórával.</li>
 *     <li>A gombatest szintje meghatározza a szórás feltételeit:
 *         <ul>
 *             <li><b>1. szint:</b> 2 spóra szükséges, és 1 spórát szórhat a szomszédos tektonra.</li>
 *             <li><b>2. szint:</b> 1 spóra szükséges, és 2 spórát szórhat a szomszédos tektonra.</li>
 *             <li><b>3. szint:</b> 0 spóra szükséges, és 3 spórát szórhat akár a szomszédos, akár a szomszéd szomszédos tektonra.</li>
 *         </ul>
 *     </li>
 *     <li>A szórás sikeres esetben növeli a gombatest szórás számlálóját.</li>
 *     <li>Új spóra objektumok jönnek létre, amelyek bekerülnek a céltekton spóra listájába.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a spóraszórást végzi.</li>
 *     <li>{@link Tekton} - Tekton</li>
 *     <li>{@link Gomba} - Gomba osztály.</li>
 *     <li>{@link Spora} - A szórás során létrejövő spóra objektumok.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-20
 */
public class Test29 extends TestCase implements ITestCase {
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
     * Létrehoz egy új {@code Test29} objektumot és inicializálja a tesztkörnyezetet.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test29(CallTracer callTracer) {
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
        g.setGombaTest(gt);
        celTekton.addSzomszedosTekton(t1);  //11
        t1.addSzomszedosTekton(celTekton);  //12
    }

    /**
     * Végrehajtja a tesztesetet, amely ellenőrzi a spóraszórás sikerességét.
     */
    @Override
    public void runTest() {
        CallTracer.enter("szoras", "Gombasz", "g, t1");
        if(gsz.szoras(g, celTekton)) {
            CallTracer.exit("szoras", "true");
        } else {
            CallTracer.exit("szoras", "HIBA");
        }
    }
}
