package model;

import java.util.*;
import java.util.concurrent.*;

/**
 * Az időzített események kezeléséért felelős osztály.
 * Az model.IDestroyable interfészt megvalósító objektumokat egy adott idő után elpusztítja.
 */
public class TimeHandler {
    private final Map<IDestroyable, Long> idozitettObjektumok = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final long ellenorzoIntervallum = 1000; // milliszekundumban

    /**
     * Konstruktor: elindítja az időzítő ellenőrző szálat, amely 1 másodpercenként figyeli az objektumokat.
     */
    public TimeHandler() {
        scheduler.scheduleAtFixedRate(this::ellenorzes, ellenorzoIntervallum, ellenorzoIntervallum, TimeUnit.MILLISECONDS);
    }

    /**
     * Új időzített eseményt regisztrál egy objektumhoz.
     *
     * @param obj  Az elpusztítható objektum
     * @param time Mennyi idő múlva pusztuljon el (ezredmásodpercben)
     */
    public void setTimer(IDestroyable obj, long time) {
        long lejaroIdo = System.currentTimeMillis() + time;
        idozitettObjektumok.put(obj, lejaroIdo);
    }

    /**
     * Privát metódus, amely ellenőrzi, hogy bármelyik regisztrált objektum ideje lejárt-e.
     * Ha igen, meghívja az elpusztul() metódust, majd eltávolítja azt a listából.
     */
    private void ellenorzes() {
        long aktualisIdo = System.currentTimeMillis();
        List<IDestroyable> lejartObjektumok = new ArrayList<>();

        for (Map.Entry<IDestroyable, Long> entry : idozitettObjektumok.entrySet()) {
            if (aktualisIdo >= entry.getValue()) {
                entry.getKey().elpusztul();
                lejartObjektumok.add(entry.getKey());
            }
        }

        for (IDestroyable obj : lejartObjektumok) {
            idozitettObjektumok.remove(obj);
        }
    }
}
