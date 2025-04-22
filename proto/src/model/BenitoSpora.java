package model;

public class BenitoSpora extends BaseSpora {

    public BenitoSpora() {
        super();
    }

    @Override
    public void hatas(Rovar r) {
        r.setMaxFogyasztas(false);
        r.setEvesHatekonysag(0);
    }
}
