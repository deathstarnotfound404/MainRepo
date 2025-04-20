package model;

public class GombaFonal implements IDestroyable {
    private Tekton startTekton;
    private Tekton celTekton;
    private boolean isDestroyable = true;
    private boolean isElragott = false;
    private Gomba alapGomba;
    private int id;

    public GombaFonal(Tekton startTekton, Tekton celTekton) {}
    public Tekton getStartTekton() { return null; }
    public Tekton getCelTekton() { return null; }
    public Gomba getAlapGomba() { return null; }
    public boolean connectedToAlapGomba() { return false; }
    public void setIsDestroyable(boolean value) {}
    public boolean getIsDestroyable() { return false; }
    public void setIsElragott(boolean value) {}
    public boolean getIsElragott() { return false; }
    public boolean rovarEves(Rovar r) { return false; }
    public void elpusztul() {}
}