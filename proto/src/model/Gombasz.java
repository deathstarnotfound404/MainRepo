package model;
import java.util.*;

public class Gombasz extends Player {
    private List<Gomba> gombaLista;

    public Gombasz(String name) {
        super(name);
        gombaLista = new ArrayList<>();
    }

    public void addGomba(Gomba g) {
        this.gombaLista.add(g);
        score++;    //Gomba - GombaTest 1:1
    }

    public int calcAllGombatestScore() {
        return score;
    }

    public void deleteFonalak(List<GombaFonal> disconnectedFonalak) {   //Itt a disconnected fonalak kerülnek átadásra
        //TODO ellenőrizni, hogy használatkor csak disconnectedet törlünk vele(elméletileg a notConnectedTo alapgomba ezt megoldja)
        for(GombaFonal gf : disconnectedFonalak) {
            if(gf.IsDestroyable() && !gf.connectedToAlapGomba()) {
                gf.getAlapGomba().deleteFonal(gf); //Ez az összes előfordulásánál kitörli a gombában
            }
        }
    }

    public boolean fonalLerakasEllenorzes(Tekton t1, Tekton t2) {
        //Mar van a ket tekton kozott fonal?
        if(Tekton.ketTektonFonallalOsszekotott(t1, t2)){
            System.out.println("Hiba: A két tekton már össze van kötve!");
            return false;
        }

        //maxEgyFonal szabaly serules
        if(t1.isMaxEgyFonal() && t1.getKapcsolodoFonalak().size() >= 1) {
            System.out.println("Hiba: Az egyik tektonon maximum egy fonal lehet!");
            return false;
        }

        if(t2.isMaxEgyFonal() && t2.getKapcsolodoFonalak().size() >= 1) {
            System.out.println("Hiba: Az egyik tektonon maximum egy fonal lehet!");
            return false;
        }

        return true;
    }

    public boolean fonalVasarlas(Gomba g) {
        if(g.getGombatest().decreaseSporakeszlet(1)) {
            g.increaseFonalkeszlet(3);
            return true;
        } else {
           return false;
        }
    }

    //TODO ellenőrizni, benne van a fonal árú és az ingyenes bónusz növezstés is
    public boolean gombafonalIranyitas(Gomba g, Tekton stratTekton, Tekton celTekton, boolean ingyen){
        //A G-nek legyen elég fonala
        if(fonalLerakasEllenorzes(stratTekton, celTekton)) {    //Ha még nincs összekötve
            if(g.fonalFolytonossagVizsgalat(stratTekton)) { //Akkor is false, ha a starttekton nincs összekötve a g Gombával, éls akkor is ha szakadása van közben

                //Ha nem ingyenes a lerakás csökkentjük a fonalkészletet
                if(!ingyen) {
                    if(!g.decreaseFonalkeszlet()) {
                        System.out.println("Hiba: Nincs elég fonal készlet a fonalnövesztéshez!");
                        return false;
                    }
                }

                GombaFonal ujFonal = new GombaFonal(g, stratTekton, celTekton);
                if(!g.addFonal(ujFonal) || !celTekton.addKapcsolodoFonalak(ujFonal) || !stratTekton.addKapcsolodoFonalak(ujFonal)) {
                    System.out.println("Hiba: A fonal nem adható a Gombához vagy a Tektonokhoz!");
                    return false;
                }

                if(celTekton.getSporaLista().size() > 0 && !ingyen) {  //Ettől lesz egy ingyenes lerakás
                    //Ha van a céltektonon elszórt spóra -> ingyen lerakható fonal
                    System.out.println("\t[Növesztés gyorsítása] Még egy fonal ingyen lerakható ha van szabad Tekton");
                    List<Tekton> celSzomszedok = new ArrayList<>(celTekton.getSzomszedok());
                    celSzomszedok.remove(stratTekton);

                    boolean valasztott = false;
                    while(!valasztott) {

                        if(celSzomszedok.size() == 0) {
                            //Ha nincs megfelelő Tekton, ami lehetne új cél
                            System.out.println("\nNincs megfelelő Tekton az ingyenes lerakáshoz!");
                            return false;
                        }

                        Random rand = new Random();
                        Tekton randomUJCel = celSzomszedok.get(rand.nextInt(celSzomszedok.size()));
                        if(gombafonalIranyitas(g, celTekton, randomUJCel, true)) {
                            valasztott = true;
                            System.out.println("Ingyenes bónusz fonal lerakva!");
                        } else {
                            celSzomszedok.remove(randomUJCel);
                        }
                    }
                    //Ha sikeres a választott és a bónusz lerakás is
                    return true;
                } else {
                    if(!ingyen) {
                        System.out.println("\t[Nincs bónusz fonal lerakás]");
                    }
                    //Ha nincs bónusz lerakás, de sikeres az első lerakás
                    return true;
                }
            } else {
                return false;   //Ekkor sem lerakható (vagy ne folytonos lenne, vagy nincs elég fonal)
            }
        } else {
            //Ha a fonal nem lerakható
            return false;
        }
    }

    public List<Gomba> getGombaLista() {
        return gombaLista;
    }

    public boolean gombatestNovesztes(Tekton t, boolean ingyen) {
        //TODO ingyen mód nem kerül 3 spórába a növesztés, hanem minden Tektonon lévő Spórát megkapja a GT
        //Igazából a Gomba hozzáadása a Gombalistához, ami implicit létrehoz GombaTestet
        //Ellenőrizzük hogy üres-e
        if(t.getGomba() != null || t.isGtGatlo() || t.getVanGombaTest()) {
            System.out.println("Hiba: GombaTest nem lerakható a Tektonon!");
            return false;
        }

        int sporaszam = t.getSporaLista().size();

        if(!ingyen) {
            //Ha nincs elég spóra objektum a Tektonon
            if(sporaszam < 5) {
                System.out.println("Hiba: Nincs elég spóra a céltektonon!");
                return false;
            } else {
                t.removeSporak(sporaszam);
                Gomba ujGomba = new Gomba(t, this, sporaszam - 3);
                addGomba(ujGomba);
                addScore(1);
                System.out.println("[growGombaTest]: Sikeres gombatest növesztés'");
                return true;
            }
        } else {
            t.removeSporak(sporaszam);
            Gomba ujGomba = new Gomba(t, this, sporaszam);
            addGomba(ujGomba);
            addScore(1);
            System.out.println("[growGombaTest]: Sikeres ingyenes gombatest növesztés'");
            return true;
        }
    }

    //TODO hol használjuk, minden fonal törlés esetén, de hogy ezt magukban a fonal törlésekben, vagy a hívásoknál "controllerből"?
    public static List<GombaFonal> protectedSzures(List<GombaFonal> listOfDisconnectedFonalak) {
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

    public boolean szoras(Gomba g, Tekton celTekton) {
        boolean ret = g.szor(celTekton, g.getGombatest());
        g.gombatestSzintlepes();
        return ret;
    }

    @Override
    public int getScoreFromPlayer(){
        return calcAllGombatestScore();
    }

    public Gomba getGombaById(int id) {
        for(Gomba g : gombaLista) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public boolean rovarEves(Rovar r){
        if(r.getEvesHatekonysag() != 0) {
            //Ha a Rovar nem bénült
            System.out.println("Hiba: Csak bénült Rovarok ehetőek meg!");

            return false;
        }
        //A gombász akármelyik fonalának a végén megtalálható a kijelölt rovar akkor elfogyasztható
        boolean rFound = false;
        Tekton helyzet = r.getHelyzet();
        for(Gomba g : this.getGombaLista()) {
            for(List<GombaFonal> l : g.getFonalLista()){
                for(GombaFonal gf : l){
                    if(gf.getStartTekton().getRovar() != null){
                        if(gf.getStartTekton().getRovar().getId() == r.getId()){
                            r.getRovarasz().removeRovar(r);
                            System.out.println("[eatRovar]: Sikeres!");
                            rFound = true;
                        }
                    }

                    if(gf.getCelTekton().getRovar() != null){
                        if(gf.getCelTekton().getRovar().getId() == r.getId()){
                            r.getRovarasz().removeRovar(r);
                            rFound = true;
                        }
                    }
                }
            }
        }

        if(rFound){
            //Gombatest növesztés
            this.gombatestNovesztes(helyzet, true);
            return true;
        }

        System.out.println("Hiba: A kijelölt ovar nem érhető el fonallal!");
        return false;
    }
}
