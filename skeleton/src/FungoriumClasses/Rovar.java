package FungoriumClasses;

import java.util.ArrayList;
import java.util.List;

public class Rovar {
    private Rovarasz rovaraszRovarja;
    private int tapanyag = 0;
    private int evesHatekonysag = 1;
    private boolean maxFogyasztas;
    private Tekton helyzet;
    private boolean tudVagni = true;

    public Rovar() {
        System.out.println("<<<return Rovar()");
    }

    public void vag(Gombafonal gf) {
        System.out.println("<<<return vag()");
    }

    public void lep(Tekton celTekton) {
        System.out.println("<<<return lep()");
    }

    public void setHelyzet(Tekton t) {
        System.out.println("<<<return setHelyzet()");
    }

    public Tekton getHelyzet() {
        System.out.println("<<<return getHelyzet()");
        return null;
    }

    public void setTapanyag(int val) {
        System.out.println("<<<return setTapanyag()");
    }

    public int getTapanyag() {
        System.out.println("<<<return getTapanyag()");
        return 0;
    }

    public void addTapanyag(int val) {
        System.out.println("<<<return addTapanyag()");
    }

    public void kepessegekAlaphelyzetbe() {
        System.out.println("<<<return kepessegekAlaphelyzetbe()");
    }

    public void sporaEves() {
        System.out.println("<<<return sporaEves()");
    }

    public void setEvesHatekonysag(int val) {
        System.out.println("<<<return setEvesHatekonysag()");
    }

    public int getEvesHatekonysag(List<ArrayList> sporaLista) {
        System.out.println("<<<return getEvesHatekonysag()");
        return 0;
    }

    public void setMaxFogyasztas(boolean val) {
        System.out.println("<<<return setMaxFogyasztas()");
    }

    public boolean getMaxFogyasztas() {
        System.out.println("<<<return getMaxFogyasztas()");
        return false;
    }

    public void setTudVagni(boolean val) {
        System.out.println("<<<return setTudVagni()");
    }

    public boolean getTudVagni() {
        System.out.println("<<<return getTudVagni()");
        return false;
    }
}
