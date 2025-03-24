package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test18} osztály a 18. szkeleton tesztesetet implementálja.
 *
 * <p><b>18: Spóra Elfogyasztás - GyorsitoSpora teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset ellenőrzi a hívási láncot a {@link GyorsitoSpora} típusú spóra elfogyasztása esetén</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Spora evés elvégzése</li>
 *          <li>Rovar evés hatékonyságának megállapítása</li>
 *          <li>Evés hatékonyság függvényében, valamint a tektonon található spórák alapján meghatározzuk az elfogyasztandó spórák mennyiségét az adott Tektonon</li>
 *          <li>Elfogyasztott mennyiség regisztrálása a Rovarban, Elfogyasztott Spórák törlése a Tektonon</li>
 *          <li>Utoljára elfogyasztott Spóra hatásánk kifejtése a Rovar, ami jelen esetben GyorsitoSpora</li>
 *          <li>evesHatekonysag 1-re állítása</li>
 *          <li>maxFogyasztás true-ra állítása</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 *     <li>{@link GyorsitoSpora} - A teszt során használt GyorsitoSpora objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test18 extends TestCase implements ITestCase {
    //Test attributes
    /** A teszt során használt Rovar*/
    Rovar r;
    /** A GyorsítóSpóra amit megeszik*/
    GyorsitoSpora s1;
    /** A Tekton hatása*/
    TektonHatas th1;
    /** A Tekton*/
    Tekton t1;

    //Test init
    /**
     * Létrehoz egy új {@code Test18} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test18(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a GyorsítóSpóra megevését szimulálja.
     */
    @Override
    public void runTest() {
        CallTracer.enter("sporaEves", "Rovar", "");
        r.sporaEves();
        CallTracer.exit("sporaEves", "");
        reset();
    }

    private void reset() {
        r = new Rovar();
        s1 = new GyorsitoSpora();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);
        t1.addSpora(s1);
        r.setHelyzet(t1);
    }
}
