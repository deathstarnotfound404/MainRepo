package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test19} osztály a 19. szkeleton tesztesetet implementálja.
 *
 * <p><b>19: Spóra Elfogyasztás - LassitoSpora teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset ellenőrzi a hívási láncot a {@link LassitoSpora} típusú spóra elfogyasztása esetén.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Spora evés elvégzése</li>
 *          <li>Rovar evés hatékonyságának megállapítása</li>
 *          <li>Evéshatékonyság függvényében, valamint a tektonon található spórák alapján meghatározzuk az elfogyasztandó spórák mennyiségét az adott Tektonon</li>
 *          <li>Elfogyasztott mennyiség regisztrálása a Rovarban, Elfogyasztott Spórák törlése a Tektonon</li>
 *          <li>Utoljára elfogyasztott Spóra hatásánk kifejtése a Rovar, ami jelen esetben LassitoSpora</li>
 *          <li>evesHatekonyság 0.25-re állítása</li>
 *          <li>maxFogyasztas false-ra állítása</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 *     <li>{@link LassitoSpora} - A teszt során használt LassitoSpora objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test19 extends TestCase implements ITestCase {
    //Test attributes
    /** A használt Rovar*/
    Rovar r;
    /** A megvendő LassítóSpóra*/
    LassitoSpora s1;
    /** A Tektonon a hatás*/
    TektonHatas th1;
    /** A Tekton*/
    Tekton t1;

    //Test init
    /**
     * Létrehoz egy új {@code Test19} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test19(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a LassitoSpora elfogyasztását szimulálja
     */
    @Override
    public void runTest() {
        CallTracer.enter("sporaEves", "Rovar", "");
        r.sporaEves();
        CallTracer.exit("sporaEves", "");
        reset();
    }

    private void reset(){
        r = new Rovar();
        s1 = new LassitoSpora();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);
        t1.addSpora(s1);
        r.setHelyzet(t1);
    }
}
