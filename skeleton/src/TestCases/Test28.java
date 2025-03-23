package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test28} osztály a 28. szkeleton tesztesetet implementálja, amely a rovar irányításának sikertelen végrehajtását teszteli,
 * ha a céltektonon már van egy másik rovar.
 *
 * <p><b>28: Rovar Irány Megadás Teszt - Cél Tektonon van Rovar</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszt során egy rovarász megpróbál irányítani egy rovart egy olyan tektonra, amely szomszédos és gombafonallal össze van kötve,
 * de a céltektonon már tartózkodik egy másik rovar. Ebben az esetben a lépés sikertelen kell legyen.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>A rovarász egy rovart próbál irányítani.</li>
 *     <li>A kiválasztott céltekton szomszédos, és egy gombafonal is összeköti az induló tektonnal.</li>
 *     <li>A céltektonon már van egy másik rovar.</li>
 *     <li>A lépést nem lehet végrehajtani.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Rovarasz} - A rovarokat irányító objektum.</li>
 *     <li>{@link Rovar} - Rovar objektum.</li>
 *     <li>{@link Tekton} - Tekton objektum.</li>
 *     <li>{@link Gombafonal} - Két tektont összekötő objektum, amely a mozgást befolyásolja.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-18
 */
public class Test28 extends TestCase implements ITestCase {
    /** Teszt attribútumok. */
    private TektonHatas th1;
    private TektonHatas th2;
    private Rovarasz rsz;
    private Rovar r;
    private Tekton t1;
    private Tekton t2;
    private Gombafonal gf;
    private Rovarasz rsz2;
    private Rovar r2;

    /**
     * Létrehoz egy új {@code Test28} objektumot és inicializálja a tesztkörnyezetet.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test28(CallTracer callTracer) {
        super(callTracer);
        th1 = new TektonHatas(); //1;
        th2 = new TektonHatas();
        rsz = new Rovarasz();   //2
        r = new Rovar();        //3
        rsz2 = new Rovarasz();
        r2 = new Rovar();
        t1 = new Tekton(th2);   //4
        t2 = new Tekton(th1);   //5
        th1.setTekton(t1);
        th2.setTekton(t2);
        gf = new Gombafonal(t1, t2);    //6
        rsz.addRovar(r, t1);    //7 - de kommunikációs diagrammhoz képest változott
        rsz2.addRovar(r2, t2);
        t1.addKapcsolodoFonalak(gf);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf);
        t2.increaseFokszam();
        t1.setRovar(r);  //12    implicit állítja a 13-at   Kommunikációsokon pont fordítva vannak felvéve a rovar és üres tektonok
        t2.setRovar(r2); //14        implicit állitja a 15-öt
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
    }

    /**
     * Végrehajtja a tesztesetet, amely során egy rovar nem tud átlépni egy másik rovart tartalmazó tektonra.
     */
    @Override
    public void runTest() {
        CallTracer.enter("rovarIranyitas", "Rovarasz", "r, t2");
        if(rsz.rovarIranyitas(r, t2)) {
            CallTracer.exit("rovarIranyitas", "HIBA");
        } else {
            CallTracer.exit("rovarIranyitas", "false");
        }
    }
}
