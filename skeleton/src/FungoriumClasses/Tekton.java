package FungoriumClasses;
import CallTracer.*;
import java.util.*;

/**
 * A {@code Tekton} osztály egy Tektont, mint csomópontot reprezentál a játékban.
 * Egy tekton különböző spórákat és gombafonalakat tartalmazhat, valamint egy rovar vagy egy gomba is tartózkodhat rajta.
 *
 * @author Bekő Máté
 * @version 1.0
 * @since 2025-03-18
 */
public class Tekton implements IDestroyable {
    //private int id = 0; -
    private int rovarLatogatottsag = 0;
    private int fonalFokszam = 0;
    private List<BaseSpora> sporaLista;
    private List<Gombafonal> kapcsolodoFonalak;
    private boolean vanGombaTestTektonon;
    private List<Tekton> szomszedosTektonok;
    private TektonHatas tektonHatasa;
    private Rovar tektononLevoRovar;
    private Gomba tektononLevoGomba;

    /**
     * Létrehoz egy új {@code Tekton} objektumot a megadott hatással.
     *
     * @param hatas A tekton hatása, amely meghatározza a játékbeli működését.
     */
    public Tekton(TektonHatas hatas) {
        this.sporaLista = new ArrayList<>();
        this.kapcsolodoFonalak = new ArrayList<>();
        this.szomszedosTektonok = new ArrayList<>();
        this.tektonHatasa = hatas;
    }

    /**
     * Visszaadja a tekton hatását.
     *
     * @return A tekton hatásának neve.
     */
    public String hatasKifejtes() {
        CallTracer.enter("hatas", "TektonHatas", "");
        String hatas = tektonHatasa.hatas();
        CallTracer.exit("hatas", hatas);
        return hatas;
    }

    /**
     * Visszaadja a tektonon lévő spórák számát.
     *
     * @return A spórák száma.
     */
    public int sporaCount() {
        return sporaLista.size();
    }

    /**
     * Hozzáad egy új spórát a tektonhoz.
     *
     * @param spora A hozzáadandó spóra.
     */
    public void addSpora(BaseSpora spora) {
        this.sporaLista.add(spora);
    }

    /**
     * Elpusztítja a tektont, és visszaadja az esetleg rajta tartózkodó rovart.
     *
     * @return A tektonon lévő rovar, ha van ilyen.
     */
    public Rovar tektonTores() {
        CallTracer.enter("fonalakFelszivasa", "Tekton", "");
        this.fonalakFelszivasa();
        CallTracer.exit("fonalakFelszivasa", "");

        CallTracer.enter("elpusztul", "Gomba", "");
        this.tektononLevoGomba.elpusztul();
        CallTracer.exit("elpusztul", "");

        return this.tektononLevoRovar;
    }

    /**
     * Megvizsgálja, hogy van-e rovar a tektonon.
     *
     * @return {@code true}, ha van rovar a tektonon, különben {@code false}.
     */
    public boolean vanBogarATektonon() {
        return tektononLevoRovar != null;
    }

    /**
     * Eltávolít egy gombafonalat és értesíti a megfelelő gombát a felszívódásról.
     */
    public void fonalakFelszivasa() {
        Gombafonal gf = this.kapcsolodoFonalak.get(0);
        CallTracer.enter("getAlapGomba", "Gombafonal", "");
        Gomba g = gf.getAlapGomba();
        CallTracer.exit("getAlapGomba", "g");

        CallTracer.enter("fonalFelszivodas", "Gomba", "gf");
        g.fonalFelszivodas(gf);
        CallTracer.exit("fonalFelszivodas", "");
    }

    /**
     * Beállítja, hogy van-e gombatest a tektonon.
     *
     * @param val {@code true}, ha van gombatest, különben {@code false}.
     */
    public void setVanGombaTest(boolean val) {
        vanGombaTestTektonon = val;
    }

    /**
     * Lekérdezi, hogy van-e gombatest a tektonon.
     *
     * @return {@code true}, ha van gombatest, különben {@code false}.
     */
    public boolean getVanGombaTest() {
        return this.vanGombaTestTektonon;
    }

    /**
     * Visszaadja a tektonon lévő gombát.
     *
     * @return A tektonon található gomba, ha van.
     */
    public Gomba getGomba() {
        return this.tektononLevoGomba;
    }

    /**
     * Visszaadja a tektonon lévő spórák listáját.
     *
     * @return A spórák listája.
     */
    public List<BaseSpora> getSporaLista() {
        return this.sporaLista;
    }

    /**
     * Beállítja a tektonhoz kapcsolódó gombafonalak fokszámát.
     *
     * @param val Az új fokszám értéke.
     */
    public void setFokszam(int val) {
        this.fonalFokszam = val;
    }

    /**
     * Visszaadja a tektonhoz kapcsolódó gombafonalak fokszámát.
     *
     * @return A fokszám értéke.
     */
    public int getFokszam() {
        return this.fonalFokszam;
    }

    /**
     * Növeli a tekton látogatottsági számát.
     */
    public void addLatogatottsag() {
        this.rovarLatogatottsag++;
    }

    /**
     * Visszaadja a tekton látogatottsági számát.
     *
     * @return A látogatottsági szám.
     */
    public int getLatogatottsag() {
        return this.rovarLatogatottsag;
    }

    /**
     * Visszaadja a tektont összekötő gombafonalak listáját.
     *
     * @return A kapcsolódó gombafonalak listája.
     */
    public List<Gombafonal> getKapcsolodoFonalak() {
        return kapcsolodoFonalak;
    }

    /**
     * Hozzáad egy új gombafonalat a tektonhoz.
     *
     * @param fonal A hozzáadandó gombafonal.
     */
    public void addKapcsolodoFonalak(Gombafonal fonal) {
        this.kapcsolodoFonalak.add(fonal);
    }

    /**
     * Beállítja a tektonon lévő rovart.
     *
     * @param r A beállítandó rovar.
     */
    public void setRovar(Rovar r) {
        //Kell e this.rovarLatogatottsag++;
        this.tektononLevoRovar = r;
        this.vanGombaTestTektonon = true;
    }

    /**
     * Visszaadja a tektonon lévő rovart.
     *
     * @return A tektonon lévő rovar, vagy {@code null}, ha nincs rovar a tektonon.
     */
    public Rovar getRovar() {
        return this.tektononLevoRovar;
    }

    /**
     * Beállítja a tektonon lévő gombát.
     *
     * @param g A beállítandó gomba.
     */
    public void setGomba(Gomba g) {
        this.tektononLevoGomba = g;
        if(g!=null) {
            this.vanGombaTestTektonon = true;
        }
    }

    /**
     * Visszaadja a tektonnal szomszédos tektonok listáját.
     *
     * @return A szomszédos tektonok listája.
     */
    public List<Tekton> getSzomszedosTektonok() {
        return this.szomszedosTektonok;
    }

    /**
     * Eltávolít egy kapcsolódó gombafonalat és csökkenti a fokszámot.
     *
     * @param gf Az eltávolítandó gombafonal.
     */
    public void removeKapcsolodoFonal(Gombafonal gf) {
        if(this.kapcsolodoFonalak.remove(gf)) {
            CallTracer.enter("decreaseFokszam", "Tekton", "1");
            decreaseFokszam(1);
            CallTracer.exit("decreaseFokszam", "");
        } else {
            CallTracer.enter("decreaseFokszam", "Tekton", "1");
            decreaseFokszam(1);
            CallTracer.exit("decreaseFokszam", "HIBA");
        }
    }

    /**
     * Csökkenti a tekton fokszámát.
     *
     * @param val A csökkentés mértéke.
     */
    public void decreaseFokszam(int val) {
        if (this.fonalFokszam > 0) {
            this.fonalFokszam--;
        }
    }

    /**
     * Növeli a tekton fokszámát.
     */
    public void increaseFokszam() {
        this.fonalFokszam++;
    }

    /**
     * Eltávolítja az összes spórát a tektonról.
     */
    public void removeSporak() {
        for (BaseSpora s : this.sporaLista) {
            CallTracer.enter("elpusztul", "BaseSpora", "");
            s.elpusztul();
            CallTracer.exit("elpusztul", "");
        }
        this.sporaLista.clear();
    }

    /**
     * Hozzáad egy szomszédos tektont.
     *
     * @param t A hozzáadandó szomszédos tekton.
     */
    public void addSzomszedosTekton(Tekton t) {
        this.szomszedosTektonok.add(t);
    }

    /**
     * Eltávolít egy szomszédos tektont.
     *
     * @param t Az eltávolítandó szomszédos tekton.
     * @return {@code true}, ha sikeresen eltávolította, különben {@code false}.
     */
    public boolean removeSzomszedosTekton(Tekton t) {
        return(this.szomszedosTektonok.remove(t));
    }

    /**
     * A tekton megsemmisülését implementáló metódus.
     */
    @Override
    public void elpusztul() {
        //Későbbi implementáció
    }
}
