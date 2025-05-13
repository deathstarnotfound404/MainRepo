package view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Egyszerű DocumentListener implementáció, amely egy adott műveletet hajt végre
 * bármilyen dokumentum változás esetén. Ez az osztály egyszerűsíti a Swing szövegmezők
 * változásainak kezelését egy lambda kifejezés vagy metódus referencia alkalmazásával.
 */
public class SimpleDocumentListener implements DocumentListener {
    /** A dokumentum változásakor végrehajtandó művelet */
    private final Runnable onChange;

    /**
     * Létrehoz egy új dokumentum figyelőt a megadott művelettel.
     *
     * @param onChange A dokumentum változásakor végrehajtandó művelet
     */
    public SimpleDocumentListener(Runnable onChange) {
        this.onChange = onChange;
    }

    /**
     * Meghívódik, amikor szöveg kerül beszúrásra a dokumentumba.
     * Végrehajtja a konstruktorban megadott műveletet.
     *
     * @param e Az esemény objektum, amely tartalmazza a változás adatait
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        onChange.run();
    }

    /**
     * Meghívódik, amikor szöveg kerül törlésre a dokumentumból.
     * Végrehajtja a konstruktorban megadott műveletet.
     *
     * @param e Az esemény objektum, amely tartalmazza a változás adatait
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        onChange.run();
    }

    /**
     * Meghívódik, amikor a dokumentum tulajdonságai megváltoznak.
     * Végrehajtja a konstruktorban megadott műveletet.
     *
     * @param e Az esemény objektum, amely tartalmazza a változás adatait
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        onChange.run();
    }
}