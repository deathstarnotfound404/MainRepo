package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test37} osztály a 37.szkeleton tesztesetet implementálja.
 *
 * <p><b>37: Fonal Vásárlás Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset ellenőrzi a Gombász által kezdeményezhető Fonal Vásárlás tevékenység hívási láncolatát.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Fonal vásárlás esetén csökkentjük az adott GombaTest Spóra készletét.</li>
 *     <li>1 spóráért 3 fonalat kapunk a GombaTest fonal készletébe.</li>
 *     <li>Döntés: Van-e spórakészlete (min 1) a Gombásznak:</li>
 *         <ol>
 *             <li>Van minimum 1</li>
 *             <li>Nincs 1 sem</li>
 *         </ol>
 *     <li>Ha nincs, akkor a decrease SporaKeszlet nem hajtódik végre és false-al tér vissza. Ekkor terminálunk false-al.</li>
 *     <li>Ha van, akkor a spóra készlet csökkentése megtörténik 1-el és ezt követően a fonal készlet növelése 3-al. Ekkor terminálunk true-val.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a fonal vásárlását végzi.</li>
 *     <li>{@link GombaTest} - A gomba állapotát és készleteit tároló osztály.</li>
 *     <li>{@link Tekton} - Tekton.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test37 extends TestCase implements ITestCase {
    // Test attributes
    /** A tesztben szereplő hatás. */
    private TektonHatas h;
    /** A tesztben szereplő tekton. */
    private Tekton t;
    /** A tesztben szereplő gombász. */
    private Gombasz gsz;
    /** A tesztben szereplő gomba. */
    private Gomba g;
    /** A tesztben szereplő GombaTest. */
    private GombaTest gt;

    /**
     * Létrehoz egy új {@code Test37} objektumot, amely inicializálja a szükséges tesztadatokat.
     *
     * @param callTracer a függvényhívások nyomon követésére és logolásra szolgáló objektum
     */
    public Test37(CallTracer callTracer) {
        super(callTracer);
        //A kommunikációs diagramm sorszámozáa alapján:
        h = new TektonHatas();          //1;
        t = new Tekton(h);              //2;
        h.setTekton(t);                 //3;
        gsz = new Gombasz();            //4;
        g = new Gomba(t);               //5;
        gt = new GombaTest(g, 0);  //6;
        gt.setAlapGomba(g);             //7;
        t.setGomba(g);                  //8;
        g.setGombaTest(gt);
    }

    /**
     * Végrehajtja a tesztesetet, amely során a fonal vásárlás mechanizmusát ellenőrizzük.
     */
    @Override
    public void runTest() {
        int choice = makeDecision("Van-e minimum 1 spórakészlete a Gombásznak?", Arrays.asList("Van minimum 1", "Nincs 1 sem"));

        switch (choice) {
            case 1:
                gt.addToSporaKeszlet(1);
                CallTracer.enter("fonalVasarlas", "Gombasz", "g");
                boolean val_1 = gsz.fonalVasarlas(this.g);       //Ezt a g gombát majd a játékos választja ki.
                if (val_1) {
                    CallTracer.exit("fonalVasarlas", "true");
                } else {
                    CallTracer.exit("fonalVasarlas", "HIBA");
                }
                break;

            case 2:
                CallTracer.enter("fonalVasarlas", "Gombasz", "g");
                boolean val_2 = gsz.fonalVasarlas(g);       //Ezt a g gombát majd a játékos választja ki.
                if(val_2) {
                    CallTracer.exit("fonalVasarlas", "HIBA");
                } else {
                    CallTracer.exit("fonalVasarlas", "false");
                }
                break;

            default:
                break;
        }
    }
}
