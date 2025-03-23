package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test2} osztály a második szkeleton tesztesetet implementálja:
 * <b>2: Játék Indítás Teszt - Rovarász inicializálás.</b>
 *
 * <p><b>Leírás:</b> A teszt során egy Rovarász és egy Rovar objektum létrehozását,
 * valamint a megfelelő hozzárendeléseket ellenőrizzük.</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Rovarász létrehozása</li>
 *     <li>Rovarász hozzárendelése a Fieldhez</li>
 *     <li>Rovar létrehozása</li>
 *     <li>Rovar Rovarászhoz rendelése</li>
 *     <li>Rovar helyzetének megadása</li>
 * </ol>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-21
 **/

public class Test2 extends TestCase implements ITestCase{
    /** A tesztben szereplő mező */
    Field f;
    /** A tesztben szereplő rovarász */
    Rovarasz rsz;
    /** A tesztben szereplő rovar */
    Rovar r;
    /** A tesztben szereplő Tekton */
    Tekton t1;
    /** A tesztben szereplő Tekton hatás */
    TektonHatas th1;

    /**
     * Létrehoz egy új {@code Test2} objektumot, inicializálja a szükséges objektumokat.
     *
     * @param callTracer a hívásokat követő és logoló objektum.
     */
    public Test2(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        rsz = new Rovarasz();
        r = new Rovar();
        th1 = new TektonHatas();
        t1 = new Tekton(th1);

    }

    /**
     * Végrehajtja a tesztesetet, amely a Rovarász és a Rovar inicializálását,
     * valamint azok megfelelő hozzárendeléseit ellenőrzi.
     */
    @Override
    public void runTest() {
        CallTracer.enter("setHelyzet", "Rovar", "t1");
        r.setHelyzet(t1);
        CallTracer.exit("setHelyzet", "");
        CallTracer.enter("addRovar", "Rovarasz", "r, t1");
        rsz.addRovar(r, t1);
        CallTracer.exit("addRovar", "");
        CallTracer.enter("addPlayer", "Field", "rsz");
        f.addPlayer(rsz);
        CallTracer.exit("addPlayer", "");
    }
}
