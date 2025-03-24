package TestCases;
import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;


/**
 * A {@code Test1} osztály az első szkeleton tesztesetet implementálja, 1: Játék Indítás Teszt - Tekton inicializálás teszt megvalósítása.<p>
 *
 * Leírás: A Tektonok létrejöttének folyamatának ellenőrzése.<p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *     <li>Tektonok létrehozása</li>
 *     <li>Hatások létrehozása</li>
 *     <li>Hatások Tektonokhoz rendelése</li>
 *     <li>Szomszédos Tektonok beállítása</li>
 *     <li>Fieldhez rendelés</li>
 * </ol>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 **/

public class Test1 extends TestCase implements ITestCase{
    /** A tesztben szereplő mező */
    Field f;
    /** Az első Tekton példány */
    Tekton t1;
    /** A második Tekton példány */
    Tekton t2;
    /** A harmadik Tekton példány */
    Tekton t3;
    /** A negyedik Tekton példány */
    Tekton t4;

    /** Az első hatás (Hatás a Fonal felszívódása) */
    FonalFelszivodoHatas th1;
    /** A második hatás (Hatás a Gomba test gátlására) */
    FonalGatloHatas th2;
    /** A harmadik hatás (Hatás a Gomba test gátlására) */
    GombaTestGatloHatas th3;
    /** A negyedik hatás (Alap hatás)*/
    TektonHatas th4;

    /**
     * Létrehoz egy új {@code Test1} objektumot, inicializálja a szükséges objektumokat.
     *
     * @param callTracer a hívásokat követő és logoló objektum.
     */
    public Test1(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a tesztesetet, amely ellenőrzi a Tektonok inicializálását,
     * hozzáadását a mezőhöz és a szomszédsági kapcsolatok beállítását.
     */
    @Override
    public void runTest() {
        CallTracer.enter("addTekton", "Field", "t1");
        f.addTekton(t1);
        CallTracer.exit("addTekton", "");
        CallTracer.enter("addTekton", "Field", "t2");
        f.addTekton(t2);
        CallTracer.exit("addTekton", "");
        CallTracer.enter("addTekton", "Field", "t3");
        f.addTekton(t3);
        CallTracer.exit("addTekton", "");
        CallTracer.enter("addTekton", "Field", "t4");
        f.addTekton(t4);
        CallTracer.exit("addTekton", "");

        CallTracer.enter("setAllTektonSzomszed", "Field", "");
        f.setAllTektonSzomszed();
        CallTracer.exit("setAllTektonSzomszed", "");
        reset();
    }

    private void reset() {
        f = new Field();
        th1 = new FonalFelszivodoHatas();
        th2 = new FonalGatloHatas();
        th3 = new GombaTestGatloHatas();
        th4 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        t3 = new Tekton(th3);
        t4 = new Tekton(th4);
    }
}
