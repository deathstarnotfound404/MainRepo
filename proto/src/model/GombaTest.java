package model;

public class GombaTest implements IDestroyable {
    private int sporaKeszlet;
    private int szorasCount = 0;
    private int szint = 1;
    private Gomba alapGomba;

    public GombaTest(Gomba g, int kezdoSporaSzam) {}
    public int getSporakeszlet() { return 0; }
    public int getSzorasCount() { return 0; }
    public void addSzorasCount(int val) {
        this.szorasCount += val;
    }
    public void addToSporaKeszlet() {
        switch (szint) {
            case 1:
                sporaKeszlet += 2;
                break;

            case 2:
                sporaKeszlet += 3;
                break;

            case 3:
                sporaKeszlet += 4;
                break;

            default:
                break;
        }
    }

    public void addToSporaKeszlet(int val) {
        sporaKeszlet += val;
    }

    public int sporaSzorzo(int szint) { return 0; }
    public int getSzint() { return 0; }
    public void szintlepes(int szorandoMennyiseg) {}
    public Gomba getAlapGomba() { return null; }
    public void setAlapGomba(Gomba g) {}
    public void increaseSporakeszlet() {}
    public boolean decreaseSporakeszlet(int val) {
        if(sporaKeszlet >= val) {
            sporaKeszlet -= val;
            return true;
        } else {
            return false;
        }
    }
    public int getId() { return 0; }
    public void elpusztul() {}
}