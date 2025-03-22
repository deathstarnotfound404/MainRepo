package FungoriumClasses;


import CallTracer.CallTracer;

public class LassitoSpora extends BaseSpora {
    public LassitoSpora() {
        super();
    }

    //hatas kifejtese
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setMaxFogyasztas", "Rovar", "false");
        r.setMaxFogyasztas(false);
        CallTracer.exit("setMaxFogyasztas", "");
        CallTracer.enter("setEvesHatekonysag", "Rovar", "0.25");
        //TODO: r.setEvesHatekonysag(0.25)
        CallTracer.exit("setEvesHatekonysag", "");
    }
}
