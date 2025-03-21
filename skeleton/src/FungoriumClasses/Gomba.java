package FungoriumClasses;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class Gomba implements IDestroyable {
    private Tekton tekton;
    private int fonalKeszlet = 0;
    private List<List<Gombafonal>> fonalLista;
    private GombaTest GombaTest;

    public Gomba(Tekton t) {
        this.tekton = t;
        fonalLista = new ArrayList<List<Gombafonal>>();
        ArrayList<Gombafonal> l = new ArrayList<Gombafonal>();
        fonalLista.add(l);
    }

    public void setTekton(Tekton t) {
        this.tekton = t;
    }

    public Tekton getTekton() {
        return this.tekton;
    }

    public void setFonalKeszlet(int val) {
        this.fonalKeszlet = val;
    }

    public int getFonalKeszlet() {
        return fonalKeszlet;
    }

    public void setGombaTest(GombaTest gt) {
        this.GombaTest = gt;
    }

    public GombaTest getGombaTest() {
        return GombaTest;
    }

    public List<List<Gombafonal>> getFonalLista() {
        return fonalLista;
    }

    public void sporaTermeles() {
        //TODO sekvencia szerint megírni
    }

    public void gombatestSzintlepes() {
        //TODO sekvencia szerint megírni
    }

    public List<Gombafonal> fonalFolytonossagVizsgalat() {
        //TODO sekvencia szerint megírni
        return null;
    }

    public void fonalFelszivodas(Gombafonal gf) {
        //TODO sekvencia szerint megírni
    }

    public void deleteFonal(Gombafonal gf) {
        fonalLista.remove(gf);              //TODO sekvencia szerint megírni

    }

    public boolean szor(Tekton celTekton, GombaTest gt) {
        return false;       //TODO sekvencia szerint megírni
    }

    public void addFonal(Gombafonal gf) {
        this.fonalLista.get(0).add(gf); ////TODO kitalálni, hogy a listát hogy bővítsük meg a listák listájával
    }

    public boolean decreaseFonalKeszlet() {
        this.fonalKeszlet--;
        return false;
    }

    public void increaseFonalKeszlet(int val) {
        this.fonalKeszlet += val;
    }

    @Override
    public void elpusztul() {
    }
}
