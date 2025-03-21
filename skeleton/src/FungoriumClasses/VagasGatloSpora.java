package FungoriumClasses;

import CallTracer.CallTracer;

public class VagasGatloSpora extends BaseSpora {
    //VagasGatloSpora konstruktora
    public VagasGatloSpora() {
        super();
    }

    //hatas kifejtese
    @Override
    public void hatas(Rovar r) {
        CallTracer.enter("setTudVagni", "Rovar", "false");
        r.setTudVagni(false);
        CallTracer.exit("setTudVagni", "");
    }
}
