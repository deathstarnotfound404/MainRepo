package FungoriumClasses;

/**
 * A {@code Spora} osztály a spórák egy alapvető típusát reprezentálja a játékban.
 * Ez a spóra nem fejt ki semmilyen hatást a rovarokra.
 *
 * @author Pünkösti Györk
 * @version 1.0
 * @since 2025-03-19
 */
public class Spora extends BaseSpora {
    /**
     * Létrehoz egy új {@code Spora} objektumot.
     */
    public Spora() {
        super();
    }

    /**
     * A spóra hatása a rovarokra.
     * Ez a metódus üres, mert ez a spóra nem fejt ki semmilyen hatást.
     *
     * @param r A rovar, amelyre a spóra hatna (de jelen esetben nincs hatás).
     */
    @Override
    public void hatas(Rovar r) {
        //Implementáció később
    }
}
