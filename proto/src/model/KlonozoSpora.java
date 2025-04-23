package model;

public class KlonozoSpora extends BaseSpora {
    public KlonozoSpora() {
        super();
    }

    @Override
    public void hatas(Rovar r) {
        //Ha null-t ad vissza akkor sikertelen, ha r rovart akkor sikeres
        r.klonozas();
    }
}
