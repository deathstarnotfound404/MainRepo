package model;

public class LassitoSpora extends BaseSpora {
    public LassitoSpora() {
        super();
    }

    @Override
    public void hatas(Rovar r) {
        r.setEvesHatekonysag(0.25);
        r.setMaxFogyasztas(false);
    }
}
