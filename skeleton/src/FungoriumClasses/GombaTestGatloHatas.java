package FungoriumClasses;
import CallTracer.*;

/**
 * A {@code GombaTestGatloHatas} osztály egy olyan hatást reprezentál,
 * amely gátolja a gombatest fejlődését egy adott {@code Tekton}-on.
 */
public class GombaTestGatloHatas extends TektonHatas {
    /**
     * Létrehoz egy új {@code GombaTestGatloHatas} objektumot.
     */
    public GombaTestGatloHatas() {
        super();
    }

    /**
     * Alkalmazza a gátló hatást a kapcsolódó {@code Tekton} objektumra.
     * A hatás során a {@code Tekton} állapota megváltozik úgy,
     * hogy jelezze: gombatest jelen van rajta.
     *
     * @return A hatás neve: {@code "GombaTestGatlo"}.
     */
    public String hatas() {
        Tekton t1 = this.getTekton();
        CallTracer.enter("setVanGombaTest", "Tekton", "true");
        t1.setVanGombaTest(true);
        CallTracer.exit("setVanGombaTest", "");
        return "GombaTestGatlo";
    }
}
