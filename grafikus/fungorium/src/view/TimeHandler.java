package view;
import javax.swing.*;
import java.util.concurrent.*;

public class TimeHandler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * TimeHandler.java
     * ----------------
     * Ez az osztály időzített műveletek végrehajtására szolgál egy játékmodell esetében.
     * Garantálja, hogy a műveletek csak akkor futnak le, ha a modell (pl. Field) éppen nem használatban van,
     * azaz szinkronizált szakaszon kívül vagyunk.
     *
     * JDK 18-kompatibilis megoldás.
     */

    /**
     * Egy műveletet késleltetve hajt végre, szinkronizálva a megadott objektumra.
     *
     * @param action      A végrehajtandó művelet (pl. modellmódosítás)
     * @param delayMillis Késletés ezredmásodpercben
     * @param lockObject  Az az objektum, amit szinkronizálunk (pl. a Field példány)
     */
    public void schedule(Runnable action, long delayMillis, Object lockObject) {
        scheduler.schedule(() -> {
            synchronized (lockObject) {
                action.run();
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Ismétlődő időzített művelet, biztonságos Swing-gel:
     * - mindig az EDT-n történik a GUI frissítés (`invokeLater`)
     * - a modellre szinkronizál
     */
    /*
    public void scheduleAtFixedRate(Runnable action, long initialDelayMillis, long periodMillis, Object lockObject) {
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (lockObject) {
                SwingUtilities.invokeLater(action);
            }
        }, initialDelayMillis, periodMillis, TimeUnit.MILLISECONDS);
    }

     */

    public void scheduleAtFixedRate(Runnable action, long initialDelayMillis, long periodMillis, Object lockObject) {
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (lockObject) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        action.run();
                    } catch (Exception ex) {
                        ex.printStackTrace(); // ← VAGY logold GUI-n
                    }
                });
            }
        }, initialDelayMillis, periodMillis, TimeUnit.MILLISECONDS);
    }


    /**
     * A játék végén a scheduler leállítása.
     */
    public void shutdown() {
        scheduler.shutdown();
    }

    /**
     * Használati példa:
     *
     * Field field = new Field();
     * Gomba g = ...;
     * GombaFonal f = ...;
     *
     * TimeHandler handler = new TimeHandler();
     * handler.schedule(() -> g.deleteFonal(f), 10000, field);
     *
     * // Ez biztosítja, hogy a fonal törlése csak akkor fut le,
     * // ha a field épp nincs használatban máshol (pl. játékosparancs közben).
     *
     * // Példa a főszál által végrehajtott modellműveletre (pl. parancs feldolgozás):
     * synchronized (field) {
     *     rovar.lep(celTekton);
     *     gomba.addFonal(ujFonal);
     *     gomba.listazFonalak();
     * }
     *
     * // Ez garantálja, hogy az időzített események csak a főszál befejezése után futhatnak le.
     */
}
