package CallTracer;

public class CallTracer {
    private static int callDepth = 0; // Nyomon követi a mélységet
    private static final String INDENTATION = "\t"; // Egy szint indentáció

    public static void enter(String functionName, String className, String params) {
        System.out.println(getIndent() + ">>>[" + className + "]." + functionName + "(" + params + ")");
        callDepth++; // Mélyül a hívási lánc
    }

    public static void exit(String functionName, String returnValue) {
        callDepth--; // Visszatérés, csökkentjük a mélységet
        System.out.println(getIndent() + "<<<return " + functionName + " -> " + returnValue);
    }

    private static String getIndent() {
        return INDENTATION.repeat(callDepth);
    }
}

