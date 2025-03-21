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
        for(Gomba g : gombaLista){
            CallTracer.enter("sporaTermeles", "Gomba", "");
            g.sporaTermeles();
            CallTracer.exit("sporaTermeles", "");
        }
    }

    public boolean fonalLerakasEllenorzes() {
        //return false, ha az üzlezi logika szerint a fonal lerakasa nem elvegezhető -> t1 ből t2 be.
        return true;
    }

    public boolean szoras(Gomba g, Tekton celTekton) {
        CallTracer.enter("getGombaTest", "Gomba", "");
        GombaTest gt = g.getGombaTest();
        CallTracer.exit("getGombaTest", "gt");

        CallTracer.enter("getTekton", "Gomba", "");
        Tekton t1 = g.getTekton();
        CallTracer.exit("getTekton", "t1");

        CallTracer.enter("szor", "Gomba", "celTekton, gt");
        if (g.szor(celTekton, gt)) {
            CallTracer.exit("szor", "true");
            return true;
        } else {
            CallTracer.exit("szor", "false");
            return false;
        }
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

    public boolean gombafonalIranyitas(Tekton t1, Tekton t2, boolean recursively) {
        CallTracer.enter("fonalLerakasEllenorzes", "Gombasz", "");
        if (this.fonalLerakasEllenorzes()){
            CallTracer.exit("fonalLerakasEllenorzes", "true");
        } else {
            CallTracer.exit("fonalLerakasEllenorzes", "HIBA");
        }

        Gomba g = t1.getGomba();
        //CallTracer.enter("getGombaTest", "Gomba", "");
        //GombaTest gt = g.getGombaTest();
        //CallTracer.exit("getGombaTets", "gt");

        CallTracer.enter("fonalFolytonossagVizsgalat", "Gomba", "t1");
        if(g.fonalFolytonossagVizsgalat(t1)){
            CallTracer.exit("fonalFolytonossagVizsgalat", "true");
        } else {
            CallTracer.exit("fonalFolytonossagVizsgalat", "HIBA");
        }

        CallTracer.enter("Gombafonal", "Gombafonal", "t1, t2");
        Gombafonal gf = new Gombafonal(t1, t2);
        CallTracer.exit("Gombafonal()", "");

        CallTracer.enter("addKapcsolodoFonalak", "Tekton:t1", "gf");
        t1.addKapcsolodoFonalak(gf);
        CallTracer.exit("addKapcsolodoFonalak", "");

        CallTracer.enter("addKapcsolodoFonalak", "Tekton:t2", "gf");
        t2.addKapcsolodoFonalak(gf);
        CallTracer.exit("addKapcsolodoFonalak", "");

        CallTracer.enter("increaseFokszam", "Tekton:t1", "");
        t1.increaseFokszam();
        CallTracer.exit("increaseFokszam", "");

        CallTracer.enter("increaseFokszam", "Tekton:t2", "");
        t2.increaseFokszam();
        CallTracer.exit("increaseFokszam", "");

        CallTracer.enter("addFonal", "Gomba", "gf");
        g.addFonal(gf);
        CallTracer.exit("addFonal", "");

        CallTracer.enter("decreaseFonalKeszlet", "Gomba", "");
        g.decreaseFonalKeszlet();
        CallTracer.exit("decreaseFonalKeszlet", "");

        if (!recursively) {
            return true;
        } else { //Mivel van spóra a céltektonon, ezért 1x folytatódik a művelet az extra fonal lerakásával
            gombafonalIranyitas(t2, t2.getSzomszedosTektonok().getFirst(), false);
        }
        return false;
    }
}
