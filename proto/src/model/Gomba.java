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
        id = Field.genID();
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
            //Ha a vizsgált t tekton a gomba Tektonja akkor igaz
            return true;
        }

        for (List<GombaFonal> l : fonalLista) {
            //Végig az összes fonallistán
            if(l.size() == 0){
                return true;
            }

            if(l.size() == 1) {
                if(l.get(0).getCelTekton().getId() == t.getId()) {
                    return true;
                }
            }

            for (int i = 0; i < l.size() - 1; i++) {
                //Ha folytonos -> azaz i. fonal céltektonja az l listában megegyezik az i+1. fonal startTektonjával
                if(l.get(i).getCelTekton().getId() == l.get(i+1).getStartTekton().getId()) {
                    //Ha az i. fonal céltektonja megewgyezik a keresett tektonnal, akkor a T tekton folytonosan van kötve a gazda gombatesthez
                    //Ha ez az esemény egyszer sem következik be akkor, a T nincs kötve ezzel a Gombával
                    if(l.get(i).getCelTekton().getId() == t.getId() || l.get(i+1).getCelTekton().getId() == t.getId() ) {
                        return true;
                    }
                } else {
                    System.out.println("Nem folytonos a hosszabítandó szakasz a GombaTesttel!");
                    return false;  //HA NEM FOLYTONOS EGY LISTA NEM IS VIZSGÁLJUK TOVÁBB
                }
            }
        }
        System.out.println("A keresett tekton nics a listában");
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
        //Szórás 3-ba kerül - szorando mennyiségnyi spóra
        if(3 > this.gombaTest.getSporakeszlet()){
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
        this.gombaTest.decreaseSporakeszlet(3);
        return true;
    }

    public void addToSporaKeszlet(int val){
        this.gombaTest.addToSporaKeszlet(val);
    }

    public boolean addFonal(GombaFonal ujGF) {
        //Nem szomszédos tektonok kötése
        if(!ujGF.getCelTekton().getSzomszedok().contains(ujGF.getStartTekton()) || !ujGF.getStartTekton().getSzomszedok().contains(ujGF.getCelTekton())) {
            System.out.println("Nem szomszédos Tektonok nem összeköthetőek!");
            return false;    //Nem szomszédosak a kijelölt tektonok
        }

        //Hurokél ellenőrzés
        if(ujGF.getStartTekton().getId() == ujGF.getCelTekton().getId()) {
            System.out.println("Hurokél nem lerakható!");
            return false;
        }

        //Kör ellenőrzés
        for(List<GombaFonal> l : fonalLista) {
            List<Tekton> lista = new ArrayList<>();
            for(GombaFonal gf_i : l) {
                lista.add(gf_i.getStartTekton());
            }

            for(Tekton tekton : lista) {
                if(ujGF.getCelTekton().getId() == tekton.getId()) {
                    System.out.println("Egymás utáni fonalak nem alkothatnak kört!");
                    return false; //Kör lenne
                }
            }
        }

        //Ellenőrzések vége --------------
        //Start Point case
        if(fonalLista.isEmpty()) {
            ArrayList<GombaFonal> lista = new ArrayList<>();
            lista.add(ujGF);
            fonalLista.add(lista);
            return true;
        }

        //Végekre beszúrás
        for(List<GombaFonal> l : fonalLista) {
            if(!l.isEmpty() && fonalFolytonossagVizsgalat(l)) {
                if(l.get(l.size()-1).getCelTekton().getId() == ujGF.getID()){
                    l.add(ujGF);
                    return true;
                }
            }
        }

        //-----
        //A konkrét értékek helyes beállítását az isSetted változó ellenőrzi

        for(List<GombaFonal> l : fonalLista) {
            for (GombaFonal gf_i : l) {
                if(gf_i.getCelTekton().getId() == ujGF.getStartTekton().getId()) {
                    int elozoIdx = l.indexOf(gf_i);
                    List<GombaFonal> ujLista = new ArrayList<>();
                    for(int i =0; i <= elozoIdx; i++){
                        ujLista.add(l.get(i));
                    }

                    ujLista.add(ujGF);

                    if(!fonalFolytonossagVizsgalat(ujLista)){
                        return false;
                    } else {
                        fonalLista.add(ujLista);
                        return true;
                    }
                }
            }
        }
        return false;
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
