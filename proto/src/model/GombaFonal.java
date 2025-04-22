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

    public Tekton getStartTekton() { return startTekton; }

    public Tekton getCelTekton() { return celTekton; }

    public Gomba getAlapGomba() { return alapGomba; }

    public boolean connectedToAlapGomba() {
        //TODO Gomba és Gombász után
        //Ez a fv kb annak felel meg amit beszéltönk, azaz adott gf paraméter folytonos e a GT ig

        return false;
    }

    public void setIsDestroyable(boolean value) {
        this.isDestroyable = value;
    }

    public boolean getIsDestroyable() {
        return this.isDestroyable;
    }
    public void setIsElragott(boolean value) {
        this.isElragott = value;
    }

    public boolean getIsElragott() {
        return this.isElragott;
    }

    public boolean rovarEves(Rovar r) {
        //TODO
    }

    public void elpusztul() {}
}