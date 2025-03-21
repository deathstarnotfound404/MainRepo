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
        return "Base";  //TODO szekvenciák alapján
    }

    public int sporaCount() {
        return sporaLista.size();
    }

    public void addSpora(BaseSpora spora) {
        this.sporaLista.add(spora);
    }

    public Rovar tektonTores() {
        return null;    //TODO szekvenciák alapján implementálni
    }

    public boolean vanBogarATektonon() {
        return vanGombaTestTektonon;
    }

    public void fonalakFelszivasa() {
        //TODO
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
    }

    public List<Tekton> getSzomszedosTekton(Tekton tekton) {
        return null;    //TODO
    }

    public void removeKapcsolodoFonal() {
        //TODO
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
