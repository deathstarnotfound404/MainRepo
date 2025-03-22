package FungoriumClasses;

import CallTracer.CallTracer;

import java.util.List;
import java.util.ArrayList;


public class Tekton implements IDestroyable {
    private int rovarLatogatottsag = 0;
    private int fonalFokszam = 0;
    private List<BaseSpora> sporaLista;
    private List<Gombafonal> kapcsolodoFonalak;
    private boolean vanGombaTestTektonon;
    private List<Tekton> szomszedosTektonok;

    private TektonHatas tektonHatasa;
    private Rovar tektononLevoRovar;
    private Gomba tektononLevoGomba;

    public Tekton(TektonHatas hatas) {
        this.sporaLista = new ArrayList<>();
        this.kapcsolodoFonalak = new ArrayList<>();
        this.szomszedosTektonok = new ArrayList<>();
        this.tektonHatasa = hatas;
    }

    public String hatasKifejtes() {
        CallTracer.enter("hatas", "TektonHatas", "");
        String hatas = tektonHatasa.hatas();
        CallTracer.exit("hatas", "Base");
        return hatas;  //TODO szekvenciák alapján
    }

    public int sporaCount() {
        return sporaLista.size();
    }

    public void addSpora(BaseSpora spora) {
        this.sporaLista.add(spora);
    }

    public Rovar tektonTores() {
        CallTracer.enter("fonalakFelszivasa", "Tekton", "");
        this.fonalakFelszivasa();
        CallTracer.exit("fonalakFelszivasa", "");

        CallTracer.enter("elpusztul", "Gomba", "");
        this.tektononLevoGomba.elpusztul();
        CallTracer.exit("elpusztul", "");

        return this.tektononLevoRovar;
    }

    public boolean vanBogarATektonon() {
        return tektononLevoRovar != null;
    }

    public void fonalakFelszivasa() {
        Gombafonal gf = this.kapcsolodoFonalak.getFirst();
        CallTracer.enter("getAlapGomba", "Gombafonal", "");
        Gomba g = gf.getAlapGomba();
        CallTracer.exit("getAlapGomba", "g");

        CallTracer.enter("fonalFelszivodas", "Gomba", "gf");
        g.fonalFelszivodas(gf);
        CallTracer.exit("fonalFelszivodas", "");
    }

    public void setVanGombaTest(boolean val) {
        vanGombaTestTektonon = val;
    }

    public boolean getVanGombaTest() {
        return this.vanGombaTestTektonon;
    }

    public Gomba getGomba() {
        return this.tektononLevoGomba;
    }

    public List<BaseSpora> getSporaLista() {
        return this.sporaLista;
    }

    public void setFokszam(int val) {
        this.fonalFokszam = val;
    }

    public int getFokszam() {
        return this.fonalFokszam;
    }

    public void addLatogatottsag() {
        this.rovarLatogatottsag++;
    }

    public int getLatogatottsag() {
        return this.rovarLatogatottsag;
    }

    public List<Gombafonal> getKapcsolodoFonal() {
        return kapcsolodoFonalak;
    }

    public void addKapcsolodoFonalak(Gombafonal fonal) {
        this.kapcsolodoFonalak.add(fonal);
    }

    public void setRovar(Rovar r) {
        //Kell e this.rovarLatogatottsag++;
        this.tektononLevoRovar = r;
        this.vanGombaTestTektonon = true;
    }

    public Rovar getRovar() {
        return this.tektononLevoRovar;
    }

    public void setGomba(Gomba g) {
        this.tektononLevoGomba = g;
        if(g!=null) {
            this.vanGombaTestTektonon = true;
        }
    }

    public List<Tekton> getSzomszedosTektonok() {
        return this.szomszedosTektonok;
    }

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

    public void decreaseFokszam(int val) {
        if (this.fonalFokszam > 0) {
            this.fonalFokszam--;
        }
    }

    public void increaseFokszam() {
        this.fonalFokszam++;
    }

    public void removeSporak() {
        for (BaseSpora s : this.sporaLista) {
            CallTracer.enter("elpusztul", "BaseSpora", "");
            s.elpusztul();
            CallTracer.exit("elpusztul", "");
        }
        this.sporaLista.clear();
    }

    public void addSzomszedosTekton(Tekton t) {
        this.szomszedosTektonok.add(t);
    }

    public boolean removeSzomszedosTekton(Tekton t) {
        return(this.szomszedosTektonok.remove(t));
    }

    @Override
    public void elpusztul() {

    }
}
