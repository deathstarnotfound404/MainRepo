package model;

public class GombaFonal implements IDestroyable {
    private int id;
    private Tekton startTekton;
    private Tekton celTekton;
    private boolean isDestroyable = true;
    private boolean isElragott = false;
    private Gomba alapGomba;

    public GombaFonal(Gomba alapgomba, Tekton startTekton, Tekton celTekton) {
        Field.genID();
        this.alapGomba = alapgomba;
        this.startTekton = startTekton;
        this.celTekton = celTekton;
    }

    public int getID(){
        return id;
    }

    public Tekton getStartTekton() {
        return startTekton;
    }

    public Tekton getCelTekton() {
        return celTekton;
    }

    public Gomba getAlapGomba() {
        return alapGomba;
    }

    public boolean connectedToAlapGomba() {
        //TODO - ellenőrizni a fonalfolytonossagVizsgalat(gf) függvényt
        return alapGomba.fonalFolytonossagVizsgalat(this);
    }

    public void setDestroyable(boolean value) {
        this.isDestroyable = value;
    }

    public boolean IsDestroyable() {
        return this.isDestroyable;
    }
    public void setElragott(boolean value) {
        this.isElragott = value;
    }

    public boolean IsElragott() {
        return this.isElragott;
    }

    public boolean rovarEves(Rovar r) {
        if(r.getEvesHatekonysag()!=0) {
            return false;
        } else {
            Tekton rovarHelyzet = r.getHelyzet();
            return this.getAlapGomba().getGombasz().gombatestNovesztes(rovarHelyzet, true);
        }
    }

    public void elpusztul() {}
}
