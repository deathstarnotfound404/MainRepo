package FungoriumClasses;

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
        System.out.println("<<<return Tekton(hatas)");
    }

    public String hatasKifejtes() {
        System.out.println("<<<return hatasKifejtes()");
        return kapcsolodoFonalak.toString();
    }

    public int sporaCount() {
        System.out.println("<<<return sporaCount()");
        return sporaLista.size();
    }

    public void addSpora(BaseSpora spora) {
        System.out.println("<<<return addSpora(spora)");
    }

    public Rovar tektonTores() {
        System.out.println("<<<return tektonTores(rovarLatogatottsag)");
        return null;
    }

    public boolean vanBogarATektonon() {
        System.out.println("<<<return vanBogarATektonon()");
        return false;
    }

    public void fonalakFelszivasa() {
        System.out.println("<<<return fonalakFelszivasa()");
    }

    public void setVanGombaTest(boolean val) {
        System.out.println("<<<return setVanGombaTest(val)");
    }

    public boolean getVanGombaTest() {
        System.out.println("<<<return getVanGombaTest()");
        return false;
    }

    public Gomba getGomba() {
        System.out.println("<<<return getGomba()");
        return null;
    }

    public List<BaseSpora> getSporaLista() {
        System.out.println("<<<return getSporaLista()");
        return sporaLista;
    }

    public void setFokszam(int val) {
        System.out.println("<<<return elpusztul()");
    }

    public int getFokszam() {
        System.out.println("<<<return getFokszam()");
        return 0;
    }

    public void addLatogatottsag() {
        System.out.println("<<<return addLatpgatottsag()");
    }

    public int getLatogatottsag() {
        System.out.println("<<<return getLatogatottsag()");
        return 0;
    }

    public List<Gombafonal> getKapcsolodoFonal() {
        System.out.println("<<<return getKapcsolodFonal()");
        return kapcsolodoFonalak;
    }

    public void addKapcsolodoFonalak(Gombafonal fonal) {
        System.out.println("<<<return addKapcsolodoFonalak()");
    }

    public void setRovar(Rovar r) {
        System.out.println("<<<return setRovar(r)");
    }

    public Rovar getRovar() {
        System.out.println("<<<return getRovar()");
        return null;
    }

    public void setGomba(Gomba g) {
        this.tektononLevoGomba = g;
        System.out.println("<<<return setGomba()");
    }

    public List<Tekton> getSzomszedosTekton(Tekton tekton) {
        System.out.println("<<<return addSzomszedosTekton()");
        return null;
    }

    public void removeKapcsolodoFonal() {
        System.out.println("<<<return removeKapcsolodoFonal()");
    }

    public void decreaseFokszam(int val) {
        System.out.println("<<<return decreaseFokszam(val)");
    }

    public void increaseFokszam() {
        System.out.println("<<<return increaseFokszam()");
    }

    public void removeSporak() {
        System.out.println("<<<return removeSporak()");
    }

    public void addSzomszedosTekton(Tekton t) {
        System.out.println("<<<return addSzomszedosTekton(t)");
    }

    public boolean removeSzomszedosTekton(Tekton t) {
        System.out.println("<<<return removeSzomszedosTekton(t)");
        return false;
    }

    @Override
    public void elpusztul() {
        System.out.println("<<<return elpusztul()");
    }
}
