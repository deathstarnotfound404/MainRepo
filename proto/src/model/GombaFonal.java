package model;

public class GombaFonal implements IDestroyable {
    private int id;
    private Tekton startTekton;
    private Tekton celTekton;
    private boolean isDestroyable = true;
    private boolean isElragott = false;
    private Gomba alapGomba;

    public GombaFonal(Tekton startTekton, Tekton celTekton) {
        Field.genID();
        this.startTekton = startTekton;
        this.celTekton = celTekton;
    }

    public int getID(){
        return id;
    }

    public Tekton getStartTekton() { return startTekton; }

    public Tekton getCelTekton() { return celTekton; }

    public Gomba getAlapGomba() { return alapGomba; }

    public boolean connectedToAlapGomba() {
        return alapGomba.fonalFolytonossagVizsgalat(this);
    }

    public void setIsDestroyable(boolean value) {
        this.isDestroyable = value;
    }

    public boolean getIsDestroyable() {
        return this.isDestroyable;
    }
    public void setElragott(boolean value) {
        this.isElragott = value;
    }

    public boolean IsElragott() {
        return this.isElragott;
    }

    public boolean rovarEves(Rovar r) {
        if()
        //TODO - Rovar után
    }

    public void elpusztul() {}
}