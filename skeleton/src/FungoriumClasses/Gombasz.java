package FungoriumClasses;
import CallTracer.*;
import java.util.*;

/**
 * A {@code Gombasz} osztály a játékosokat reprezentálja, akik gombákat irányítanak.
 *
 * @author Botos Dániel, Kozma Szabolcs, Czene Zsombor
 * @version 1.0
 * @since 2025-03-18
 */
public class Gombasz extends Player {
    private List<Gomba> gombaLista;

    /**
     * Létrehoz egy új {@code Gombasz} objektumot és inicializálja az üres gombalistát.
     */
    public Gombasz() {
        gombaLista = new ArrayList<Gomba>();
    }

    /**
     * Hozzáad egy gombát a gombászhoz.
     *
     * @param g A hozzáadandó {@code Gomba}.
     */
    public void addGomba(Gomba g) {
        gombaLista.add(g);
    }

    /**
     * Visszaadja a gombászhoz tartozó gombák listáját.
     *
     * @return A gombák listája.
     */
    public List<Gomba> getGombaLista() {
        return gombaLista;
    }

    /**
     * Kiszámítja az összes gombatest pontszámát.
     *
     * @return Az összesített pontszám.
     */
    public int calcAllGombatestScore() {
        return 0;
    }

    /**
     * Az összes gomba elvégzi a spóratermelési folyamatot.
     */
    public void sporaTermelesAll() {
        for(Gomba g : gombaLista){
            CallTracer.enter("sporaTermeles", "Gomba", "");
            g.sporaTermeles();
            CallTracer.exit("sporaTermeles", "");
        }
    }

    /**
     * Ellenőrzi, hogy a fonal lerakása végrehajtható-e.
     *
     * @return {@code true}, ha a fonal lerakása lehetséges, különben {@code false}.
     */
    public boolean fonalLerakasEllenorzes() {
        //return false, ha az üzlezi logika szerint a fonal lerakasa nem elvegezhető -> t1 ből t2 be.
        return true;
    }

    /**
     * Szórást hajt végre egy adott gombával egy céltektonra.
     *
     * @param g         A szórást végző {@code Gomba}.
     * @param celTekton A cél {@code Tekton}.
     * @return {@code true}, ha a szórás sikeres, különben {@code false}.
     */
    public boolean szoras(Gomba g, Tekton celTekton) {
        CallTracer.enter("getGombaTest", "Gomba", "");
        GombaTest gt = g.getGombaTest();
        CallTracer.exit("getGombaTest", "gt");

        CallTracer.enter("getTekton", "Gomba", "");
        Tekton t1 = g.getTekton();
        CallTracer.exit("getTekton", "t1");

        CallTracer.enter("szor", "Gomba", "celTekton, gt");
        if (g.szor(celTekton, gt)) {
            CallTracer.exit("szor", "true");
            return true;
        } else {
            CallTracer.exit("szor", "false");
            return false;
        }
    }

    /**
     * Fonalat vásárol egy adott gombával.
     *
     * @param g A vásárlást végző {@code Gomba}.
     * @return {@code true}, ha a vásárlás sikeres, különben {@code false}.
     */
    public boolean fonalVasarlas(Gomba g) {
        CallTracer.enter("getGombaTest", "Gomba", "");
        GombaTest gt = g.getGombaTest();
        CallTracer.exit("getGombaTest", "gt");

        CallTracer.enter("decreaseSporaKeszlet", "GombaTest", "");
        boolean val = gt.decreaseSporaKeszlet();
        if (val) {
            CallTracer.exit("decreaseSporaKeszlet", "true");

            CallTracer.enter("increaseFonalKeszlet", "Gomba", "3");
            g.increaseFonalKeszlet(3);
            CallTracer.exit("increaseFonalKeszlet", "");
        } else {
            CallTracer.exit("decreaseSporaKeszlet", "false");
        }
        return val;
    }

    /**
     * Gombatest növesztése egy adott tektonon.
     *
     * @param t A cél {@code Tekton}.
     * @return {@code true}, ha a növesztés sikeres, különben {@code false}.
     */
    public boolean gombatestNovesztes(Tekton t) {
        CallTracer.enter("getVanGombaTest", "Tekton", "");
        if (!t.getVanGombaTest()) {
            CallTracer.exit("getVanGombaTest", "false");
        } else {
            CallTracer.exit("getGomba", "HIBA");
        }
        CallTracer.enter("getGomba", "Tekton", "");
        Gomba g = t.getGomba();
        CallTracer.exit("getGomba", "g");
        CallTracer.enter("sporaCount", "Tekton", "");
        int sporaSzam = t.sporaCount();
        CallTracer.exit("sporaCount", "sporaSzam");

        CallTracer.enter("removeSporak", "Tekton", "5");
        t.removeSporak();
        CallTracer.exit("removeSporak", "");

        CallTracer.enter("GombaTest", "GombaTest", "g, sporaSzam");
        GombaTest gt = new GombaTest(g, sporaSzam);
        CallTracer.exit("GombaTest", "");

        CallTracer.enter("addToSporaKeszlet", "GombaTest", "sporaSzam - 3");
        gt.addToSporaKeszlet(sporaSzam - 3);    //Szabály szerint 3-at használ fel  //TODO ellenőrizni
        CallTracer.exit("addToSporaKeszlet", "");

        CallTracer.enter("addScore", "Gombasz", "");
        this.addScore();
        CallTracer.exit("addScore", "");

        CallTracer.enter("setVanGombaTest", "Tekton", "true");
        t.setVanGombaTest(true);
        CallTracer.exit("setVanGombaTest", "");

        return true;
    }

    /**
     * Gombafonal növesztése két tekton között.
     *
     * @param g          A növesztést végző {@code Gomba}.
     * @param startTekton A kezdő {@code Tekton}.
     * @param celTekton   A cél {@code Tekton}.
     */
    public void gombafonalNovesztes(Gomba g, Tekton startTekton, Tekton celTekton) {
        //Implementáció később
    }

    /**
     * Gombafonal irányítását végzi két tekton között.
     *
     * @param t1         A kiindulási {@code Tekton}.
     * @param t2         A cél {@code Tekton}.
     * @return {@code true}, ha a művelet sikeres, különben {@code false}.
     */
    public boolean gombafonalIranyitas(Gomba g, Tekton t1, Tekton t2, boolean bonus) {
        CallTracer.enter("fonalLerakasEllenorzes", "Gombasz", "");
        if (this.fonalLerakasEllenorzes()){
            CallTracer.exit("fonalLerakasEllenorzes", "true");
        } else {
            CallTracer.exit("fonalLerakasEllenorzes", "HIBA");
            return false;
        }

        CallTracer.enter("getGombaTest", "Gomba", "");
        GombaTest gt = g.getGombaTest();
        CallTracer.exit("getGombaTets", "gt");

        CallTracer.enter("fonalFolytonossagVizsgalat", "Gomba", "t1");
        if(g.fonalFolytonossagVizsgalat() == null){
            CallTracer.exit("fonalFolytonossagVizsgalat", "true");
        } else {
            CallTracer.exit("fonalFolytonossagVizsgalat", "HIBA");
            return false;
        }

        CallTracer.enter("Gombafonal", "Gombafonal", "t1, t2");
        Gombafonal gf = new Gombafonal(t1, t2);
        gf.setAlapGomba(g);
        CallTracer.exit("Gombafonal()", "");

        CallTracer.enter("addKapcsolodoFonalak", "Tekton:t1", "gf");
        t1.addKapcsolodoFonalak(gf);
        CallTracer.exit("addKapcsolodoFonalak", "");

        CallTracer.enter("addKapcsolodoFonalak", "Tekton:t2", "gf");
        t2.addKapcsolodoFonalak(gf);
        CallTracer.exit("addKapcsolodoFonalak", "");

        CallTracer.enter("increaseFokszam", "Tekton:t1", "");
        t1.increaseFokszam();
        CallTracer.exit("increaseFokszam", "");

        CallTracer.enter("increaseFokszam", "Tekton:t2", "");
        t2.increaseFokszam();
        CallTracer.exit("increaseFokszam", "");

        CallTracer.enter("addFonal", "Gomba", "gf");
        g.addFonal(gf);
        CallTracer.exit("addFonal", "");

        if(!bonus){
            CallTracer.enter("decreaseFonalKeszlet", "Gomba", "");
            g.decreaseFonalKeszlet();
            CallTracer.exit("decreaseFonalKeszlet", "");
        }
        return true;
    }
}
