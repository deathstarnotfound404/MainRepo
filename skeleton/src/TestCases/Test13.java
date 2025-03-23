package TestCases;
import CallTracer.*;
import FungoriumClasses.*;
import java.util.Arrays;

/**
 * A {@code Test13} osztály a 13. szkeleton tesztesetet implementálja.
 *
 * <p><b>13: Tekton Hatás Kifejtés: FonalGatloHatas teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A {@link Tekton} hatás kifejtésének tesztelése {@link FonalGatloHatas} objektummal.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Hatás kifejtése a {@code Tekton}-on.</li>
 *     <li>Interakció a felhasználóval:</li>
 *     <ul>
 *         <li>Döntés: A Tektonon már van fonal?</li>
 *         <li>1. Igen - a gátló hatás érvényesül, nem növeszthető több fonal.</li>
 *         <li>2. Nem - a gátló hatás nem érvényesül, lehet még egy fonalat növeszteni.</li>
 *     </ul>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link FonalGatloHatas} - A Tektonhoz kapcsolódó fonalnövekedést gátló hatás.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-22
 */
public class Test13 extends TestCase implements ITestCase{
    /** A FonalGatloHatas típusú hatás. */
    FonalGatloHatas h;

    /** Tekton objektum. */
    Tekton t1;


    /**
     * Létrehoz egy új {@code Test13} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test13(CallTracer callTracer) {
        super(callTracer);
        h = new FonalGatloHatas();
        t1 = new Tekton(h);
        h.setTekton(t1);
    }

    /**
     * Végrehajtja a tesztesetet, amely során a Tekton fonal gátló hatáskifejtését teszteljük.
     */
    @Override
    public void runTest() {
        int choice = makeDecision("Mekkora a t1-es tekton fonal fokszáma?", Arrays.asList("fonalFokszam > 0", "fonalFokszam == 0"));
        switch (choice) {
            case 1:
                t1.setFokszam(3);
                CallTracer.enter("hatasKifejtes", "Tekton", "");
                String hatas1 = t1.hatasKifejtes();
                if (hatas1.equals("FonalGatlo")) {
                    CallTracer.exit("hatasKifejtes", hatas1);
                } else {
                    CallTracer.exit("hatasKifejtes", "HIBA");
                }
                break;
            case 2:
                t1.setFokszam(0);
                CallTracer.enter("hatasKifejtes", "Tekton", "");
                String hatas2 = t1.hatasKifejtes();
                if (hatas2.equals("NincsFonalGatlo")) {
                    CallTracer.exit("hatasKifejtes", hatas2);
                } else {
                    CallTracer.exit("hatasKifejtes", "HIBA");
                }
                break;
            default:
                break;
        }
    }
}
