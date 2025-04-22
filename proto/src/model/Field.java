package model;
import java.util.*;

public class Field {
    private List<Tekton> tektonLista;
    private List<Player> playerLista;

    public Field() {}

    public void addPlayer(Player p) {
        playerLista.add(p);
    }

    public void addTekton(Tekton t) {
        tektonLista.add(t);
    }

    public List<Player> kiertekeles() {
        //TODO
        return null;
    }

    public void setAllTektonSzomszed() {
        //TODO
    }
}