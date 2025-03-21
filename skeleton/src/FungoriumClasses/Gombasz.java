package FungoriumClasses;
import CallTracer.*;

import java.util.ArrayList;
import java.util.List;

public class Gombasz extends Player {
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

    public boolean gombatestNovesztes(Tekton t) {
        CallTracer.enter("getVanGombaTest", "Tekton", "");
        if (!t.getVanGombaTest()) {
            CallTracer.exit("getVanGombaTest", "false");
        } else {
            CallTracer.exit("getGomba", "HIBA");
        }
        CallTracer.enter("getGomba", "Tekton", "");
        Gomba g = t.getGomba();
        CallTracer.exit("getGomba", "g");
        CallTracer.enter("sporaCount", "Tekton", "");
        int sporaSzam = t.sporaCount();
        CallTracer.exit("sporaCount", "sporaSzam");

        CallTracer.enter("removeSporak", "Tekton", "5");
        t.removeSporak();
        CallTracer.exit("removeSporak", "");

        CallTracer.enter("GombaTest", "GombaTest", "g, sporaSzam");
        GombaTest gt = new GombaTest(g, sporaSzam);
        CallTracer.exit("GombaTest", "");

        CallTracer.enter("addToSporaKeszlet", "GombaTest", "sporaSzam - 3");
        gt.addToSporaKeszlet(sporaSzam - 3);    //Szabály szerint 3-at használ fel  //TODO ellenőrizni
        CallTracer.exit("addToSporaKeszlet", "");

        CallTracer.enter("addScore", "Gombasz", "");
        this.addScore();
        CallTracer.exit("addScore", "");

        CallTracer.enter("setVanGombaTest", "Tekton", "true");
        t.setVanGombaTest(true);
        CallTracer.exit("setVanGombaTest", "");

        return true;
    }

    public void gombafonalNovesztes(Gomba g, Tekton startTekton, Tekton celTekton) {

    }
}
