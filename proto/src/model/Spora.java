package model;

public class Spora extends BaseSpora {
    public Spora() {
        super();
    }

    @Override
    public void hatas(Rovar r) {
        r.setMaxFogyasztas(false);
        r.setEvesHatekonysag(0.5);
    }
}
