package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test29} osztály a 29. szkeleton tesztesetet implementálja, Spóra Szórás Teszt szzekvenciája.<p>
 *
 * Spora létrehozása, gombatestből való szórása másik céltektonra.<p>
 *
 * Szomszédos tektont kapott célnak és van elég Spórája a GombaTestnek a művelet elvégzéséhez
 * A Gombatest  szintjének megfelelő mennyiségű spórát tud szórni, és szintnek megfelelően szomszéd szomszédra is szórhat adott esetben
 * Döntés: A Gombatest Szintje:
 *   1. 1. szint
 *   2. 2. szint
 *   3. 3. szint
 * Ha 1. szintű, akkor 2 spóra a szórás ára, és 1 spórát szórhat a szomszédos tektonra.
 * Ha 2.szintű, akkor 1 spóra a szórás ára, és 2 spórát szórhat a szomszédos tektonra.
 * Ha 3.szintű, akkor 0 spóra a szórás ára, és 3 spórát szórhat a szomszédos vagy szomszéd szomszédos tektonjára.
 * A szórás növeli a gombatest szórásCount-ját
 * A szórás létrehozza a Spora objektumokat
 * A létrehozott Sporákat beállítja az adott tekton spóra listájában
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */

public class Test29 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    TektonHatas th1;
    TektonHatas th2;
    Gombasz gsz;
    Field f;
    Tekton t1;
    Tekton celTekton;
    Gomba g;
    GombaTest gt;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
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
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
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
