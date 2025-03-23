package FungoriumClasses;

/**
 * A TektonHatas osztály egy tektonhoz társított hatást reprezentál.
 * Az osztály lehetőséget biztosít egy tekton tárolására és egy alapértelmezett hatás visszaadására.
 *
 * @author Bekő Máté
 * @version 1.0
 * @since 2025-03-18
 */
public class TektonHatas {

    private Tekton tekton;

    /**
     * Konstruktor, amely létrehoz egy TektonHatas példányt.
     */
    public TektonHatas() {

    }

    /**
     * Visszaadja a tekton hatását.
     * Az alapértelmezett implementáció "Base" értéket ad vissza.
     *
     * @return A tekton hatását reprezentáló szöveg.
     */
    public String hatas() {
        return "Base";  //Alapértelmezett működés
    }

    /**
     * Beállítja a hozzá tartozó tekton objektumot.
     *
     * @param t A beállítandó tekton.
     */
    public void setTekton(Tekton t) {
        this.tekton = t;
    }

    /**
     * Visszaadja a társított tekton objektumot.
     *
     * @return A társított tekton, vagy {@code null}, ha nincs beállítva.
     */
    public Tekton getTekton() {
        return this.tekton;
    }
}
