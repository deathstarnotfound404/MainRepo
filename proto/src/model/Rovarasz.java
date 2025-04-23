package model;
import java.util.*;

public class Rovarasz extends Player {
    private List<Rovar> rovarLista;

    public Rovarasz() {
        rovarLista = new ArrayList<Rovar>();
    }

    public void addRovar(Rovar r, Tekton t) {
        if(t.vanRovarATektonon()) {
            throw new Exception("Van rovar a tektonon.");
        }
         t.setRovar(r);
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
            r.vag(gf);
            return true;
        } else {
            return false;
        }
    }

    public List<Rovar> getRovarLista() {
        return rovarLista;
    }
    public void removeRovar(Rovar r) {
        rovarLista.remove(r);
    }
    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        return r.lep(celTekton);
    }
}
