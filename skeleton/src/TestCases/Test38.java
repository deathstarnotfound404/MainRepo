package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test38} osztály a 38. szkeleton tesztesetet implementálja.
 *
 * <p><b>38: Gombafonal Folytonosság Megszakadása Teszt - Nincs megszakadás</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Ha tekton törés, felszívódás vagy fonal vágás hatására egy összefüggő fonalsorozat megszakad,
 * akkor a gombának ezt újra folytonossá kell tennie, különben az elszakadt rész törlődik.
 * Ez a teszteset egy olyan szituációt modellez, ahol nincs fonalszakadás.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Olyan tektonok és gombafonalak létrehozása, amelyek folytonos láncot alkotnak.</li>
 *     <li>A gombafonal folytonosságának ellenőrzése.</li>
 *     <li>Ha nincs megszakadás, a teszt sikeres.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A tektonokat reprezentáló osztály.</li>
 *     <li>{@link Gombafonal} - A tektonokat összekötő gombafonalakat reprezentáló osztály.</li>
 *     <li>{@link Gomba} - A gomba osztály.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test38 extends TestCase implements ITestCase {
    // Teszt attribútumok
    /** A tektonokra ható hatások. */
    TektonHatas th1;
    TektonHatas th2;
    TektonHatas th3;
    TektonHatas th4;

    /** A tesztben szereplő tektonok. */
    Tekton t1;
    Tekton t2;
    Tekton t3;
    Tekton t4;

    /** A tesztben szereplő gombafonalak. */
    Gombafonal gf1;
    Gombafonal gf2;
    Gombafonal gf3;

    /** A teszt során vizsgált gomba. */
    Gomba g;

    /** A gomba gombatestének állapota. */
    GombaTest gt;

    /**
     * Létrehoz egy új {@code Test38} objektumot, amely inicializálja a tektonokat, gombafonalakat és a gombát.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló és log-oló objektum
     */
    public Test38(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely a gombafonalak folytonosságát vizsgálja.
     * Ha nincs fonalszakadás, akkor a teszt sikeres.
     */
    @Override
    public void runTest() {
        CallTracer.enter("fonalFolytonossagVizsgalat", "Gomba", "");
        if(g.fonalFolytonossagVizsgalat() == null) {
            CallTracer.exit("fonalFolytonossagVizsgalat", "null");
        } else {
            CallTracer.exit("fonalFolytonossagVizsgalat", "HIBA");
        }
        reset();
    }

    private void reset(){
        th1 = new TektonHatas();
        th2 = new TektonHatas();
        th3 = new TektonHatas();
        th4 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        t3 = new Tekton(th3);
        t4 = new Tekton(th4);

        // Tektonok összekapcsolása
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
        t2.addSzomszedosTekton(t3);
        t3.addSzomszedosTekton(t2);
        t3.addSzomszedosTekton(t4);
        t4.addSzomszedosTekton(t3);
        g =new Gomba(t1);
        gt = new GombaTest(g, 3);
        gt.setAlapGomba(g);
        g.setGombaTest(gt);

        // Gombafonalak létrehozása és kapcsolása
        gf1 = new Gombafonal(t1, t2);
        gf2= new Gombafonal(t2, t3);
        gf3 = new Gombafonal(t3, t4);
        gf1.setAlapGomba(g);
        gf2.setAlapGomba(g);
        gf3.setAlapGomba(g);

        t1.addKapcsolodoFonalak(gf1);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf1);
        t2.increaseFokszam();
        t2.addKapcsolodoFonalak(gf2);
        t2.increaseFokszam();
        t3.addKapcsolodoFonalak(gf2);
        t3.increaseFokszam();
        t3.addKapcsolodoFonalak(gf3);
        t3.increaseFokszam();
        t4.addKapcsolodoFonalak(gf3);
        t4.increaseFokszam();

        g.addFonal(gf1);
        g.addFonal(gf2);
        g.addFonal(gf3);
    }
}
