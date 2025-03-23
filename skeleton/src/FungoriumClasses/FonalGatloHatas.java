package FungoriumClasses;
import CallTracer.*;

/**
 * A {@code FonalGatloHatas} osztály egy speciális {@link TektonHatas}, amely meggátolja a további fonalak növesztését az adott Tektonon.
 *
 * <p>Amikor a hatás aktiválódik, a tekton {@code fokszam} értékét lekéri,
 * és ennek alapján eldönti, hogy van-e gátló fonal a tektonon.</p>
 *
 * <p>Kapcsolódó osztályok:</p>
 * <ul>
 *     <li>{@link TektonHatas} - Az osztály, amelyből ez az osztály származik.</li>
 *     <li>{@link Tekton} - Az osztály, amelyen a hatás végrehajtódik.</li>
 *     <li>{@link CallTracer} - A híváskövetési mechanizmust biztosító osztály.</li>
 * </ul>
 *
 * @author NAME
 * @version 1.0
 * @since 2025-03-18
 */
public class FonalGatloHatas extends TektonHatas{

    /**
     * Létrehoz egy új {@code FonalGatloHatas} objektumot.
     */
    public FonalGatloHatas() {
        super();
    }

    /**
     * Kifejti a hatást az adott tektonon.
     *
     * <p>A metódus először lekéri a hatáshoz tartozó {@link Tekton} objektumot,
     * majd meghívja rajta a {@code getFokszam()} metódust.</p>
     *
     * @return A hatás eredményétől függően "FonalGatlo" vagy "NincsFonalGatlo" szöveget ad vissza, amit majd a kontroller tudtára kell adni.
     */
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
