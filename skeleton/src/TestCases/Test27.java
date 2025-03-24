package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test27} osztály a 27. szkeleton tesztesetet implementálja, amely a rovar irányításának sikertelen végrehajtását teszteli,
 * ha a céltekton nincs gombafonallal összekötve az induló tektonnal.
 *
 * <p><b>27: Rovar Irány Megadás Teszt - Fonallal Nem Összekötött Tektonok</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszt során egy rovarász megpróbál irányítani egy rovart egy olyan tektonra, amely szomszédos ugyan,
 * de nincs gombafonallal összekötve. Ebben az esetben a lépés sikertelen kell legyen.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>A rovarász egy rovart próbál irányítani.</li>
 *     <li>A kiválasztott céltekton vagy nem szomszédos, vagy nincs gombafonallal összekötve az induló tektonnal.</li>
 *     <li>A lépést nem lehet végrehajtani.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Rovarasz} - A rovarokat irányító objektum.</li>
 *     <li>{@link Rovar} - A mozgásra képes onektum, amelyet a rovarász irányít.</li>
 *     <li>{@link Tekton} - Tekton objektum.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test27 extends TestCase implements ITestCase {
    /** Teszt attributumok. */
    private TektonHatas th1;
    private TektonHatas th2;
    private Rovarasz rsz;
    private Rovar r;
    private Tekton t1;
    private Tekton t2;

    /**
     * Létrehoz egy új {@code Test27} objektumot és inicializálja a tesztkörnyezetet.
     *
     * @param callTracer a függvényhívások nyomon követésére és logolásra szolgáló objektum
     */
    public Test27(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely során egy rovar nem tud átlépni egy gombafonallal nem összekötött tektonra.
     */
    @Override
    public void runTest() {
        CallTracer.enter("rovarIranyitas", "Rovarasz", "r, t2");
        if(rsz.rovarIranyitas(r, t2)) {
            CallTracer.exit("rovarIranyitas", "HIBA");
        } else {
            CallTracer.exit("rovarIranyitas", "false");
        }
        reset();
    }

    private void reset() {
        th1 = new TektonHatas(); //1;
        th2 = new TektonHatas();
        rsz = new Rovarasz();   //2
        r = new Rovar();        //3
        t1 = new Tekton(th2);   //4
        t2 = new Tekton(th1);   //5
        th1.setTekton(t1);
        th2.setTekton(t2);
        //Fonallal nem kötjük most össze a két tektont!
        rsz.addRovar(r, t1);    //7 - de kommunikációs diagrammhoz képest változott
        t1.setRovar(r);  //12    implicit állítja a 13-at   Kommunikációsokon pont fordítva vannak felvéve a rovar és üres tektonok
        t2.setRovar(null); //14        implicit állitja a 15-öt
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
    }
}
