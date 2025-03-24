package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;

/**
 * A {@code Test26} osztály a 26. szkeleton tesztesetet implementálja.
 *
 * <p><b>26: Rovar Irány Megadás Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * Kiválasztott céltekton fonallal összekötött start tektonnal, céltektonon nincs Rovar</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Rovarász irányítja a rovart</li>
 *          <li>Rovar lépés megkezdése</li>
 *          <li>szomszédos tektont kapott célnak, ahol nincs rovar</li>
 *          <li>A lépést elvégezzük</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 *     <li>{@link Rovarasz} - A teszt során használt Rovarasz objektum.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 *     <li>{@link Gombafonal} - A teszt során használt Gombafonal objektum.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-22
 */

public class Test26 extends TestCase implements ITestCase {
    //Test attributes
    /** A tesztben használt egyik TektonHatas*/
    private TektonHatas th1;
    /** A tesztben használt másik TektonHatas*/
    private TektonHatas th2;
    /** A tesztben használt Rovarasz*/
    private Rovarasz rsz;
    /** A tesztben használt Rovar*/
    private Rovar r;
    /** A tesztben használt egyik Tekton*/
    private Tekton t1;
    /** A tesztben használt másik Tekton*/
    private Tekton t2;
    /** A tesztben használt Gombafonal*/
    private Gombafonal gf;

    //Test init
    /**
     * Létrehoz egy új {@code Test26} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test26(CallTracer callTracer) {
        super(callTracer);
        reset();
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a rovar irány megadást teszteli
     */
    @Override
    public void runTest() {
        CallTracer.enter("rovarIranyitas", "Rovarasz", "r, t2");
        if(rsz.rovarIranyitas(r, t2)) {
            CallTracer.exit("rovarIranyitas", "true");
        } else {
            CallTracer.exit("rovarIranyitas", "HIBA");
        }
        reset();
    }

    public void reset() {
        th1 = new TektonHatas(); //1;
        th2 = new TektonHatas();
        rsz = new Rovarasz();   //2
        r = new Rovar();        //3
        t1 = new Tekton(th2);   //4
        t2 = new Tekton(th1);   //5
        th1.setTekton(t1);
        th2.setTekton(t2);
        gf = new Gombafonal(t1, t2);    //6
        rsz.addRovar(r, t1);    //7 - de kommunikációs diagrammhoz képest változott
        t1.addKapcsolodoFonalak(gf);
        t1.increaseFokszam();
        t2.addKapcsolodoFonalak(gf);
        t2.increaseFokszam();
        t1.setRovar(r);  //12    implicit állítja a 13-at   Kommunikációsokon pont fordítva vannak felvéve a rovar és üres tektonok
        t2.setRovar(null); //14        implicit állitja a 15-öt
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
    }
}
