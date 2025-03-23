package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test20} osztály a 20. szkeleton tesztesetet implementálja.
 *
 * <p><b>20: Spóra Elfogyasztás - BenitoSpora teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset ellenőrzi a hívási láncot a {@link BenitoSpora} típusú spóra elfogyasztása esetén.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *           <li>Spora evés elvégzése</li>
 *           <li>Rovar evés hatékonyságának megállapítása</li>
 *           <li>Evéshatékonyság függvényében, valamint a tektonon található spórák alapján meghatározzuk az elfogyasztandó spórák mennyiségét az adott Tektonon</li>
 *           <li>Elfogyasztott mennyiség regisztrálása a Rovarban, Elfogyasztott Spórák törlése a Tektonon</li>
 *           <li>Utoljára elfogyasztott Spóra hatásánk kifejtése a Rovar, ami jelen esetben LassitoSpora</li>
 *           <li>evesHatekonyság 0-ra állítása</li>
 *           <li>maxFogyasztas false-ra állítása</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link BenitoSpora} - A teszt során használt BenitoSpora objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test20 extends TestCase implements ITestCase {
    //Test attributes
    /** A teszt során használt Rovar*/
    Rovar r;
    /** A megevendő BenitoSpora*/
    BenitoSpora s1;
    /** A Tektonon uralkodó hatás*/
    TektonHatas th1;
    /** A Tekton*/
    Tekton t1;

    //Test init
    /**
     * Létrehoz egy új {@code Test20} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test20(CallTracer callTracer) {
        super(callTracer);
        r = new Rovar();
        s1 = new BenitoSpora();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);
        t1.addSpora(s1);
        r.setHelyzet(t1);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a BenitoSpora elfogyasztasat szimulalja
     */
    @Override
    public void runTest() {
        CallTracer.enter("sporaEves", "Rovar", "");
        r.sporaEves();
        CallTracer.exit("sporaEves", "");
    }
}
