package FungoriumClasses;

import java.util.ArrayList;
import java.util.List;

public class Rovar {
    private int tapanyag = 0;
    private int evesHatekonysag = 1;
    private boolean maxFogyasztas = false;
    private Tekton helyzet;
    private boolean tudVagni = true;

    public Rovar() {

    }

    public void vag(Gombafonal gf) {
        //TODO szekvenciák alapján
    }

    public void lep(Tekton celTekton) {
        //TODO szekvenciák alapján
    }

    public void setHelyzet(Tekton t) {
        this.helyzet = t;
    }

    public Tekton getHelyzet() {
        return this.helyzet;
    }

    public void setTapanyag(int val) {
        this.tapanyag = val;
    }

    public int getTapanyag() {
        return this.tapanyag;
    }

    public void addTapanyag(int val) {
        this.tapanyag += val;
    }

    public void kepessegekAlaphelyzetbe() {
        evesHatekonysag = 1;
        maxFogyasztas = false;
        tudVagni = true;
    }

    public void sporaEves() {
        //TODO szekvenciák alapján
    }

    public void setEvesHatekonysag(int val) {
        this.evesHatekonysag = val;
    }

    public int getEvesHatekonysag(List<ArrayList> sporaLista) {
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
