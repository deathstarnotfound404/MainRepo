package FungoriumClasses;
import CallTracer.*;

/**
 * A {@code Rovarasz} osztály egy játékost reprezentál, aki egy rovart irányít a játékban.
 * A rovarász képes mozgatni a rovarját, gombafonalakat vágni és pontokat gyűjteni a tápanyagok alapján.
 */
public class Rovarasz extends Player {
    /**
     * A rovarászhoz tartozó rovar.
     */
    private Rovar rovaraszRovarja;

    /**
     * Létrehoz egy új {@code Rovarasz} objektumot.
     */
    public Rovarasz() {
    }

    /**
     * Visszaadja a rovarászhoz tartozó rovart.
     *
     * @return A rovarász saját {@code Rovar} objektuma.
     */
    public Rovar getRovar() {
        return rovaraszRovarja;
    }

    /**
     * Hozzárendel egy rovart a rovarászhoz, és beállítja annak helyzetét.
     *
     * @param r A rovar, amelyet a rovarász irányítani fog.
     * @param t A {@code Tekton}, ahol a rovar elhelyezkedik.
     */
    public void addRovar(Rovar r, Tekton t) {
        this.rovaraszRovarja = r;
        this.rovaraszRovarja.setHelyzet(t);
    }

    /**
     * Kiszámítja a rovarász összesített tápanyagalapú pontszámát.
     *
     * @return Az összesített tápanyag-pontszám.
     */
    public int calcAllTapanyagScore() {
        return 0;
    }

    /**
     * Megpróbálja a megadott rovart a cél {@code Tekton}-ra irányítani.
     *
     * @param r         Az irányítandó rovar.
     * @param celTekton A cél {@code Tekton}, ahová a rovar mozogni próbál.
     * @return {@code true}, ha a mozgás sikeres, különben {@code false}.
     */
    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        CallTracer.enter("lep", "Rovar", "celTekton:Tekton");
        boolean val = r.lep(celTekton);
        if (val) {
            CallTracer.exit("lep", "true");
        } else {
            CallTracer.exit("lep", "false");
        }
        return val;
    }

    /**
     * Megpróbálja a megadott rovart egy gombafonal elvágására utasítani.
     *
     * @param r  Az elvágást végrehajtó rovar.
     * @param gf A vágandó {@code Gombafonal}.
     * @return {@code true}, ha a vágás sikeres, különben {@code false}.
     */
    public boolean fonalVagas(Rovar r, Gombafonal gf) {
        if(!r.getTudVagni()) return false;
        else{
            CallTracer.enter("vag", "Rovar", "gf");
            r.vag(gf);
            CallTracer.exit("vag", "true");
            return true;
        }
    }
}
