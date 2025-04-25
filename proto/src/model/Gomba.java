package model;
import java.util.*;

public class Gomba implements IDestroyable {
    private List<List<GombaFonal>> fonalLista;
    private int fonalKeszlet = 0;
    private Tekton tekton;
    private GombaTest gombaTest;
    private int id;
    private Gombasz gombasz;

    public Gomba(Tekton t, Gombasz gsz, int kezdoSporaszam) {
        Field.genID();
        gombasz = gsz;
        tekton = t;
        t.setGomba(this);   //Itt létrejön biztosan, mert akkor híjuk csak meg ha leellenőriztük h üres a tekton
        fonalLista = new ArrayList<>();
        gombaTest = new GombaTest(this, kezdoSporaszam);
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
        gombaTest.szintlepes();
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
        for (List<GombaFonal> l : fonalLista) {
            for (int i = 0; i < l.size(); i++) {
                if(i < l.size() - 2) {
                    if(l.get(i).getCelTekton().getId() == l.get(i+1).getStartTekton().getId()) {

                        if(l.get(i).getID() == gf.getID()) {
                            return true;
                        }

                    } else {
                        return false; //HA NEM FOLYTONOS EGY LISTA NEM IS VIZSGÁLJUK TOVÁBB
                    }
                }
            }
        }
        return false;
    }

    public boolean fonalFolytonossagVizsgalat(Tekton t){
        //Megvizsgálja, hogy az átadott tekton folytonosan van-e kötve a gombatesttel
        if(t.getId() == this.tekton.getId()){
            return true;
        }

        for (List<GombaFonal> l : fonalLista) {
            //Végig az összes fonallistán
            for (int i = 0; i < l.size(); i++) {
                if(i < l.size() - 2) {
                    //Ha folytonos -> azaz i. fonal céltektonja az l listában megegyezik az i+1. fonal startTektonjával
                    if(l.get(i).getCelTekton().getId() == l.get(i+1).getStartTekton().getId()) {
                        //Ha az i. fonal céltektonja megewgyezik a keresett tektonnal, akkor a T tekton folytonosan van kötve a gazda gombatesthez
                        //Ha ez az esemény egyszer sem következik be akkor, a T nincs kötve ezzel a Gombával
                        if(l.get(i).getCelTekton().getId() == t.getId()) {
                            return true;
                        }
                    } else {
                        return false;  //HA NEM FOLYTONOS EGY LISTA NEM IS VIZSGÁLJUK TOVÁBB
                    }
                }
            }
        }
        return false;
    }

    //TODO protected ne legyen törölve
    public void fonalFelszivodas(Tekton t) {
        List<GombaFonal> felszivandoFonalak = t.getKapcsolodoFonalak();

        //1. Másik kapcsolódó Tektonról töröljük a fonalakat
        for (GombaFonal gf : felszivandoFonalak) {
            Tekton t1 = gf.getStartTekton();
            Tekton t2 = gf.getCelTekton();

            if(t1.getId() == t.getId()){
                t2.removeKapcsolodoFonal(gf);   //Ez auto nézi hogy defend fonal-e amit töröl
            } else {
                t1.removeKapcsolodoFonal(gf);
            }
        }

        //2. t-ből töröljük fonalakat
        t.clearKapcsolodoFonalak();     //Ez auto nézi hogy defend fonal-e amit töröl

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
                if(Gombasz.protectedSzures(l).contains(gf)) {
                    break;
                } else {
                    l.remove(gf);   // Protected fonalak nincsenek törölve
                }
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

        //Célpont ellenőrzése - Nem szomszédos tektonok ell.
        if(!this.tekton.getSzomszedosTektonok().contains(celTekton)){
            if(this.gombaTest.getSzint() == 3){ //Ha 3. szintű akkor szomszéd szomszédjára is szórhat
                //Szomszéd szomszédjainak ellenőrzése
                if(!szomszedSzomszedjaEllenorzes(celTekton)){
                    System.out.println("\tHiba: 3. SZintű GombaTest maximum csak a szomszéd szomszédjaira szórhat!");
                    return false;
                }
            } else {
                //Ha kevesebb mint 3. szintű, akkor csak szomszédos Tektonra szórhat
                System.out.println("\tHiba: 1. és 2. szintű GombaTest csak szomszédos Tektonra szórhat!.");
                return false;
            }
        }

        int szint = gt.getSzint();
        int szorandoMennyiseg = gt.sporaSzorzo(szint);

        //Ha nincs elég spóra a szóráshoz - Nincs elég spórakészlet
        //SZórás 5 be kerül - szorando mennyiségnyi spóra (kerül a tektonra vagy a Gombatesthez) és 5 -szorando mennyiség a szorás ára
        if(5 >= this.gombaTest.getSporakeszlet()){
            System.out.println("\tHiba: Nincs elég Spóra a szóráshoz.");
            return false;
        }

        //Ha már van a céltektonon Gomba és GombaTest
        if(celTekton.getGomba() != null){
            //Ekkor nem jön létre Spora objektum csak Gombatesthez adjuk a spórákat
            celTekton.getGomba().addToSporaKeszlet(szorandoMennyiseg);
            System.out.println("\tSzórás: Spórák GombaTesthez adódtak.");
        } else {
            //Ha még nincs a céltektonon Gomba és GombaTest, akkor a tektonon jönnek létre a Sporak
            for(int i = 0; i< (szorandoMennyiseg); ++i){
                BaseSpora s = BaseSpora.generateRandomSpora();  //Mindegyik 1/6-od valószínűséggel
                celTekton.addSpora(s);
                System.out.println("\tSzórás: Spórák Tektonon létrejöttek.");
            }
        }
        this.gombaTest.addSzorasCount(1);
        //TODO Szórás után ellenőrizni, hogy elpusztul-e
        this.gombaTest.decreaseSporakeszlet(5);
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

    //TODO protectedet nem törölhet
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

    //TODO protected ne legyen törölve
    public void elpusztul() {
        for(List<GombaFonal> l : fonalLista) {
            l.clear();
        }
        fonalLista.clear();
        tekton.setGomba(null);
        gombasz.getGombaLista().remove(this);
        System.out.println("Gomba Id" + this.getId() + "Elpusztult!");
    }

    public Gombasz getGombasz(){
        return gombasz;
    }

    public int getId(){
        return id;
    }
}
