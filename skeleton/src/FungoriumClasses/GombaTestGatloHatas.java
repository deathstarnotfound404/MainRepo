package FungoriumClasses;
import CallTracer.CallTracer;

public class GombaTestGatloHatas extends TektonHatas {
    public GombaTestGatloHatas() {
        super();
    }

    public String hatas() {
        Tekton t1 = this.getTekton();
        CallTracer.enter("setVanGombaTest", "Tekton", "true");
        t1.setVanGombaTest(true);
        CallTracer.exit("setVanGombaTest", "");
        return "GombaTestGatlo";
    }
}
