package model;
import java.util.*;

public class Rovarasz extends Player {
    private List<Rovar> rovarLista;

    public Rovarasz(String name) {
        super(name);
        rovarLista = new ArrayList<>();
    }

    public boolean addRovar(Rovar r, Tekton t) {
        if(t.setRovar(r)) {
            this.rovarLista.add(r);
            return true;
        } else {
            return false;
        }
    }

    public int calcAllTapanyagScore() {
        int sumTapanyag = 0;
        for(Rovar r : rovarLista) {
            sumTapanyag += r.getTapanyag();
        }
        return sumTapanyag;
    }

    public boolean fonalVagas(Rovar r, GombaFonal gf) {
        if (r.getTudVagni()) {
            return r.vag(gf);
        } else {
            return false;
        }
    }

    public List<Rovar> getRovarLista() {
        return rovarLista;
    }

    public void removeRovar(Rovar r) {
        if(r.getHelyzet().getRovar() != null) {
            r.getHelyzet().setRovar(null);
        }
        rovarLista.remove(r);
    }

    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        return r.lep(celTekton);
    }

    @Override
    public int getScoreFromPlayer(){
        return calcAllTapanyagScore();
    }

    public Rovar getRovarById(int id) {
        for(Rovar r : rovarLista) {
            if(r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}
