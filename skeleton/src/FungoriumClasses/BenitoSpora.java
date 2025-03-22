package FungoriumClasses;

import CallTracer.CallTracer;

public class BenitoSpora extends BaseSpora {
    //BenitoSpora konstruktora
    public BenitoSpora() {
        super();
    }

    //a spora hatasanak kifejtese
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setMaxFogyasztas", "Rovar", "false");
        r.setMaxFogyasztas(false);
        CallTracer.exit("setMaxFogyasztas", "");
        CallTracer.enter("setEvesHatekonysag", "Rovar", "0");
        r.setEvesHatekonysag(0);
        CallTracer.exit("setEvesHatekonysag", "");
    }
}
