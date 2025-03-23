package TestCases;

import CallTracer.CallTracer;
import FungoriumClasses.*;
import java.util.*;
/**
 * A {@code Test23} osztály a 23. szkeleton tesztesetet implementálja.
 *
 * <p><b>23: Játék Kiértékelés Teszt</b></p>
 *
 * <p><b>Rövid leírás:</b><br>
 * A teszteset a játék végkimenetelét ellenőrzi, hány pontja van a játékosoknak. </p>
 *
 * <p><b>Aktorok:</b><br>
 * Tesztelő, Skeleton</p>
 *
 * <p><b>Forgatókönyv:</b></p>
 * <ol>
 *          <li>Kiértékelés hívása a Fieldből</li>
 *          <li>A kiértékelés minden játékosra megtörténik</li>
 *          <li>A Rovarasz minden rovarjának összegezzük a tápanyagát, ezt a végső összeget rögzítjük a Rovarasz score-jában.</li>
 *          <li>A Gombász score-ja egyből kiolvasható, mert ennek növelése a GombaTestjeinek létrehozásakor automatikusan megtörténik.</li>
 *          <li>A Rovarasz score-ja az összegzés után kiolvasható</li>
 *          <li>Visszaadjuk a legnagyobb értékkel rendelkező Gombászt és Rovarászt mint győztesek.</li>
 *          <li>Döntetlen kezelése</li>
 *          <li>Döntés: A Döntetlen előfordulása:</li>
 *          <li>        1. igen</li>
 *          <li>        2. nem</li>
 *          <li>Ha igen, akkor több játékost kapunk vissza</li>
 *          <li>Ha nem akkor csak 1-1 játékost kapunk vissza</li>
 * </ol>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *     <li>{@link Tekton} - A teszt során használt Tekton objektum.</li>
 *     <li>{@link Field} - A teszt során használt Field objektum.</li>
 *     <li>{@link Rovarasz} - A teszt során használt Rovarasz objektum.</li>
 *     <li>{@link Rovar} - A teszt során használt Rovar objektum.</li>
 *     <li>{@link Gombasz} - A teszt során használt Gombasz objektum.</li>
 *     <li>{@link TektonHatas} - A teszt során használt TektonHatas objektum.</li>
 * </ul>
 *
 * @author Kozma Szabolcs
 * @version 1.0
 * @since 2025-03-22
 */

public class Test23 extends TestCase implements ITestCase {
    //Test attributes
    /** A tesztben használt Field*/
    Field f;
    /** A tesztben használt Rovarász*/
    Rovarasz rsz;
    /** A tesztben használt Rovar*/
    Rovar r;
    /** A tesztben használt Gombasz*/
    Gombasz gsz;
    /** A tesztben használt TektonHatas*/
    TektonHatas th;
    /** A tesztben használt Tekton*/
    Tekton t;

    //Test init
    /**
     * Létrehoz egy új {@code Test23} objektumot, amely inicializálja a szükséges objektumokat a teszthez.
     *
     * @param callTracer A híváskövető és logoló objektum.
     */
    public Test23(CallTracer callTracer) {
        super(callTracer);
        f = new Field();
        rsz = new Rovarasz();
        r = new Rovar();
        gsz = new Gombasz();
        th = new TektonHatas();
        t = new Tekton(th);
        rsz.addRovar(r, t);
    }

    /**
     * Végrehajtja a TestCase-hez tartozó tesztesetet, amely a játék kiértékelését szimulálja
     */
    @Override
    public void runTest() {
        CallTracer.enter("kiertekeles", "Field", "");
        f.kiertekeles();
        CallTracer.exit("kiertekeles", "gyoztesJatekosok");
    }
}
