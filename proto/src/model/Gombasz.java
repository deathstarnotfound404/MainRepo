package model;
import java.util.*;

public class Gombasz extends Player {
    private int score = 0;
    private List<Gomba> gombaLista;

    public Gombasz() {
        gombaLista = new ArrayList<>();
    }

    public void addGomba(Gomba g) {
        this.gombaLista.add(g);
        score++;    //Gomba - GombaTest 1:1
    }
    public int calcAllGombatestScore() {
        return score;
    }
    public void deleteFonalak(List<GombaFonal> list) {}
    public boolean fonalLerakasEllenorzes(Tekton t1, Tekton t2) { return false; }
    public void fonalVasarlas(Gomba g) {}
    public List<Gomba> getGombaLista() { return null; }
    public void gombafonalNovesztes(Gomba g, Tekton start, Tekton cel) {}
    public void gombatestNovesztes(Tekton t) {}
    public List<GombaFonal> protectedSzures(List<GombaFonal> list) { return null; }
    public void sporaTermelesAll() {}
    public void szoras(Gomba g, Tekton celTekton) {}
}