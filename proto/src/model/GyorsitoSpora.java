package model;

public class GyorsitoSpora extends BaseSpora {
    public GyorsitoSpora() {
        super();
    }

    @Override
    public void hatas(Rovar r) {
        r.setMaxFogyasztas(true);
        r.setEvesHatekonysag(1);
    }
}
