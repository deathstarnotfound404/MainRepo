package FungoriumClasses;

public abstract class BaseSpora implements IDestroyable {
    //az alapertelmezett erteke annak, hogy egy spora mennyi tapanyagot tartalmaz
    protected int tapanyag = 1;

    //Konstructor
    public BaseSpora() {
    }

    //Absztrakt metodus, amit az alosztalyok fognak implementalni a hatas miatt
    public abstract void hatas(Rovar r);

    //tapanyag erteket visszaterito getter
    public int getTapanyag() {
        return tapanyag;
    }

    //A tapanyag erteket beallito setter metodus
    public void setTapanyag(int t) {
        tapanyag = t;
    }

    @Override
    public void elpusztul() {
    }
}