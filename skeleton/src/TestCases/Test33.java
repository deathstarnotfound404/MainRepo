package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test33} osztály a 33. szkeleton tesztesetet implementálja, amely a Gombafonal Irányítás Teszt - Cél Tektonon van Spóra szekvencia tesztelése.
 * <p>
 * Rövid leírás:
 *     Ekkor +1 fonal lerakható, rendszer bekér kontrollertől még egy t3-at újabb célnak, folytatólagosan az előző fonal folytatásának
 * <p>
 * Forgatókönyv:<p>
 *      1; Ha a céltektonon van elszórt Spóra, akkor a Gombász az először lerakott fonalat meghosszabbíthatja még 1 fonallal, a fonalkészlet felhasználása nélkül (növekedés gyorsítás)<p>
 *      2; Ekkor a kiinduló tekton adott, és ennek függvényében választható meg a céltekton<p>
 *      3; Gombafonal létrehozása<p>
 *      4; Gombafonal felvétel a Tektonokon és fokszámok növelése<p>
 *      5; Fonal felvétele a Gombán<p>
 *      6; A Gombász fonalkészletét ekkor NEM csökkentjük.<p>
 * <p>
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */

public class Test33 extends TestCase implements ITestCase {
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
    public Test33(CallTracer callTracer) {
        super(callTracer);
        gsz = new Gombasz();            //1
        th1 = new TektonHatas();        //2
        th2 = new TektonHatas();        //3
        t1 = new Tekton(th1);           //4
        t2 = new Tekton(th2);           //5
        g = new Gomba(t1);              //6
        t1.addSzomszedosTekton(t2);
        t1.setGomba(g);
        t2.addSzomszedosTekton(t1);
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
        CallTracer.enter("gombafonalIranyitas", "Gombasz", "t1, t2");
        if (gsz.gombafonalIranyitas(t1, t2, true)) {    //előre tudjuk, hogy t2-n van spóra, ezért állítjuk true-ra
            CallTracer.exit("gombafonalIranyitas", "true");
        } else {
            CallTracer.exit("gombafonalIranyitas", "HIBA");
        }
    }
}
