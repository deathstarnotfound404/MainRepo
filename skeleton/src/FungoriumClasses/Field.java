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

    public List<Player> kiertekeles() {
        //TODO
        Rovarasz bestRovarasz = null;
        Gombasz bestGombasz = null;
        for(Player p : players){
            //ha rovarasz
            if(p.getClass() == (Rovarasz.class) && bestRovarasz == null) bestRovarasz = (Rovarasz)p;
            else if(p.getClass() == (Rovarasz.class) && p.getScore() > bestRovarasz.getScore()){
                bestRovarasz = (Rovarasz)p;
            }
            //ha gombasz
            if(p.getClass() == (Gombasz.class) && bestGombasz == null) bestGombasz = (Gombasz)p;
            else if(p.getClass() == (Gombasz.class) && p.getScore() > bestGombasz.getScore()){
                bestGombasz = (Gombasz)p;
            }
        }
        List<Player> bestPlayers = new ArrayList<Player>();
        bestPlayers.add(bestGombasz);
        bestPlayers.add(bestRovarasz);
        return bestPlayers;
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
