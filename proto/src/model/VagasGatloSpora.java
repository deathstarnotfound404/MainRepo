package model;

public class VagasGatloSpora extends BaseSpora {
    public VagasGatloSpora() {
        super();
    }

    @Override
    public void hatas(Rovar r) {
        r.setTudVagni(false);
    }
}
