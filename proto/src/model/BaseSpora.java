package model;

public abstract class BaseSpora implements IDestroyable {
    protected int tapanyag = 1;
    protected int id;
    protected static int idCounter;

    public BaseSpora() {
        // konstruktor
    }

    public abstract void hatas(Rovar r);

    public int getTapanyag() { return tapanyag; }
    public void setTapanyag(int val) { this.tapanyag = val; }
    public int getId() { return id; }
    public void elpusztul() {}
}