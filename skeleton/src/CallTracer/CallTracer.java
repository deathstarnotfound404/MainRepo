package CallTracer;

/**
 * A {@code CallTracer} osztály egy egyszerű hívásnyomkövető és logoló eszköz,
 * amely vizuálisan követi a függvényhívások mélységét és a visszatérési értékeket.
 *
 * <p>Az osztály segít a program végrehajtásának megértésében, különösen rekurzív vagy mélyen egymásba ágyazott hívások esetén.</p>
 *
 * <p><b>Használat:</b></p>
 * <pre>
 * {@code
 * CallTracer.enter("metodusNeve", "OsztalyNeve", "paraméterek");
 * // Metódus logikája...
 * CallTracer.exit("metodusNeve", "visszatérési érték");
 * }
 * </pre>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-20
 */
public class CallTracer {
    /** A függvényhívások mélységét követő számláló. */
    private static int callDepth = 0;

    /** A hívási szintek vizuális megkülönböztetésére szolgáló tabulátor. */
    private static final String INDENTATION = "\t"; // Egy szint indentáció

    /**
     * Logol egy függvényhívás megkezdését, és kiírja a hívás részleteit.
     *
     * @param functionName a meghívott függvény neve
     * @param className az függvényt tartalmazó osztály neve
     * @param params a függvényhívás paraméterei
     */
    public static void enter(String functionName, String className, String params) {
        System.out.println(getIndent() + ">>>[" + className + "]." + functionName + "(" + params + ")");
        callDepth++;
    }

    /**
     * Visszatér egy függvényhívás végén, és kiírja a visszatérési értéket.
     *
     * @param functionName a befejezett függvény neve
     * @param returnValue a függvény visszatérési értéke
     */
    public static void exit(String functionName, String returnValue) {
        callDepth--; // Visszatérés, csökkentjük a mélységet
        System.out.println(getIndent() + "<<<return " + functionName + " -> " + returnValue);
    }

    /**
     * Elkészíti az aktuális mélységhez tartozó indentációt.
     *
     * @return az aktuális hívási mélységhez tartozó tabulált string
     */
    private static String getIndent() {
        return INDENTATION.repeat(callDepth);
    }
}

