package FungoriumClasses;

import CallTracer.CallTracer;

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

    public boolean lep(Tekton celTekton) {
        Tekton t1 = helyzet;
        CallTracer.enter("getSzomszedosTektonok", "Tekton", "");
        List<Tekton> szomszedLista = t1.getSzomszedosTektonok();
        CallTracer.exit("getSzomszedosTektonok", "szomszedLista:List<Tekton>");

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

    public void setHelyzet(Tekton t) {
        this.helyzet = t;
        t.setRovar(this);
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
        CallTracer.enter("getSporaLista", "Tekton", "");
        List<BaseSpora> sporaLista = helyzet.getSporaLista();
        CallTracer.exit("getSporaLista", "sporaLista");
        List<BaseSpora> megevendo = sporaLista.subList(0,evesHatekonysag);
        for(BaseSpora s: megevendo){
            CallTracer.enter("addTapanyag", "Rovar", "s.tapanyag");
            addTapanyag(s.tapanyag);
            CallTracer.exit("addTapanyag", "");
            CallTracer.enter("hatas", "GyorsitoSpora", "this");
            s.hatas(this);
            CallTracer.exit("GyorsitoSpora", "");
        }
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
