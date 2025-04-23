package model;
import FungoriumClasses.Gomba;

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

    public void deleteFonalak(List<GombaFonal> list) {
        for(Gombafonal gf : list) {
            deleteFonal(gf);
        }
    }

    public boolean fonalLerakasEllenorzes(Tekton t1, Tekton t2) {
        //Mar van a ket tekton kozott?
        for(GombaFonal fonal : t1.getKapcsolodoFonalak()) {
            if((fonal.getStart() == t1 && fonal.getCel() == t2) || (fonal.getStart() == t2 && fonal.getCel() == t1)) {
                return false;
            }
        }

        //maxEgyFonal szabaly serules
        if(t1.isMaxEgyFonal() && t1.getKapcsolodofonalak().size() >= 1) {
            return false;
        }

        if(t2.isMaxEgyFonal() && t2.getKapcsolodofonalak().size() >= 1) {
            return false;
        }

        return true;
    }

    public void fonalVasarlas(Gomba g) {
        GombaTest gt = g.getGombatest();
        int sporaCount = gt.getSporakeszlet();

        if (sporaCount >= 1) {
            // Minden spóráért 3 fonalat kap
            int ujFonal = g.getFonalKeszlet() + 3;
            g.setFonalKeszlet(ujFonal);

            // Csökkentjük a spóra készletet eggyel
            gt.setSporakeszlet(sporaCount - 1);
        } else {
            // Ha nincs elég spóra, nem vásárolható fonal
            throw new Exception("Nincs elég spóra fonal vásárlásához.");
        }
    }

    public List<Gomba> getGombaLista() {
        return gombaLista;
    }

    public void gombafonalNovesztes(Gomba g, Tekton start, Tekton cel) {
        List<List<GombaFonal>> fonalListaLista = g.getFonalak();
        boolean hozzaadva = false;

        for(List<GombaFonal> fonalLista : fonalListaLista) {
            if(!fonalLista.isEmpty()) {
                GombaFonal utolsoFonal = fonalLista.get(fonalLista.size() - 1);
                if(utolsoFonal.getCelTekton().equals(startTekton)) {
                    GombaFonal ujFonal = new GombaFonal(g, startTekton, celTekton);
                    fonalLista.add(ujFonal);
                    startTekton.addKapcsolodoFonalak(ujFonal);
                    celTekton.addKapcsolodoFonalak(ujFonal);
                    hozzadva = true;
                    break;
                }
            }
        }

        if(!hozzaadva) {
            //Gombatest poziciojat nezzuk
            Tekton gombaHelyzet = g.getGombaTest().getHelyzet();
            if(gombaHelyzet.equals(startTekton)) {
                Lista<GombaFonal> ujLista = new ArrayList<>();
                GombaFonal ujFonal = new GombaFonal(g, startTekton, celTekton);
                ujLista.add(ujFonal);
                fonalListaLista.add(ujLista);
                startTekton.addKapcsolodoFonalak(ujFonal);
                celTekton.addKapcsolodoFonalak(ujFonal);
            } else {
                throw new IllegalArgumentException("Nem lehet fonalat növeszteni: nincs megfelelő kezdőpont.");
            }
        }
    }

    public void gombatestNovesztes(Tekton t, boolean ingyen) {
        //TODO ingyen mód nem kerül 3 spórába a növesztés, hanem minden Tektonon lévő Spórát megkapja a GT
    }

    public List<GombaFonal> protectedSzures(List<GombaFonal> list) {
        List<GombaFonal> filtered = new ArrayList<>();

        for (GombaFonal fonal : listOfDisconnectedFonalak) {
            Tekton start = fonal.getStartTekton();
            Tekton cel = fonal.getCelTekton();

            if (start.isDefendFonalak() || cel.isDefendFonalak()) {
                filtered.add(fonal);
            }
        }

        return filtered;
    }

    public void sporaTermelesAll() {
        for (Gomba g : gombaLista) {
            g.sporaTermeles();
        }
    }
    public void szoras(Gomba g, Tekton celTekton) {
        int szorhatoSporaMennyiseg = g.getGombaTest().getSporakeszlet();

        //TODO
    }
}
