package FungoriumClasses;

import CallTracer.CallTracer;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
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
        CallTracer.enter("getSzint", "GombaTest", "");
        int szint = GombaTest.getSzint();
        CallTracer.exit("getSzint", "szint");
        switch (szint){
            case 1:
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "2");
                GombaTest.addToSporaKeszlet(2);
                CallTracer.exit("addToSporaKeszlet", "");
                break;
            case 2:
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "3");
                GombaTest.addToSporaKeszlet(3);
                CallTracer.exit("addToSporaKeszlet", "");
                break;
            case 3:
                CallTracer.enter("addToSporaKeszlet", "GombaTest", "4");
                GombaTest.addToSporaKeszlet(4);
                CallTracer.exit("addToSporaKeszlet", "");
                break;
        }
    }

    public void gombatestSzintlepes() {
        //TODO sekvencia szerint megírni
    }

    public List<Gombafonal> fonalFolytonossagVizsgalat() {
        //TODO sekvencia szerint megírni
        return null;
    }

    public void fonalFelszivodas(Gombafonal gf) {
        Tekton t1 = gf.getStartTekton();
        Tekton t2 = gf.getCelTekton();

        CallTracer.enter("removeKapcsolodoFonal", "Tekton:t1", "gf");
        t1.removeKapcsolodoFonal(gf);
        CallTracer.exit("removeKapcsolodoFonal", "");

        CallTracer.enter("removeKapcsolodoFonal", "Tekton:t2", "gf");
        t2.removeKapcsolodoFonal(gf);
        CallTracer.exit("removeKapcsolodoFonal", "");

        CallTracer.enter("elpusztul", "Gombafonal:gf", "");
        gf.elpusztul();
        CallTracer.exit("elpusztul", "");
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
