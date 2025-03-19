package FungoriumClasses;

import java.util.List;
import java.util.ArrayList;

public class Field {
    private List<Tekton> tektonLista;
    private List<Player> players;

    public Field() {
        this.tektonLista = new ArrayList<>();
        this.players = new ArrayList<>();
        System.out.println("<<<return FIeld()");
    }

    List<Player> kiertekeles() {
        System.out.println("<<<return kiertekeles()");
        return players;
    }

    public void addPlayer(Player player) {
        System.out.println("<<<return addPlayer()");
    }

    public void addTekton(Tekton tekton) {
        System.out.println("<<<return addTekton()");
    }

    void setAllTektonSzomszed() {
        System.out.println("<<<return setAllTektonSzomszed()");
    }
}
