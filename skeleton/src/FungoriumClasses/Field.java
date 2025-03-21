package FungoriumClasses;

import CallTracer.CallTracer;

import java.util.List;
import java.util.ArrayList;

public class Field {
    private List<Tekton> tektonLista;
    private List<Player> players;

    public Field() {
        this.tektonLista = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    List<Player> kiertekeles() {
        return null;    //TODO
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addTekton(Tekton tekton) {
        this.tektonLista.add(tekton);
    }

    public void setAllTektonSzomszed() {
        for(Tekton t : tektonLista){
            for(Tekton tt : tektonLista){
                if(t != tt){
                    CallTracer.enter("addSzomszedosTekton", "Tekton", "t");
                    tt.addSzomszedosTekton(t);
                    CallTracer.exit("addSzomszedosTekton", "");
                }
            }
        }
    }

    public List<Tekton> getTektonLista(){return tektonLista;}
}
