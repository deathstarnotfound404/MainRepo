package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;

/**
 * A {@code Test24} osztály a 24. szkeleton tesztesetet implementálja.
 *
 * <p><b>24: Gombafonal Elvágás Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset ellenőrzi a {@link Gombafonal} elvágásának helyes lefutását.</p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Döntés: A rovar képes-e fonalat vágni, tudVagni = true:</li>
 *          <li>1. igen</li>
 *          <li>2. nem</li>
 *          <li>Ha nem akkor a fonal elvágás nem hajtódik végre</li>
 *          <li>Ha igen, akkor a fonal elvágása végrehajtódik</li>
 *          <li>Az adott fonalat töröljük minden tektonról mint kapcsolódó fonal</li>
 *          <li>Az adott fonalat kitöröljük a Gombájából</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link Rovarasz} - A teszt során használt Rovarasz objektum.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 *     <li>{@link Gombafonal} - A teszt során használt Gombafonal objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 *     <li>{@link Gomba} - A teszt során használt Gomba objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test24 extends TestCase implements ITestCase {
    //Test attributes
    /** A teszt során használt Rovarasz*/
    Rovarasz rsz;
    /** A teszt során használt Rovar*/
    Rovar r;
    /** A teszt során használt Gombafonal*/
    Gombafonal gf;
    /** A teszt során használt egyik Tekton*/
    Tekton t1;
    /** A teszt során használt másik Tekton*/
    Tekton t2;
    /** A teszt során használt egyik TektonHatas*/
    TektonHatas th1;
    /** A teszt során használt másik TektonHatas*/
    TektonHatas th2;
    /** A teszt során használt Gomba*/
    Gomba g;

    //Test init
    /**
     * Létrehoz egy új {@code Test24} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test24(CallTracer callTracer) {
        super(callTracer);
        rsz = new Rovarasz();
        r = new Rovar();
        th1 = new TektonHatas();
        th2 = new TektonHatas();
        t1 = new Tekton(th1);
        t2 = new Tekton(th2);
        gf = new Gombafonal(t1, t2);
        g = new Gomba(t1);
        rsz.addRovar(r, t2);
        r.setHelyzet(t2);
        t2.setRovar(r);
        gf.setAlapGomba(g);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a Gombafonal elvágását szimulálja
     */
    @Override
    public void runTest() {
        CallTracer.enter("fonalVagas", "Rovarasz", "r, gf");
        rsz.fonalVagas(r,gf);
        CallTracer.exit("fonalVagas", "");
    }
}
