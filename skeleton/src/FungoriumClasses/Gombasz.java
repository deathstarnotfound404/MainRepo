package FungoriumClasses;
import CallTracer.*;

import java.util.ArrayList;
import java.util.List;

public class Gombasz {
    private List<Gomba> gombaLista;

    public Gombasz() {
        gombaLista = new ArrayList<Gomba>();
    }

    public void addGomba(Gomba g) {
        gombaLista.add(g);
    }

    public List<Gomba> getGombaLista() {
        return gombaLista;
    }

    public int calcAllGombatestScore() {
        //TODO szekvenciákkal egyeztetni
        return 0;
    }

    public void sporaTermelesAll() {

    }

    public boolean fonalLerakasEllenorzes() {
        return false;
    }

    public void szoras(Gomba g, Tekton celTekton) {

    }

    public boolean fonalVasarlas(Gomba g) {
        CallTracer.enter("getGombaTest", "Gomba", "");
        GombaTest gt = g.getGombaTest();
        CallTracer.exit("getGombaTest", "gt");

        CallTracer.enter("decreaseSporaKeszlet", "GombaTest", "");
        boolean val = gt.decreaseSporaKeszlet();
        if (val) {
            CallTracer.exit("decreaseSporaKeszlet", "true");

            CallTracer.enter("increaseFonalKeszlet", "Gomba", "3");
            g.increaseFonalKeszlet(3);
            CallTracer.exit("increaseFonalKeszlet", "");
        } else {
            CallTracer.exit("decreaseSporaKeszlet", "false");
        }
        return val;
    }

    public void gombatestNovesztes(Tekton t) {
    }

    public void gombafonalNovesztes(Gomba g, Tekton startTekton, Tekton celTekton) {

    }
}
