package model;
import java.util.*;

public class Field {
    public static int idCounter = 0;
    private List<Tekton> tektonLista;
    private List<Player> playerLista;

    //TODO játék állapótának kiírásá fv

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

    public static int genID() {
        ++idCounter;
        return idCounter;
    }
}