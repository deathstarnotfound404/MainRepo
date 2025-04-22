package model;
import java.util.*;

public class Gomba implements IDestroyable {
    private List<List<GombaFonal>> fonalLista;
    private int fonalKeszlet = 0;
    private Tekton tekton;
    private GombaTest gombaTest;
    protected int id;

    public Gomba(Tekton t) {
        Field.genID();
        tekton = t;
        fonalLista = new ArrayList<>();
    }

    public List<List<GombaFonal>> getFonalLista() { return fonalLista; }

    public void setFonalKeszlet(int val) {
        fonalKeszlet = val;
    }

    public int getFonalKeszlet() {
        return fonalKeszlet;
    }

    public GombaTest getGombatest() {
        return gombaTest;
    }

    public void sporaTermeles() {
        this.gombaTest.addToSporaKeszletTermelessel();
    }

    public void gombatestSzintlepes() {
        //TODO - Ellenőtizni, hogy ezt használjuk-e valahol
        int mennyiseg = gombaTest.getSzorasCount();
        gombaTest.szintlepes(mennyiseg);
    }

    public List<GombaFonal> fonalFolytonossagVizsgalat() {
        List<GombaFonal> nemfolytonosList = new ArrayList<>();

        //Folytonosság ellenőrzése a cél és követő start tektonok egyezesenek vizsgalataval
        //Sublist-ként a szakadás utáni összes fonlat felvesszük a nemfolytonosra
        for (List<GombaFonal> l : fonalLista) {
            for (int i = 0; i < l.size(); i++) {
                if(i < l.size() - 2) {
                    if(l.get(i).getCelTekton().getId()!= l.get(i+1).getStartTekton().getId()) {
                        nemfolytonosList.addAll(l.subList(i + 1, l.size()));
                        break;
                    }
                }
            }
        }

        return nemfolytonosList;
    }

    public boolean fonalFolytonossagVizsgalat(List<GombaFonal> l_i) {
        if(l_i.size() == 0){
            return true;
        }
        for (int i = 0; i < l_i.size(); i++) {
            if(i < l_i.size() - 2) {
                if(l_i.get(i).getCelTekton().getId()!= l_i.get(i+1).getStartTekton().getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean fonalFolytonossagVizsgalat(GombaFonal gf){
        //TODO ELLENŐRIZNI EGYÜTT - BIZTOS HOGY LEHET EGYSZERŰSÍTENI
        //Megvizsgálja, hogy az átadott fonal folytonos-e a gazda gombatestjével, és alapgombájával

        //Megvizsgálja listánként hogy az egymást követő elemek folytonosak-e
        //Ha végig folytonos és megtalálja a keresett fonalat, akkor true és folytonos
        //HA sose talál folytonos
        boolean folytonos = true;
        for (List<GombaFonal> l : fonalLista) {
            folytonos = true;
            for (int i = 0; i < l.size(); i++) {
                if(i < l.size() - 2) {
                    if(l.get(i).getCelTekton().getId() == l.get(i+1).getStartTekton().getId()) {
                        if(folytonos) {
                            folytonos = true;

                            if(l.get(i).getID() == gf.getID()) {
                                return true;
                            }
                        }
                    } else {
                        folytonos = false;
                        break;  //HA NEM FOLYTONOS EGY LISTA NEM IS VIZSGÁLJUK TOVÁBB
                    }
                }
            }
        }
        return false;
    }

    public void fonalFelszivodas(Tekton t) {
        List<GombaFonal> felszivandoFonalak = t.getKapcsolodoFonalak();

        //1. Másik kapcsolódó Tektonról töröljük a fonalakat
        for (GombaFonal gf : felszivandoFonalak) {
            Tekton t1 = gf.getStartTekton();
            Tekton t2 = gf.getCelTekton();

            if(t1.getId() == t.getId()){
                t2.removeKapcsolodoFonal(gf);
            } else {
                t1.removeKapcsolodoFonal(gf);
            }


        }

        //2. t-ből töröljük fonalakat
        t.clearKapcsolodoFonalak();

        //3. Gombából töröljük az adott fonalakat
        for(GombaFonal gf : felszivandoFonalak){
            this.deleteFonal(gf);
        }
    }

    public void setGombaTest(GombaTest gt) {
        gombaTest = gt;
    }

    public void deleteFonal(GombaFonal gf) {
        for (List<GombaFonal> l : fonalLista) {
            if(l.contains(gf)) {
                l.remove(gf);
            }
        }
    }

    private boolean szomszedSzomszedjaEllenorzes(Tekton celT){
        //Ellenőrizzük hogy az adott (this) Gomba szomszédjainak szomszédja-e a t
        List<Tekton> szomszedSzomszedai = new ArrayList<>();
        for (Tekton tSzomszed : this.tekton.getSzomszedosTektonok()) {  //A gomba szomszédos tektonjai
            szomszedSzomszedai.addAll(tSzomszed.getSzomszedosTektonok());   //A szomszédok szomszédai
        }

        if(szomszedSzomszedai.contains(celT)){
            //Ekkor szomszédok szomszédai között van a célpont
            return true;
        } else {
            return false;
        }
    }

    public boolean szor(Tekton celTekton, GombaTest gt) {
        List<Tekton> szomszedLista = celTekton.getSzomszedosTektonok();

        //Célpont ellenőrzése
        if(!this.tekton.getSzomszedosTektonok().contains(celTekton)){
            if(this.gombaTest.getSzint() == 3){ //Ha 3. szintű akkor szomszéd szomszédjára is szórhat
                //Szomszéd szomszédjainak ellenőrzése
                if(!szomszedSzomszedjaEllenorzes(celTekton)){
                    return false;
                }
            } else {
                //Ha kevesebb mint 3. szintű, akkor csak szomszédos Tektonra szórhat
                return false;
            }
        }

        int szint = gt.getSzint();
        int szorandoMennyiseg = gt.sporaSzorzo(szint);

        //Ha nincs elég spóra a szóráshoz
        if(szorandoMennyiseg > this.gombaTest.getSporakeszlet()){
            return false;
        }

        //Ha már van a céltektonon Gomba és GombaTest
        if(celTekton.getGomba() != null){
            //Ekkor nem jön létre Spora objektum csak Gombatesthez adjuk a spórákat
            celTekton.getGomba().addToSporaKeszlet(szorandoMennyiseg);
        } else {
            //Ha még nincs a céltektonon Gomba és GombaTest, akkor a tektonon jönnek létre a Sporak
            for(int i = 0; i<szorandoMennyiseg; ++i){
                //TODO random spóra generálás kezelése
                Spora s = new Spora();
                celTekton.addSpora(s);
            }
        }
        this.gombaTest.addSzorasCount(1);
        this.gombaTest.decreaseSporakeszlet(szorandoMennyiseg);
        return true;
    }

    public void addToSporaKeszlet(int val){
        this.gombaTest.addToSporaKeszlet(val);
    }

    public void addFonal(GombaFonal gf) {
        //Tektonok kimentése
        Tekton actualStartTekton = gf.getStartTekton();
        Tekton actualEndTekton = gf.getCelTekton();

        //Nem szomszédos tektonok kötése
        if(!actualEndTekton.getSzomszedok().contains(actualStartTekton) || !actualStartTekton.getSzomszedok().contains(actualEndTekton)) {
            return;    //Nem szomszédosak a kijelölt tektonok
        }

        //Start Point case
        if(fonalLista.isEmpty()) {
            ArrayList<GombaFonal> lista = new ArrayList<>();
            lista.add(gf);
            fonalLista.add(lista);
            return;
        }

        //If not Start point in list

        //Megkeressük és kimentjük a beszúrandó gombafonal helyét
        List<GombaFonal> actualLista = null;
        GombaFonal gfActual = null;
        int elozoIdx = 0;   //fonallistán belüli idx
        boolean isSetted = false;
        //A konkrét értékek helyes beállítását az isSetted változó ellenőrzi

        for(List<GombaFonal> l : fonalLista) {
            for (GombaFonal gf_i : l) {
                if(gf_i.getCelTekton().getId() == actualStartTekton.getId()) {
                    actualLista = l;
                    elozoIdx = l.indexOf(gf_i);
                    gfActual = gf_i;
                    isSetted = true;
                }
            }
        }

        //Hurokél ellenőrzés
        if(actualStartTekton.getId() == actualEndTekton.getId()) {
            return;
        }

        //Kör ellenőrzés
        for(List<GombaFonal> l : fonalLista) {
            List<Tekton> lista = new ArrayList<>();
            for(GombaFonal gf_i : l) {
                lista.add(gf_i.getStartTekton());
            }

            for(Tekton tekton : lista) {
                if(actualEndTekton.getId() == tekton.getId()) {
                    return; //Kör lenne
                }
            }
        }

        //Ellenőrzések - Ha már létezik ilyen fonal, vagy nem találtunk hosszabbítandó szakaszt
        if(gfActual.getCelTekton().getId() == gf.getCelTekton().getId() || gfActual.getCelTekton().getId() == gf.getStartTekton().getId()){
            if(!isSetted) {
                List<GombaFonal> l = new ArrayList<>();
                if(!fonalFolytonossagVizsgalat(l)){
                    return;
                }
                l.add(gf);
                fonalLista.add(l);
            }
            return;
        }

        //End point case
        if ((actualLista.size() - 1) == elozoIdx) {
            if(!fonalFolytonossagVizsgalat(actualLista)){
                return;
            }
            actualLista.add(gf);
        } else  //Mid-Point case
        {
            List<GombaFonal> ujLista = new ArrayList<>();
            for(int i =0; i <= elozoIdx; i++){
                ujLista.add(actualLista.get(i));
            }
            if(!fonalFolytonossagVizsgalat(ujLista)){
                return;
            }
            ujLista.add(gf);
            fonalLista.add(ujLista);
        }

    }

    public boolean decreaseFonalkeszlet() {
        if(fonalKeszlet > 0) {
            fonalKeszlet--;
            return true;
        } else {
            return false;
        }
    }

    public void increaseFonalkeszlet(int val) {
        fonalKeszlet += val;
    }

    public Tekton getTekton() {
        return tekton;
    }

    public void setTekton(Tekton t) {
        tekton = t;
    }

    public void nemFolytonosFonalTorles() {
        List<GombaFonal> nemFolytonos = fonalFolytonossagVizsgalat();
        for (GombaFonal gf : nemFolytonos) {
            for (List<GombaFonal> l : fonalLista) {
                if(l.contains(gf)) {
                    l.remove(gf);
                }
            }
        }
    }

    public void elpusztul() {
        //TODO
    }
}