package FungoriumClasses;

import CallTracer.CallTracer;

public class GyorsitoSpora extends BaseSpora {
    //GyorsitoSpora konstruktora
    public GyorsitoSpora() {
        super();
    }

    //a spora hatasanak implementalasa
    @Override
    public void hatas(Rovar r) {
        //TODO szekvenciák alapján
        CallTracer.enter("setMaxFogyasztas", "Rovar", "true");
        r.setMaxFogyasztas(true);
        CallTracer.exit("setMaxFogyasztas", "");
    }
}
