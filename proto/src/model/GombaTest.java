package model;

public class GombaTest implements IDestroyable {
    private int sporaKeszlet;
    private int szorasCount = 0;
    private int szint = 1;
    private Gomba alapGomba;

    public GombaTest(Gomba g, int kezdoSporaSzam) {}
    public int getSporakeszlet() { return 0; }
    public int getSzorasCount() { return 0; }
    public void addSzorasCount(int val) {}
    public void addToSporaKeszlet(int val) {}
    public int sporaSzorzo(int szint) { return 0; }
    public int getSzint() { return 0; }
    public void szintlepes(int szorandoMennyiseg) {}
    public Gomba getAlapGomba() { return null; }
    public void setAlapGomba(Gomba g) {}
    public void increaseSporakeszlet() {}
    public boolean decreaseSporakeszlet() { return false; }
    public int getId() { return 0; }
    public void elpusztul() {}
}