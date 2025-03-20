package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test37} osztály a 37. szkeleton tesztesetet implementálja.
 * <p>
 *     Az osztály tartalmazza a 37. teszteset Fonal Vásárlás Teszt végrehajtását.
 *     A teszteset ellenőrzi a Gombász által kezdeményezhető Fonal Vásárlás tevékenység hívási láncolatát.
 * <p>
 *      1; Fonal vásárlás esetén csökkentjük az adott GombaTest Spóra készletét <p>
 *      2; 1 spóráért 3 fonalat kapunk a GombaTest fonal készletébe.<p>
 *      3; Döntés: Van-e spórakészlete a (min 1) a Gombásznak:<p>
 *          1. Van minimum 1<p>
 *          2. Nincs 1 sem<p>
 *      4; Ha nincs akkor a decrease SporaKeszlet nem hajtódik végre és false-al tér vissza. Ekkor terminálunk false-al.<p>
 *      5; Ha van, akkor a spóra készlet csökkentése megtörténik 1-el és ezt követően a fonal készlet növelése 3-al. Ekkor terminálunk true-val.<p>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-20
 */

public class Test37 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
    TektonHatas h;
    Tekton t;
    Gombasz gsz;
    Gomba g;
    GombaTest gt;

    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
     */
    public Test37(CallTracer callTracer) {
        super(callTracer);
        callTracer.enter("Test37", "Test37", "");
        //A kommunikációs diagramm sorszámozáa alapján:
        h = new TektonHatas();          //1;
        t = new Tekton(h);              //2;
        h.setTekton(t);                 //3;
        gsz = new Gombasz();            //4;
        g = new Gomba(t);               //5;
        gt = new GombaTest(g, 0);  //6;
        gt.setAlapGomba(g);             //7;
        t.setGomba(g);                  //8;
        callTracer.exit("Test37", "");
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        int choice = makeDecision("Van-e minimum 1 spórakészlete a Gombásznak?", Arrays.asList("Van minimum 1", "Nincs 1 sem"));

        switch (choice) {
            case 1:
                gt.addToSporaKeszlet(10);
                callTracer.enter("addToSporaKeszlet", "GombaTest", "10");
                gsz.fonalVasarlas(g);       //Ezt a g gombát majd a játékos választja ki.
                callTracer.exit("addToSporaKeszlet", "");
                break;

            case 2:

                break;

            default:
                break;
        }
    }
}
