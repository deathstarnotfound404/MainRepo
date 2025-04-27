package model;
import java.util.*;

/**
 * Represents an Entomologist player in the game.
 * The Rovarasz (Entomologist) controls insects and manages their actions,
 * such as movement, thread cutting, and resource gathering. This player's
 * score is determined by the total amount of nutrients collected by all
 * their insects.
 * Extends the Player base class.
 */
public class Rovarasz extends Player {
    /** List of insects controlled by this player */
    private List<Rovar> rovarLista;

    /**
     * Constructs a new Rovarasz player with a given name.
     * Initializes an empty list to store controlled insects.
     *
     * @param name the player's name
     */
    public Rovarasz(String name) {
        super(name);
        rovarLista = new ArrayList<>();
    }

    /**
     * Adds an insect to a specific Tekton cell and to this player's control.
     * The insect is only added if the target Tekton is available.
     *
     * @param r the insect to add
     * @param t the Tekton where the insect should be placed
     * @return true if the insect was successfully added, false otherwise
     */
    public boolean addRovar(Rovar r, Tekton t) {
        if(t.setRovar(r)) {
            this.rovarLista.add(r);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the total nutrients collected by all insects under this player's control.
     * This is used to determine the player's score.
     *
     * @return the sum of all nutrients collected by this player's insects
     */
    public int calcAllTapanyagScore() {
        int sumTapanyag = 0;
        for(Rovar r : rovarLista) {
            sumTapanyag += r.getTapanyag();
        }
        return sumTapanyag;
    }

    /**
     * Commands an insect to cut a fungal thread if possible.
     * The insect must have the cutting ability enabled.
     *
     * @param r the insect to perform the cutting
     * @param gf the fungal thread to cut
     * @return true if the thread was successfully cut, false otherwise
     */
    public boolean fonalVagas(Rovar r, GombaFonal gf) {
        if (r.getTudVagni()) {
            return r.vag(gf);
        } else {
            return false;
        }
    }

    /**
     * Returns the list of insects controlled by this player.
     *
     * @return the list of controlled insects
     */
    public List<Rovar> getRovarLista() {
        return rovarLista;
    }

    /**
     * Removes an insect from this player's control and from its current Tekton.
     *
     * @param r the insect to remove
     */
    public void removeRovar(Rovar r) {
        if(r.getHelyzet().getRovar() != null) {
            r.getHelyzet().setRovar(null);
        }
        rovarLista.remove(r);
    }

    /**
     * Commands an insect to move to a target Tekton.
     * Movement is only possible if the target is a neighbor and connected by fungal threads.
     *
     * @param r the insect to move
     * @param celTekton the destination Tekton
     * @return true if the movement was successful, false otherwise
     */
    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        return r.lep(celTekton);
    }

    /**
     * Calculates the player's score based on nutrients collected.
     * Implements the abstract method from the Player class.
     *
     * @return the player's current score
     */
    @Override
    public int getScoreFromPlayer(){
        return calcAllTapanyagScore();
    }

    /**
     * Finds and returns an insect with the specified ID.
     *
     * @param id the ID of the insect to find
     * @return the matching insect, or null if no insect with that ID was found
     */
    public Rovar getRovarById(int id) {
        for(Rovar r : rovarLista) {
            if(r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}