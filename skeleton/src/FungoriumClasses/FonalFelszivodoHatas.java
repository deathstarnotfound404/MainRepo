package FungoriumClasses;

import CallTracer.CallTracer;

public class FonalFelszivodoHatas extends TektonHatas{

    public FonalFelszivodoHatas() {

    }

    public String hatas() {
        Tekton t1 = this.getTekton();
        CallTracer.enter("fonalakFelszivasa", "Tekton", "");
        t1.fonalakFelszivasa();
        CallTracer.exit("fonalakFelszivasa", "");
        return "Felszivas";
    }
}
