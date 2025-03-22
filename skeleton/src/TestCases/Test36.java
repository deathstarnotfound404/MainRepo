package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test36} osztály a 36. szkeleton tesztesetet implementálja.
 *
 * <p>Az osztály tartalmazza a ... (attribútumok felsorolása).
 * Lehetőséget biztosít az /num/. teszteset végrehajtására ami ... (mit csinál).</p>
 *
 * <p>Kapcsolódó osztályok:
 * {@link Osztaly} - ... (funkcio: pl: EGy gombafonalhoz tartozó start és céltekton).</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2025-03-18
 */

public class Test36 extends TestCase implements ITestCase {
    //Test attributes
    /** Javadoc, attributumok leirasa. */
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


    //Test init
    /**
     * Létrehoz egy új {@code Test1} objektumot.
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
     * Végrehajtja a TestCase-hez tartozó tesztesetet.
     */
    @Override
    public void runTest() {
        CallTracer.enter("gombatestNovesztes", "Gombasz", "t1");
        gsz.gombatestNovesztes(t1);     //A felhasználó választja ki az adott t1-et
        CallTracer.exit("gombatestNovesztes", "true");
    }
}
