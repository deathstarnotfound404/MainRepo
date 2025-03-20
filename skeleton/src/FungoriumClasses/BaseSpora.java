package FungoriumClasses;

public abstract class BaseSpora implements IDestroyable {
    //az alapertelmezett erteke annak, hogy egy spora mennyi tapanyagot tartalmaz
    protected int tapanyag = 1;

    //Konstructor
    public BaseSpora() {
        System.out.println("<<<return BaseSpora()");
    }

    //Absztrakt metodus, amit az alos
    public abstract void hatas(Rovar r);

    public int getTapanyag() {
        System.out.println("<<<return getTapanyag()");
        return tapanyag;
    }

    //A tapanyag erteket beallito setter metodus
    public void setTapanyag(int t) {
        tapanyag = t;
        System.out.println("<<<return setTapanyag()");
    }

    @Override
    public void elpusztul() {
        System.out.println("<<<retrun elpusztul()");
    }
}