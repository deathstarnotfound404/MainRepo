package model;

/**
 * Interface representing objects that can be destroyed within the game.
 * Any class implementing this interface must define behavior for when
 * the object is destroyed or removed from the game.
 */
public interface IDestroyable {

    /**
     * Defines the behavior when this object is destroyed.
     * Implementing classes should handle cleanup tasks, resource release,
     * and any notifications needed when the object is removed from the game.
     */
    void elpusztul();
}