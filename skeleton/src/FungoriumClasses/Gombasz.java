package FungoriumClasses;

import java.util.List;

public class Gombasz {
    private List<Gomba> gombaLista;

    public Gombasz() {
        System.out.println("<<<return Gombasz()");
    }

    public void addGomba(Gomba g) {
        System.out.println("<<<return addGomba()");
    }

    public List<Gomba> getGombaLista() {
        System.out.println("<<<return getGombaLista()");
        return gombaLista;
    }

    public int calcAllGombatestScore() {
        System.out.println("<<<return calcAllGombatestScore()");
        return 0;
    }

    public void sporaTermelesAll() {
        System.out.println("<<<return sporaTermelesAll()");
    }

    public boolean fonalLerakasEllenorzes() {
        System.out.println("<<<return fonalLerakasEllenorzes()");
        return false;
    }

    public void szoras(Gomba g, Tekton celTekton) {
        System.out.println("<<<return szoras()");
    }

    public void fonalVasarlas(Gomba g) {
        System.out.println("<<<return fonalVasarlas()");
    }

    public void gombatestNovesztes(Tekton t) {
        System.out.println("<<<return gombatestNovesztes()");
    }

    public void gombafonalNovesztes(Gomba g, Tekton startTekton, Tekton celTekton) {
        System.out.println("<<<return gombafonalNovesztes()");
    }
}
