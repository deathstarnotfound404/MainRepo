package model;

/**
 * Az interfész, amelyet minden olyan osztálynak meg kell valósítania,
 * amely elpusztítható a játék során (pl. spóra, gombafonal, gomba, tekton stb.).
 */
public interface IDestroyable {
    /**
     * Az objektum elpusztítását végrehajtó metódus.
     * A megvalósítás során az objektumot el kell távolítani a játékból.
     */
    void elpusztul();
}
