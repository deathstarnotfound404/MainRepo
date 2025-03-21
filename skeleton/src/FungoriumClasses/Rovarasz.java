package FungoriumClasses;

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
        return false;       //TODO szekvenciák alapján implementálni
    }
}
