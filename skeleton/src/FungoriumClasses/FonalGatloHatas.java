package FungoriumClasses;

import CallTracer.CallTracer;

public class FonalGatloHatas extends TektonHatas{
    public FonalGatloHatas() {
    }

    public String hatas() {
        Tekton t1 = this.getTekton();
        CallTracer.enter("getFokszam", "Tekton", "");
        int val = t1.getFokszam();
        CallTracer.exit("getFokszam", "val:int");
        if(val == 0) {
            return "NincsFonalGatlo";
        } else {
            return "FonalGatlo";
        }
    }
}
