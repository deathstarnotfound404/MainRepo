package FungoriumClasses;

public class GombaTest implements IDestroyable {
    private Gomba AlapGomba;
    private int szint = 1;
    private int sporaKeszlet;
    private int szorasCount = 0;

    public GombaTest(Gomba g, int kezdoSporaSzam) {
        System.out.println("<<<return GombaTest()");
    }

    public void setAlapGomba(Gomba g) {
        System.out.println("<<<return setAlapGomba()");
    }

    public Gomba getAlapGomba() {
        System.out.println("<<<return getAlapGomba()");
        return null;
    }

    public int getSporaKeszlet() {
        System.out.println("<<<return getSporaKeszlet()");
        return 0;
    }

    public int getSzorasCount() {
        System.out.println("<<<return getSzorasCount()");
        return 0;
    }

    public int getSzint() {
        System.out.println("<<<return getSzint()");
        return 0;
    }

    public void addToSporaKeszlet(int val) {
        System.out.println("<<<return addToSporaKeszlet()");
    }

    public void addSzorasCount(int val) {
        System.out.println("<<<return addSzorasCount()");
    }

    public void szintlepes(int szorandoMennyiseg) {
        System.out.println("<<<return szintlepes()");
    }

    public void increaseSporaKeszlet() {
        System.out.println("<<<return increaseSporaKeszlet()");
    }

    public boolean decreaseSporaKeszlet() {
        System.out.println("<<<return decreaseSporaKeszlet()");
        return false;
    }

    public void sporaSzorzo(int szint) {
        System.out.println("<<<return sporaSzorzo()");
    }

    @Override
    public void elpusztul() {
        System.out.println("<<<return elpusztul()");
    }
}
