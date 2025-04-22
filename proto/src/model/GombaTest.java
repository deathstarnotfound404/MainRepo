package model;

public class GombaTest implements IDestroyable {
    private int id;
    private int sporaKeszlet = 0;
    private int szorasCount = 0;
    private int szint = 1;
    private Gomba alapGomba;

    public GombaTest(Gomba g, int kezdoSporaSzam) {
        id = Field.genID();
        alapGomba = g;
        sporaKeszlet = kezdoSporaSzam;
    }

    public int getSporakeszlet() {
        return sporaKeszlet;
    }

    public int getSzorasCount() {
        return szorasCount;
    }

    public void addSzorasCount(int val) {
        this.szorasCount += val;
    }

    public void addToSporaKeszletTermelessel() {
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

    public int sporaSzorzo(int szint) {
        int szorandoMennyiseg = 0;
        switch (szint){
            case 1:
                decreaseSporakeszlet(2);
                szorandoMennyiseg = 1;
                break;

            case 2:
                decreaseSporakeszlet(1);
                szorandoMennyiseg = 2;
                break;

            case 3:
                szorandoMennyiseg = 3;
                break;
        }

        return szorandoMennyiseg;
    }

    public int getSzint() {
        return szint;
    }

    public void szintlepes(int szorandoMennyiseg) {
        //TODO
    }

    public Gomba getAlapGomba() {
        return alapGomba;
    }

    public void setAlapGomba(Gomba g) {
        alapGomba = g;
    }

    public void increaseSporakeszlet() {
        sporaKeszlet++;
    }

    public boolean decreaseSporakeszlet(int val) {
        if(sporaKeszlet >= val) {
            sporaKeszlet -= val;
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void elpusztul() {
        //TODO
    }

}