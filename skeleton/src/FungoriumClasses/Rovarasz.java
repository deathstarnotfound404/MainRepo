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
        this.rovaraszRovarja = new Rovar();
        this.rovaraszRovarja.setHelyzet(t); //TODO Pontosan ellenőrizni szekvenciák alapján, hogyan történik ez a folyamat.
    }

    public int calcAllTapanyagScore() {
        return 0;   //TODO szekvenciák alapján implementálni
    }

    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        return false;   //TODO szekvenciák alapján implementálni
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
