package FungoriumClasses;
import CallTracer.*;
import java.util.*;

/**
 * A {@code Gomba} osztály egy gombát reprezentál a játékban, amely fonalakat növeszthet,
 * spórákat termelhet és terjedhet más tektonokra.
 *
 * <p>A gombák fonalakon keresztül kapcsolódnak más tektonokhoz, és egy adott gombatesthez kötődnek.
 * A gombag gombatestje spórákat is szórhat a megfelelő feltételek teljesülése esetén.</p>
 *
 * <p>Kapcsolódó osztályok:</p>
 * <ul>
 *     <li>{@link Tekton} - A gomba ezen a tektonon helyezkedik el.</li>
 *     <li>{@link Gombafonal} - A gomba által növesztett fonalak.</li>
 *     <li>{@link GombaTest} - A gomba központi testét reprezentáló osztály.</li>
 *     <li>{@link CallTracer} - A program futásának nyomon követésére szolgáló osztály.</li>
 * </ul>
 *
 * @author Botos Dániel, Kozma Szabolcs, Czene Zsombor
 * @version 1.0
 * @since 2025-03-19
 */
public class Gomba implements IDestroyable {
    private Tekton tekton;
    private int fonalKeszlet = 0;
    private List<List<Gombafonal>> fonalLista;
    private GombaTest GombaTest;

    /**
     * Létrehoz egy új {@code Gomba} objektumot, amely egy adott tektonon helyezkedik el.
     *
     * @param t A tekton, amelyen a gomba elhelyezkedik.
     */
    public Gomba(Tekton t) {
        this.tekton = t;
        fonalLista = new ArrayList<List<Gombafonal>>();
        ArrayList<Gombafonal> l = new ArrayList<Gombafonal>();
        fonalLista.add(l);
    }

    // --- Getterek és Setterek ---

    /**
     * Beállítja a gomba aktuális tektonját.
     *
     * @param t Az új tekton, amelyre a gombát helyezzük.
     */
    public void setTekton(Tekton t) {
        this.tekton = t;
    }

    /**
     * Visszaadja a gomba aktuális tektonját.
     *
     * @return A tekton, amelyen a gomba elhelyezkedik.
     */
    public Tekton getTekton() {
        return this.tekton;
    }

    /**
     * Beállítja a gomba fonalkészletének mértékét.
     *
     * @param val Az új fonalkészlet értéke.
     */
    public void setFonalKeszlet(int val) {
        this.fonalKeszlet = val;
    }

    /**
     * Visszaadja a gomba fonalkészletének aktuális értékét.
     *
     * @return A gomba fonalkészlet értéke.
     */
    public int getFonalKeszlet() {
        return fonalKeszlet;
    }

    /**
     * Beállítja a gomba GombaTestét.
     *
     * @param gt A gomba GombaTeste.
     */
    public void setGombaTest(GombaTest gt) {
        this.GombaTest = gt;
    }

    /**
     * Visszaadja a gomba GombaTestét.
     *
     * @return A gomba GombaTeste.
     */
    public GombaTest getGombaTest() {
        return GombaTest;
    }

    /**
     * Visszaadja a gomba által növesztett fonalak listáját.
     *
     * @return A gomba fonallista struktúrája.
     */
    public List<List<Gombafonal>> getFonalLista() {
        return fonalLista;
    }

    // --- Gomba működése ---

    /**
     * A gomba spórákat termel a szintjétől függően.
     */
    public void sporaTermeles() {
        //TODO sekvencia szerint megírni
        CallTracer.enter("getSzint", "GombaTest", "");
        int szint = GombaTest.getSzint();
        CallTracer.exit("getSzint", "szint");
        switch (szint){
            case 1:
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "2");
                GombaTest.addToSporaKeszlet(2);
                CallTracer.exit("addToSporaKeszlet", "");
                break;
            case 2:
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "3");
                GombaTest.addToSporaKeszlet(3);
                CallTracer.exit("addToSporaKeszlet", "");
                break;
            case 3:
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "4");
                GombaTest.addToSporaKeszlet(4);
                CallTracer.exit("addToSporaKeszlet", "");
                break;
        }
    }

    /**
     * A gombák szintlépésének elvégzése.
     */
    public void gombatestSzintlepes() {
        //TODO sekvencia szerint megírni
    }

    /**
     * Ellenőrzi, hogy a gombafonalak folytonosak-e az alapgombához.
     *
     * @return A folytonosságot megszakító fonalak listája, vagy null, ha minden fonal folytonos.
     */
    public List<Gombafonal> fonalFolytonossagVizsgalat() {
        //Fonalak folytonosak-e t1-ig, el lehet e jutni az alapgombából t1-ig
        //Az üzleti logika alapján döntjük majd el.
        //return false, ha nem folytonos
        List<Gombafonal> listOfDisconnectedFonalak = new ArrayList<Gombafonal>();
        for(List<Gombafonal> l : fonalLista){
            for(Gombafonal f : l){
                CallTracer.enter("connectedToAlapGomba", "Gombafonal", "");
                if(!f.connectedToAlapGomba()){
                    listOfDisconnectedFonalak.add(f);
                    CallTracer.exit("connectedToAlapGomba", "false");
                }else {
                    CallTracer.exit("connectedToAlapGomba", "true");
                }
            }
        }
        if (listOfDisconnectedFonalak.isEmpty()){
            return null;
        } else {
            return listOfDisconnectedFonalak;
        }
    }

    /**
     * A gombafonal felszívódását kezeli.
     *
     * @param gf Az eltávolítandó gombafonal.
     */
    public void fonalFelszivodas(Gombafonal gf) {
        Tekton t1 = gf.getStartTekton();
        Tekton t2 = gf.getCelTekton();

        CallTracer.enter("removeKapcsolodoFonal", "Tekton:t1", "gf");
        t1.removeKapcsolodoFonal(gf);
        CallTracer.exit("removeKapcsolodoFonal", "");

        CallTracer.enter("removeKapcsolodoFonal", "Tekton:t2", "gf");
        t2.removeKapcsolodoFonal(gf);
        CallTracer.exit("removeKapcsolodoFonal", "");

        CallTracer.enter("elpusztul", "Gombafonal:gf", "");
        gf.elpusztul();
        CallTracer.exit("elpusztul", "");
    }

    public void deleteFonal(Gombafonal gf) {
        fonalLista.remove(gf);              //TODO sekvencia szerint megírni

    }

    /**
     * A gomba spórákat szór a céltektonra, ha rendelkezik elegendő spórával.
     *
     * @param celTekton A céltekton, amelyre a gomba spórákat szór.
     * @param gt        A gomba központi teste.
     * @return {@code true}, ha a szórás sikeres volt, egyébként {@code false}.
     */
    public boolean szor(Tekton celTekton, GombaTest gt) {
        CallTracer.enter("getSzomszedosTektonok", "Tekton", "");
        List<Tekton> szomszedLista = celTekton.getSzomszedosTektonok();
        CallTracer.exit("getSzomszedosTektonok", "szomszedLista");

        //Ha nem szomszédosak a tektonok
        boolean szomszedosak = false;
        szomszedosak = szomszedLista.contains(gt.getAlapGomba().getTekton());
        if(!szomszedosak) { //Test30 miatt kell
            return false;
        }

        CallTracer.enter("getSzint", "GombaTest", "");
        int szint = gt.getSzint();
        CallTracer.exit("getSzint", "szint");

        CallTracer.enter("sporaSzorzo", "GombaTest", "szint:int");
        int szorandoMennyiseg = gt.sporaSzorzo(szint);
        CallTracer.exit("sporaSzorzo", "szorandoMennyiseg : int");

        boolean van_eleg_spora = gt.getSporaKeszlet() >= 3;

        if (van_eleg_spora) {
            for (int i = 0; i <3; i++) {
                CallTracer.enter("decreaseSporaKeszlet", "GombaTest", "");
                gt.decreaseSporaKeszlet(); //3 költség levonása a gombatest től
                CallTracer.exit("decreaseSporaKeszlet", "");
            }

            CallTracer.enter("szintlepes", "GombaTest", "szorandoMennyiseg:int");
            gt.szintlepes(szorandoMennyiseg);
            CallTracer.exit("szintlepes", "");

            List<BaseSpora> s_list = new ArrayList<>();
            for (int i = 0; i < szorandoMennyiseg; ++i) {
                CallTracer.enter("Spora", "Spora", "");
                s_list.add(new Spora());
                CallTracer.exit("Spora()", "");
            }

            if(celTekton.getVanGombaTest()) {
                GombaTest tmp_gt = celTekton.getGomba().getGombaTest(); // A gombatest ahova szorunk
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "szorandoMennyiseg:int");
                tmp_gt.addToSporaKeszlet(szorandoMennyiseg);
                CallTracer.exit("addToSporaKeszlet", "");
            } else {
                for(BaseSpora s : s_list) {
                    CallTracer.enter("addSpora", "Tekton", "s:Spora");
                    celTekton.addSpora(s);
                    CallTracer.exit("addSpora", "");
                }
            }

            CallTracer.enter("addSzorasCount", "GombaTest", "1");
            gt.addSzorasCount(1);
            CallTracer.exit("addSzorasCount", "");

            return true;
        } else {
            return false;
        }
    }

    /**
     * Gombafonalak felvétele a fonallistába.
     *
     * @param gf Fonal, melyet felveszünk a fonallistába
     */
    public void addFonal(Gombafonal gf) {
        this.fonalLista.get(0).add(gf); ////TODO kitalálni, hogy a listát hogy bővítsük meg a listák listájával
    }

    /**
     * A fonalkészlet 1-el történő csökkentése.
     */
    public boolean decreaseFonalKeszlet() {
        this.fonalKeszlet--;
        return false;
    }

    /**
     * A fonalkészlet növelése.
     *
     * @param val Az érték, amennyivel a készletet növeljük.
     */
    public void increaseFonalKeszlet(int val) {
        this.fonalKeszlet += val;
    }

    @Override
    public void elpusztul() {
    }
}
