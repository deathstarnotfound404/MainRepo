package TestCases;
/**
 * Az {@code ITestCase} interfész egy teszteset végrehajtásának alapvető szerkezetét határozza meg.
 *
 * <p>Az interfészt implementáló osztályoknak biztosítaniuk kell a teszteset futtatásának mechanizmusát.</p>
 *
 * @author Czene Zsombor
 * @version 1.0
 * @since 2025-03-20
 */
public interface ITestCase {
    /**
     * A teszteset végrehajtását definiáló metódus.
     *
     * <p>Az implementáció a konkrét teszteset lépéseit tartalmazza.</p>
     */
    public void runTest();
}
