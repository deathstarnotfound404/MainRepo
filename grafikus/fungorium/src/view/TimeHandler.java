package view;
import javax.swing.*;
import java.util.concurrent.*;

/**
 * Az időzített feladatok kezelésére szolgáló segédosztály.
 * Ez az osztály biztonságos módon ütemezi az időzített végrehajtásokat,
 * biztosítva a szálbiztosságot és a Swing EDT-vel való kompatibilitást.
 */
public class TimeHandler {
    /** Az időzítésekért felelős ütemező szolgáltatás */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Egyszer végrehajtandó feladat ütemezése késleltetéssel.
     * Az ütemezett feladat a megadott objektum zárolása mellett fut,
     * így biztosítva a szálbiztonságot.
     *
     * @param action A végrehajtandó feladat
     * @param delayMillis A késleltetés ezredmásodpercben
     * @param lockObject A zároláshoz használt szinkronizációs objektum
     */
    public void schedule(Runnable action, long delayMillis, Object lockObject) {
        scheduler.schedule(() -> {
            synchronized (lockObject) {
                action.run();
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Ismétlődő feladat ütemezése adott időközönként.
     * A feladat a Swing eseményszálán (EDT) fut, így biztosítva a
     * GUI komponensekkel való biztonságos munkát. A feladat végrehajtása
     * a megadott objektum zárolása mellett történik.
     *
     * @param action Az ismétlődő feladat
     * @param initialDelayMillis A kezdeti késleltetés ezredmásodpercben
     * @param periodMillis Az ismétlődés időköze ezredmásodpercben
     * @param lockObject A zároláshoz használt szinkronizációs objektum
     */
    public void scheduleAtFixedRate(Runnable action, long initialDelayMillis, long periodMillis, Object lockObject) {
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (lockObject) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        action.run();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }, initialDelayMillis, periodMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Leállítja az ütemezőt és felszabadítja az erőforrásokat.
     * Ezt a metódust kell meghívni, amikor az időkezelő már nem szükséges,
     * hogy elkerüljük az erőforrás szivárgást.
     */
    public void shutdown() {
        scheduler.shutdown();
    }
}