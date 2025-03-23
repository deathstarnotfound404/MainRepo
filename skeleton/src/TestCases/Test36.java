package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test36} osztály a 36. szkeleton tesztesetet implementálja.
 *
 * <p><b>36: Gombatest Növesztés</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset ellenőrzi a gombatest növesztés helyes lefutását.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Ellenőrizzük, hogy a tektonon már van-e gombatest (nincs).</li>
 *     <li>Ellenőrizzük, hogy van-e minimum 5 Spóra a Tektonon.</li>
 *     <li>Döntés: Van 5 spóra a Tektonon:</li>
 *         <ol>
 *             <li>Igen</li>
 *             <li>Nem</li>
 *         </ol>
 *     <li>Ha nincs, akkor a teszt false-al terminál.</li>
 *     <li>Ha van legalább 5, akkor:
 *         <ul>
 *             <li>A Tekton listájából törlünk minden Spórát.</li>
 *             <li>Létrehozzuk a gombatestet 3 Spóra költséggel.</li>
 *             <li>A maradék spórák számával inicializáljuk a GombaTest kezdő spóra készletét.</li>
 *             <li>A gombász score-ját növeljük 1-el.</li>
 *             <li>A Tektonon beállítjuk, hogy már van GombaTest.</li>
 *             <li>Terminálunk true-val.</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Gombasz} - A tesztelt objektum, amely a gombatest növesztését végzi.</li>
 *     <li>{@link GombaTest} - A gombatest állapotát és készleteit tároló osztály.</li>
 *     <li>{@link Tekton} - Tekton.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-21
 */
public class Test36 extends TestCase implements ITestCase {
    /**
     * A Teszt attribútumai
     */
    Gombasz gsz;
    Gomba g;
    GombaTest gt;
    Tekton t_alap;
    TektonHatas h;
    Spora s1;
    Spora s2;
    Spora s3;
    Spora s4;
    Spora s5;
    Tekton t1;


    /**
     * Létrehoz egy új {@code Test36} objektumot, amely inicializálja a szükséges tesztadatokat.
     *
     * @param callTracer a függvényhívások nyomon követésére szolgáló objektum
     */
    public Test36(CallTracer callTracer) {
        super(callTracer);
        gsz = new Gombasz();            //1;
        h = new TektonHatas();          //2;
        t_alap = new Tekton(h);             //3;
        g = new Gomba(t_alap);              //4
        s1 = new Spora();               //5;
        s2 = new Spora();               //6;
        s3 = new Spora();               //7;
        s4 = new Spora();               //8;
        s5 = new Spora();               //9;
        gt = new GombaTest(g, 2);   //10
        g.setGombaTest(gt);             //11;
        gsz.addGomba(g);                //12
        t_alap.addSpora(s1);                //13
        t_alap.addSpora(s2);                //14
        t_alap.addSpora(s3);                //15
        t_alap.addSpora(s4);                //16
        t_alap.addSpora(s5);                //17
        gt.setAlapGomba(g);
        t_alap.setGomba(g);
        t1 = new Tekton(h);

    }

    /**
     * Végrehajtja a tesztesetet, amely során a gombatest növesztésének mechanizmusát ellenőrizzük.
     */
    @Override
    public void runTest() {
        CallTracer.enter("gombatestNovesztes", "Gombasz", "t1");
        gsz.gombatestNovesztes(t1);     //A felhasználó választja ki az adott t1-et
        CallTracer.exit("gombatestNovesztes", "true");
    }
}
