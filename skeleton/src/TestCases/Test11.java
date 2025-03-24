package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test11} osztály a 11. szkeleton tesztesetet implementálja.
 *
 * <p><b>11: Tekton Hatás Kifejtés: FonalFelszivodoHatas teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A {@link Tekton} hatás kifejtésének tesztelése {@link FonalFelszivodoHatas} objektummal.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Előzmények: {@code FonalFelszivodoHatas} típusú hatással rendelkező {@code Tekton} létrehozása.</li>
 *     <li>Legalább két szomszédos {@code Tekton} létrehozása, kapcsolódó fonalakkal.</li>
 *     <li>{@code Gomba} létrehozása valamelyik {@code Tekton}-on.</li>
 *     <li>Hatás kifejtése a {@code Tekton}-on.</li>
 *     <li>Fonalak felszívása a szomszédos {@code Tekton}-okon.</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link FonalFelszivodoHatas} - A Tektonhoz kapcsolódó fonalakat felszívó hatás.</li>
 *     <li>{@link TektonHatas} - Általános Tekton hatás.</li>
 *     <li>{@link Gombafonal} - A gombafonal objektum, amely összeköti a Tektonokat.</li>
 *     <li>{@link Gomba} - A gomba objektum, amely egy Tektonon helyezkedik el.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-22
 */
public class Test11 extends TestCase implements ITestCase {
    /** Az első Tekton objektum. */
    Tekton t1;

    /** A FonalFelszivodoHatas típusú hatás. */
    FonalFelszivodoHatas h1;

    /** A gombafonal objektum. */
    Gombafonal gf;

    /** A gomba objektum. */
    Gomba g;

    /** A második Tekton objektum. */
    Tekton t2;

    /** Egy általános TektonHatas objektum. */
    TektonHatas h2;

    /**
     * Létrehoz egy új {@code Test11} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test11(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Tekton fonal felszívó hatáskifejtését teszteljük.
     */
    @Override
    public void runTest() {
        CallTracer.enter("hatasKifejtes", "Tekton", "");
        String hatas = t1.hatasKifejtes();
        if (hatas.equals("Felszivas")) {
            CallTracer.exit("hatasKifejtes", hatas);
        } else {
            CallTracer.exit("hatasKifejtes", "HIBA");
        }
        reset();
    }

    private void reset() {
        h1 = new FonalFelszivodoHatas();
        t1 = new Tekton(h1);
        h1.setTekton(t1);
        g = new Gomba(t1);
        t1.setGomba(g);

        h2 = new TektonHatas();
        t2 = new Tekton(h2);
        h2.setTekton(t2);

        gf = new Gombafonal(t1, t2);
        gf.setAlapGomba(g);
        t1.addKapcsolodoFonalak(gf);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf);
        t2.increaseFokszam();
    }
}
