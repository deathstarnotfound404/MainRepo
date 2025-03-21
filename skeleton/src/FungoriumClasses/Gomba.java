package FungoriumClasses;

import CallTracer.CallTracer;

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

    public boolean fonalFolytonossagVizsgalat(Tekton t1) {
        //Fonalak folytonosak-e t1-ig, el lehet e jutni az alapgombából t1-ig
        //Az üzleti logika alapján döntjük majd el.
        //return false, ha nem folytonos
        return true;
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
        CallTracer.enter("getSzomszedosTektonok", "Tekton", "");
        List<Tekton> szomszedLista = celTekton.getSzomszedosTektonok();
        CallTracer.exit("getSzomszedosTektonok", "szomszedLista");

        //Ha nem szomszédosak a tektonok
        boolean szomszedosak = false;
        szomszedosak = szomszedLista.contains(gt.getAlapGomba().getTekton());
        if(!szomszedosak) { //Test30 miatt kell
            return false;
        }

        CallTracer.enter("getSzint", "GombaTest", "");
        int szint = gt.getSzint();
        CallTracer.exit("getSzint", "szint");

        CallTracer.enter("sporaSzorzo", "GombaTest", "szint:int");
        int szorandoMennyiseg = gt.sporaSzorzo(szint);
        CallTracer.exit("sporaSzorzo", "szorandoMennyiseg : int");

        boolean van_eleg_spora = gt.getSporaKeszlet() >= 3;

        if (van_eleg_spora) {
            for (int i = 0; i <3; i++) {
                CallTracer.enter("decreaseSporaKeszlet", "GombaTest", "");
                gt.decreaseSporaKeszlet(); //3 költség levonása a gombatest től
                CallTracer.exit("decreaseSporaKeszlet", "");
            }

            CallTracer.enter("szintlepes", "GombaTest", "szorandoMennyiseg:int");
            gt.szintlepes(szorandoMennyiseg);
            CallTracer.exit("szintlepes", "");

            List<BaseSpora> s_list = new ArrayList<>();
            for (int i = 0; i < szorandoMennyiseg; ++i) {
                CallTracer.enter("Spora", "Spora", "");
                s_list.add(new Spora());
                CallTracer.exit("Spora()", "");
            }

            for(BaseSpora s : s_list) {
                CallTracer.enter("addSpora", "Tekton", "s:Spora");
                celTekton.addSpora(s);
                CallTracer.exit("addSpora", "");
            }

            CallTracer.enter("addSzorasCount", "GombaTest", "1");
            gt.addSzorasCount(1);
            CallTracer.exit("addSzorasCount", "");

            return true;
        } else {
            return false;
        }
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
