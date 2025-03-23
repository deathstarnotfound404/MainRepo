package FungoriumClasses;


/**
 * A {@code GombaTest} osztály egy gombatestet reprezentál a játékban.
 * Tartalmazza a kapcsolódó gombát, a spórakészletet, a szintet és a szórási számlálót.
 */
public class GombaTest implements IDestroyable {
    private Gomba AlapGomba;
    private int szint = 1;
    private int sporaKeszlet;
    private int szorasCount = 0;

    /**
     * Létrehoz egy új {@code GombaTest} objektumot a megadott gombához és kezdeti spóraszámmal.
     *
     * @param g              A gombához tartozó {@code Gomba} objektum.
     * @param kezdoSporaSzam A kezdeti spórák száma.
     */
    public GombaTest(Gomba g, int kezdoSporaSzam) {
        this.AlapGomba = g;
        this.sporaKeszlet = kezdoSporaSzam;
    }

    /**
     * Beállítja az alap gombát.
     *
     * @param g Az új {@code Gomba} objektum.
     */
    public void setAlapGomba(Gomba g) {
        this.AlapGomba = g;
    }

    /**
     * Visszaadja az alap gombát.
     *
     * @return Az alap {@code Gomba} objektum.
     */
    public Gomba getAlapGomba() {
        return this.AlapGomba;
    }

    /**
     * Visszaadja a jelenlegi spórakészletet.
     *
     * @return A spórák száma.
     */
    public int getSporaKeszlet() {
        return this.sporaKeszlet;
    }

    /**
     * Visszaadja a szórások számát.
     *
     * @return A szórások száma.
     */
    public int getSzorasCount() {
        return this.szorasCount;
    }

    /**
     * Visszaadja a gombatest aktuális szintjét.
     *
     * @return A szint értéke.
     */
    public int getSzint() {
        return this.szint;
    }

    /**
     * Növeli a spórakészletet egy adott értékkel.
     *
     * @param val A növelés mértéke.
     */
    public void addToSporaKeszlet(int val) {
        this.sporaKeszlet += val;
    }

    /**
     * Növeli a szórások számát egy adott értékkel.
     *
     * @param val A növelés mértéke.
     */
    public void addSzorasCount(int val) {
        this.szorasCount += val;
    }

    /**
     * Szintlépést hajt végre.
     *
     * @param szorandoMennyiseg A szórandó mennyiség a szintlépés előtt.
     */
    public void szintlepes(int szorandoMennyiseg) {
        szint += 1;
    }

    /**
     * Egy egységgel növeli a spórakészletet egy egységgel.
     */
    public void increaseSporaKeszlet() {
        this.sporaKeszlet += 1;
    }

    /**
     * Csökkenti a spórakészletet egy egységgel, ha van elég spóra.
     *
     * @return {@code true}, ha sikerült csökkenteni a spórakészletet, különben {@code false}.
     */
    public boolean decreaseSporaKeszlet() {
        if (sporaKeszlet <= 0) {
            return false;
        } else {
            sporaKeszlet--;
            return true;
        }
    }

    /**
     * A szint alapján visszaadja a megfelelő spóraszorzót.
     *
     * @param szint A gombatest aktuális szintje.
     * @return A szorzó értéke. 1-es szintnél 1, 2-es szintnél 2, 3-as szintnél 3, egyébként 0.
     */
    public int sporaSzorzo(int szint) {
        if(szint == 1) {
            return 1;
        }

        if(szint == 2) {
            return 2;
        }

        if(szint == 3) {
            return 3;
        }

        return 0;
    }

    /**
     * A gombatest elpusztulását kezeli.
     */
    @Override
    public void elpusztul() {
        //Implementáció később
    }
}
