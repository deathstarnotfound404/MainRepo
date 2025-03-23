package TestCases;
import CallTracer.*;
import java.util.*;

/**
 * A {@code TestCase} egy absztrakt osztály, amely egy teszteset alapstruktúráját biztosítja.
 *
 * <p>Ez az osztály tartalmazza a tesztesetekhez szükséges közös funkcionalitásokat,
 * például a híváskövetést és a felhasználói döntések kezelését.</p>
 *
 * <p><b>Kapcsolódó osztályok:</b></p>
 * <ul>
 *   <li>{@link CallTracer} - Híváskövetés és logolás.</li>
 * </ul>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-20
 */
public abstract class TestCase {
    /** A tesztesethez kapcsolódó híváskövető. */
    CallTracer callTracer;

    /**
     * Létrehoz egy új {@code TestCase} objektumot egy adott hívásnyomkövető példánnyal.
     *
     * @param callTracer a híváskövetéshez használt objektum
     */
    public TestCase(CallTracer callTracer) {
        this.callTracer = callTracer;
    }

    /**
     * Felhasználói döntést kér a konzolon keresztül egy adott opciólistából.
     *
     * <p>A metódus kiírja a döntési lehetőségeket, majd bekér a felhasználótól egy érvényes választást.
     * A bevitt adatot validálja, és csak számként megadott, érvényes opciókat fogad el.</p>
     *
     * @param dontes a döntés szöveges leírása
     * @param actions az elérhető lehetőségek szöveges listája
     * @return a felhasználó által kiválasztott opció sorszáma
     */
    public int makeDecision(String dontes, List<String> actions) {
        System.out.println("Decision: " + dontes);
        int i = 1;
        for (String act : actions) {
            System.out.println("\t" + i + "; " + act);
            i+=1;
        }

        int choice = 0;
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        while (!done) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= actions.size()) {
                    done = true;
                } else {
                    System.out.println("<< Error: Please choose a valid decision");
                }
            } catch (NumberFormatException e) {
                System.out.println("<< Error: Invalid input format");
            }
        }
        return choice;
    }
}
