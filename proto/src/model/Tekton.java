package model;
import java.util.*;

public class Tekton implements IDestroyable{
    private int id;
    private int rovarLatogatottsag = 0;
    private int fonalFokszam = 0;
    private List<BaseSpora> sporaLista;
    private List<GombaFonal> kapcsolodoFonalak;
    private List<Tekton> szomszedosTektonok;
    private boolean defendFonalak = false;
    private boolean maxEgyFonal = false;
    private boolean vanGombaTestTektonon = false;
    private boolean gtGatlo = false;
    private TektonHatas tektonHatas;
    private Gomba tektononLevoGomba;
    private Rovar tektononLevoRovar;

    public Tekton(TektonHatas hatas) {
        id = Field.genID();
        sporaLista = new ArrayList<>();
        kapcsolodoFonalak = new ArrayList<>();
        szomszedosTektonok = new ArrayList<>();
        tektonHatas = hatas;
        //TODO azok a hatások érvényesítése melyek alapértelmezetten kellenek
    }

    public List<Tekton> getSzomszedok() {
        return this.szomszedosTektonok;
    }

    public String hatasKifejtes() {
        return tektonHatas.hatas();
    }

    public int sporaCount() {
        return sporaLista.size();
    }

    public void addSpora(BaseSpora spora) {
        sporaLista.add(spora);
    }

    //TODO TÖRÉS ELLENŐRZÉSE
    public boolean tektonTores(){
        if(rovarLatogatottsag >= 10) {
            this.fonalakFelszivasa();

            if(tektononLevoGomba != null) {
                tektononLevoGomba.elpusztul();
            }

            List<Tekton> meglevoSzomszedok = szomszedosTektonok;

            Tekton ujTekton1 = new Tekton(TektonHatas.generateRandomTektonHatas());
            Tekton ujTekton2= new Tekton(TektonHatas.generateRandomTektonHatas());
            Field.addTekton(ujTekton1);
            Field.addTekton(ujTekton2);

            ujTekton1.addSzomszedosTekton(ujTekton1);
            ujTekton2.addSzomszedosTekton(ujTekton2);
            ujTekton1.getSzomszedok().addAll(this.szomszedosTektonok);
            ujTekton2.getSzomszedok().addAll(this.szomszedosTektonok);

            for (Tekton t : meglevoSzomszedok) {
                t.addSzomszedosTekton(ujTekton1);
                t.addSzomszedosTekton(ujTekton2);
            }

            if(this.vanRovarATektonon()) {
                ujTekton1.setRovar(tektononLevoRovar);
                tektononLevoRovar.setHelyzet(ujTekton1);
                this.setRovar(null);
            }

            //Régi tekton törlése
            this.elpusztul();
            System.out.println("[TektonTores] Tekton kettétört.");
            return true;
        } else {
            return false;
        }

    }

    public boolean vanRovarATektonon(){
        return this.tektononLevoRovar != null;
    }

    public void setSzomszedok(List<Tekton> szomszedok) {
        this.szomszedosTektonok = szomszedok;
    }

    public void clearKapcsolodoFonalak(){
        if(!defendFonalak){
            this.kapcsolodoFonalak.clear();
            this.fonalFokszam = 0;
        }
    }

    public void fonalakFelszivasa(){
        if(!defendFonalak) {
            for(GombaFonal gf : kapcsolodoFonalak) {
                gf.getAlapGomba().deleteFonal(gf);
            }
            clearKapcsolodoFonalak();
        }
    }

    public void setVanGombaTest(boolean val){
        this.vanGombaTestTektonon = val;
    }

    public boolean getVanGombaTest(){
        return this.vanGombaTestTektonon;
    }

    public Gomba getGomba(){
        return this.tektononLevoGomba;
    }

    List<BaseSpora> getSporaLista() {
        return sporaLista;
    }

    public int getFokszam(){
        return this.fonalFokszam;
    }

    public void addLatogatottsag() {
        this.rovarLatogatottsag++;
    }

    public int getLatogatottsag() {
        return this.rovarLatogatottsag;
    }

    public List<GombaFonal> getKapcsolodoFonalak() {
        return kapcsolodoFonalak;
    }

    public boolean addKapcsolodoFonalak(GombaFonal gf) {
        //TODO maxegyfonal ellenőrzése
        if(maxEgyFonal) {
            if(kapcsolodoFonalak.size() >= 1) {
                return false;
            }
        }

        if(!kapcsolodoFonalak.contains(gf)){
            this.kapcsolodoFonalak.add(gf);
            this.fonalFokszam = kapcsolodoFonalak.size();
        }

        return true;
    }

    public boolean setRovar(Rovar r) {
        if(tektononLevoRovar!=null){    //Ha van már rovar a tektonon
            if(r == null){  //HA ezt null ra akarjuk állítani
                rovarLatogatottsag++;
                tektononLevoRovar = null;
                return true;
            } else {
                return false;   //már van egy rovar a tektonon, tekton nem üres
            }
        } else {
            this.tektononLevoRovar = r;
            return true;
        }
    }

    public Rovar getRovar(){
        return this.tektononLevoRovar;
    }

    public void setGomba(Gomba g){
        if(g != null) {
            if(!vanGombaTestTektonon && this.tektononLevoGomba == null && !gtGatlo) {
                this.tektononLevoGomba = g;
                vanGombaTestTektonon = true;
            }
        } else {
            this.tektononLevoGomba = null;
            //TODO, ez bezavarhat a hatásba, mert ha GOmbaTestGátló akkor nem kene ezt itt visszaállítani
            vanGombaTestTektonon = false;
        }
    }

    public List<Tekton> getSzomszedosTektonok(){
        return szomszedosTektonok;
    }

    public void removeKapcsolodoFonal(GombaFonal gf) {
        if(this.kapcsolodoFonalak.contains(gf) && !this.defendFonalak){ //TODO ellenőrizni, hogy nem kavar e be a defend a dologba
            this.kapcsolodoFonalak.remove(gf);
            this.fonalFokszam = kapcsolodoFonalak.size();
        } else {
            //TODO jelezni, hogy nem volt benne a törlendő ha kell
            return;
        }
    }

    public void removeSporak(int val){
        if(this.sporaLista.size() >= val){
            //Ekkor eltávolítható val-nyi
            sporaLista.subList(0, val).clear();
        } else {
            sporaLista.clear();
        }
    }

    public void addSzomszedosTekton(Tekton t) {
        if(!szomszedosTektonok.contains(t)){
            szomszedosTektonok.add(t);
        }
    }

    public boolean removeSzomszedosTekton(Tekton t){
        if(t.getSzomszedosTektonok().contains(this)){
            t.getSzomszedosTektonok().remove(this);
        }
        return szomszedosTektonok.remove(t);
    }

    public void setDefendFonalak(boolean val){
        this.defendFonalak = val;
    }

    public boolean isDefendFonalak(){
        return this.defendFonalak;
    }

    public void setMaxEgyFonal(boolean val){
        this.maxEgyFonal = val;
    }

    public boolean isMaxEgyFonal(){
        return this.maxEgyFonal;
    }

    public TektonHatas getTektonHatas() {
        return tektonHatas;
    }

    public int getId() {
        return id;
    }

    @Override
    public void elpusztul() {
        if(tektononLevoGomba != null) {
            this.tektononLevoGomba.elpusztul();
        }
        this.sporaLista.clear();
        this.kapcsolodoFonalak.clear(); //Ahol használjuk előtte a fonalak felszívása már kezeli magukat a fonalakat
        Field.getTektonList().remove(this);
        for (Tekton t : Field.getTektonList()){
            t.getSzomszedosTektonok().remove(this);
        }
    }

    public static boolean ketTektonFonallalOsszekotott(Tekton t1, Tekton t2){
        //Fonallal összekötött-e a két tekton
        List<GombaFonal> celFonalak = t1.getKapcsolodoFonalak();
        List<GombaFonal> thisFonalak = t2.getKapcsolodoFonalak();

        boolean vanKozosFonal = false;

        for (GombaFonal f1 : celFonalak) {
            for (GombaFonal f2 : thisFonalak) {
                if (f1.getID() == f2.getID()) {
                    vanKozosFonal = true;
                    break;
                }
            }
            if (vanKozosFonal) break;
        }

        return vanKozosFonal;
    }

    public boolean isGtGatlo(){
        return gtGatlo;
    }

    public void setGtGatlo(boolean val){
        this.gtGatlo = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\trovarLatogatottsag: ").append(this.rovarLatogatottsag).append("\n");
        sb.append("\tfonalFokszam: ").append(this.fonalFokszam).append("\n");
        sb.append("\tsporaListaSize: ").append(this.sporaLista.size()).append("\n");
        sb.append("\tszomszedosTektonokSize: ").append(this.szomszedosTektonok.size()).append("\n");

        if(defendFonalak) {
            sb.append("\tdefendFonalak: 1\n");
        } else {
            sb.append("\tdefendFonalak: 0\n");
        }

        if (tektononLevoGomba != null) {
            sb.append("\tgombatest: 1 - sporaszam: ")
                    .append(tektononLevoGomba.getGombatest().getSporakeszlet())
                    .append(" - fonalszam: ")
                    .append(tektononLevoGomba.getFonalKeszlet())
                    .append(" - szint: ")
                    .append(tektononLevoGomba.getGombatest().getSzint())
                    .append(" - szorasCount:")
                    .append(tektononLevoGomba.getGombatest().getSzorasCount())
                    .append("\n");
        } else {
            sb.append("\tgombatest: 0\n");
        }

        if (tektononLevoRovar == null) {
            sb.append("\trovar: 0\n");
        } else {
            sb.append("\trovar: 1 - ID: ").append(tektononLevoRovar.getID()).append("\n");
        }

        return sb.toString();
    }

    public static void connectSzomszedok(Tekton t1, Tekton t2) {
        t1.addSzomszedosTekton(t2);
        t2.addSzomszedosTekton(t1);
    }

    public void setRovarLatogatottsag(int i) {
        this.rovarLatogatottsag = i;
    }

}
