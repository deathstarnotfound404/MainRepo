package FungoriumClasses;

import CallTracer.CallTracer;

import java.util.Arrays;

public class Rovarasz extends Player {
    private Rovar rovaraszRovarja;
    public Rovarasz() {
    }

    public Rovar getRovar() {
        return rovaraszRovarja;
    }

    public void addRovar(Rovar r, Tekton t) {
        this.rovaraszRovarja = r;
        this.rovaraszRovarja.setHelyzet(t);
    }

    public int calcAllTapanyagScore() {
        return 0;   //TODO szekvenciák alapján implementálni
    }

    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        CallTracer.enter("lep", "Rovar", "celTekton:Tekton");
        boolean val = r.lep(celTekton);
        if (val) {
            CallTracer.exit("lep", "true");
        } else {
            CallTracer.exit("lep", "false");
        }
        return val;
    }

    public boolean fonalVagas(Rovar r, Gombafonal gf) {
        //TODO szekvenciák alapján implementálni
        if(!r.getTudVagni()) return false;
        else{
            CallTracer.enter("vag", "Rovar", "gf");
            r.vag(gf);
            CallTracer.exit("vag", "true");
            return true;
        }
    }
}
