package FungoriumClasses;

/**
 * A {@code BaseSpora} absztrakt osztály a spóra objektumok alapvető tulajdonságait és működését határozza meg.
 *
 * <p>Az osztály egy spóra alapvető tápanyagértékét tárolja, valamint tartalmazza a spórák hatásának és elpusztulásának kezelését.</p>
 *
 * <p>Az osztály az {@link IDestroyable} interfészt valósítja meg, így minden spóra elpusztítható.</p>
 *
 * @author NAME
 * @version 1.0
 * @since 2025-03-19
 */

public abstract class BaseSpora implements IDestroyable {
    /** Az alapértelmezett tápanyagérték, amelyet egy spóra tartalmaz. */
    protected int tapanyag = 1;

    /**
     * Létrehoz egy új {@code BaseSpora} objektumot alapértelmezett beállításokkal.
     */
    public BaseSpora() {
    }

    /**
     * Meghatározza a spóra hatását egy adott rovarra.
     *
     * <p>Ezt a metódust az alosztályok implementálják a konkrét hatások meghatározására.</p>
     *
     * @param r A rovar, amelyre a spóra hatással lesz.
     */
    public abstract void hatas(Rovar r);

    /**
     * Visszaadja a spóra aktuális tápanyagértékét.
     *
     * @return A spóra tápanyagértéke.
     */
    public int getTapanyag() {
        return tapanyag;
    }

    /**
     * Beállítja a spóra tápanyagértékét.
     *
     * @param t Az új tápanyagérték.
     */
    public void setTapanyag(int t) {
        tapanyag = t;
    }

    /**
     * A spóra elpusztításának metódusa.
     *
     * <p>Ez az implementáció jelenleg üres, de az alosztályok felülírhatják a megfelelő működés érdekében.</p>
     */
    @Override
    public void elpusztul() {}
}