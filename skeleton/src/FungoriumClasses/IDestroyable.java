package FungoriumClasses;

/**
 * Az {@code IDestroyable} interfész olyan objektumok számára készült, amelyek elpusztíthatók a játék során.
 */
public interface IDestroyable {
    /**
     * Az objektum elpusztítását végrehajtó metódus.
     * Az implementáló osztályok határozzák meg a pontos működést.
     */
    public void elpusztul();
}
