package FungoriumClasses;

public class GombaTest implements IDestroyable {
    private Gomba AlapGomba;
    private int szint = 1;
    private int sporaKeszlet;
    private int szorasCount = 0;

    public GombaTest(Gomba g, int kezdoSporaSzam) {
        this.AlapGomba = g;
        this.sporaKeszlet = kezdoSporaSzam;
    }

    public void setAlapGomba(Gomba g) {
        this.AlapGomba = g;
    }

    public Gomba getAlapGomba() {
        return this.AlapGomba;
    }

    public int getSporaKeszlet() {
        return this.sporaKeszlet;
    }

    public int getSzorasCount() {
        return this.szorasCount;
    }

    public int getSzint() {
        return this.szint;
    }

    public void addToSporaKeszlet(int val) {
        this.sporaKeszlet += val;
    }

    public void addSzorasCount(int val) {
        this.szorasCount += val;
    }

    public void szintlepes(int szorandoMennyiseg) {
        //TODO szekvenciák alapján
    }

    public void increaseSporaKeszlet() {
        this.sporaKeszlet += 1;
    }

    public boolean decreaseSporaKeszlet() {
        if (sporaKeszlet <= 0) {
            return false;
        } else {
            sporaKeszlet--;
            return true;
        }
    }

    public void sporaSzorzo(int szint) {
        //TODO követelmények alapján
    }

    @Override
    public void elpusztul() {
    }
}
