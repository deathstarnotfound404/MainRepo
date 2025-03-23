package FungoriumClasses;
import CallTracer.*;
import java.util.*;

/**
 * A {@code Rovar} osztály egy rovart reprezentál a játékban.
 * A rovar képes mozogni, gombafonalakat elvágni, spórákat enni és tápanyagot gyűjteni.
 *
 * @author Botos Dániel, Kozma Szabolcs, Czene Zsombor
 * @version 1.0
 * @since 2025-03-18
 */
public class Rovar {
    /**
     * A rovar által gyűjtött tápanyag mennyisége.
     */
    private int tapanyag = 0;

    /**
     * A rovar evési hatékonysága.
     */
    private double evesHatekonysag = 1;

    /**
     * Jelzi, hogy a rovar maximális fogyasztási módba van-e kapcsolva.
     */
    private boolean maxFogyasztas = false;

    /**
     * A rovar aktuális helyzete, amely egy {@code Tekton} objektum.
     */
    private Tekton helyzet;

    /**
     * Jelzi, hogy a rovar képes-e gombafonalakat elvágni.
     */
    private boolean tudVagni = true;

    /**
     * Létrehoz egy új {@code Rovar} objektumot alapértelmezett értékekkel.
     */
    public Rovar() {

    }

    /**
     * A rovar elvág egy adott gombafonalat és eltávolítva azt a játékból.
     *
     * @param gf A vágandó gombafonal.
     */
    public void vag(Gombafonal gf) {
        CallTracer.enter("getStartTekton", "Gombafonal", "");
        Tekton start = gf.getStartTekton();
        CallTracer.exit("getStartTekton", "");
        CallTracer.enter("getCelTekton", "GombaFonal", "");
        Tekton cel = gf.getCelTekton();
        CallTracer.exit("getCelTekton", "");
        CallTracer.enter("removeKapcsolodoFonal", "Tekton", "gf");
        start.removeKapcsolodoFonal(gf);
        CallTracer.exit("removeKapcsolodoFonal", "");
        CallTracer.enter("removeKapcsolodoFonal", "Tekton", "gf");
        cel.removeKapcsolodoFonal(gf);
        CallTracer.exit("removeKapcsolodoFonal", "");
        CallTracer.enter("getAlapGomba", "Gombafonal", "");
        Gomba g = gf.getAlapGomba();
        CallTracer.exit("getAlapGomba", "Gomba");
        CallTracer.enter("deleteFonal", "Gomba", "gf");
        g.deleteFonal(gf);
        CallTracer.exit("deleteFonal", "");
    }

    /**
     * A rovar megpróbál egy szomszédos {@code Tekton}-ra lépni.
     *
     * @param celTekton A cél Tekton, ahová a rovar lépni szeretne.
     * @return {@code true}, ha a mozgás sikeres, különben {@code false}.
     */
    public boolean lep(Tekton celTekton) {
        Tekton t1 = helyzet;
        CallTracer.enter("getSzomszedosTektonok", "Tekton", "");
        List<Tekton> szomszedLista = t1.getSzomszedosTektonok();
        CallTracer.exit("getSzomszedosTektonok", "szomszedLista:List<Tekton>");

        boolean TektonokOsszekotve = false;
        for (Tekton tekton : szomszedLista) {
            for(Gombafonal f : tekton.getKapcsolodoFonalak()){
                if (f.getStartTekton().equals(t1)) {
                    TektonokOsszekotve = true;
                } else {
                    TektonokOsszekotve = false;
                }
            }
        }

        if (!TektonokOsszekotve) {
            return false;
        }

        CallTracer.enter("vanBogarATektonon", "Tekton", "");
        if(celTekton.vanBogarATektonon()) {
            CallTracer.exit("vanBogarATektonon", "true");
            return false;
        } else {
            CallTracer.exit("vanBogarATektonon", "false");

            Rovar r = t1.getRovar();

            CallTracer.enter("setRovar", "Tekton", "null");
            t1.setRovar(null);
            CallTracer.exit("setRovar", "");

            CallTracer.enter("addLatogatottsag", "Tekton", "");
            t1.addLatogatottsag();
            CallTracer.exit("addLatogatottsag", "");

            CallTracer.enter("setRovar", "Tekton", "r:Rovar");
            celTekton.setRovar(r);
            CallTracer.exit("setRovar", "");

            CallTracer.enter("setHelyzet", "Rovar", "celTekton:Tekton");
            r.setHelyzet(celTekton);
            CallTracer.exit("setHelyzet", "");

            return true;
        }
    }

    /**
     * Beállítja a rovar aktuális helyzetét.
     *
     * @param t Az új helyzetet reprezentáló {@code Tekton} objektum.
     */
    public void setHelyzet(Tekton t) {
        this.helyzet = t;
        t.setRovar(this);
    }

    /**
     * Visszaadja a rovar aktuális helyzetét.
     *
     * @return A rovar aktuális helyzetét reprezentáló {@code Tekton}.
     */
    public Tekton getHelyzet() {
        return this.helyzet;
    }

    /**
     * Beállítja a rovar tápanyagának mennyiségét.
     *
     * @param val Az új tápanyagszint.
     */
    public void setTapanyag(int val) {
        this.tapanyag = val;
    }

    /**
     * Visszaadja a rovar aktuális tápanyagszintjét.
     *
     * @return A rovar tápanyagszintje.
     */
    public int getTapanyag() {
        return this.tapanyag;
    }

    /**
     * Növeli a rovar tápanyagát a megadott értékkel.
     *
     * @param val A növelés mértéke.
     */
    public void addTapanyag(int val) {
        this.tapanyag += val;
    }

    /**
     * Visszaállítja a rovar képességeit az alapértékekre.
     */
    public void kepessegekAlaphelyzetbe() {
        evesHatekonysag = 1;
        maxFogyasztas = false;
        tudVagni = true;
    }

    /**
     * A rovar elfogyasztja az elérhető spórákat a jelenlegi helyén.
     */
    public void sporaEves() {
        CallTracer.enter("getSporaLista", "Tekton", "");
        List<BaseSpora> sporaLista = helyzet.getSporaLista();
        CallTracer.exit("getSporaLista", "sporaLista");
        int mennyiseg = (int) Math.floor(sporaLista.size() * evesHatekonysag);
        if(sporaLista.size() == 1){
            mennyiseg = 1;
        }
        List<BaseSpora> megevendo = sporaLista.subList(0,mennyiseg);
        for(BaseSpora s: megevendo){
            CallTracer.enter("addTapanyag", "Rovar", "s.tapanyag");
            addTapanyag(s.tapanyag);
            CallTracer.exit("addTapanyag", "");
            CallTracer.enter("hatas", "GyorsitoSpora", "this");
            s.hatas(this);
            CallTracer.exit("GyorsitoSpora", "");
        }
    }

    /**
     * További setterek és getterek
     */
    public void setEvesHatekonysag(double val) {
        this.evesHatekonysag = val;
    }

    public double getEvesHatekonysag(List<ArrayList> sporaLista) {
        return this.evesHatekonysag;
    }

    public void setMaxFogyasztas(boolean val) {
        this.maxFogyasztas = val;
    }

    public boolean getMaxFogyasztas() {
        return this.maxFogyasztas;
    }

    public void setTudVagni(boolean val) {
        this.tudVagni = val;
    }

    public boolean getTudVagni() {
        return this.tudVagni;
    }
}
