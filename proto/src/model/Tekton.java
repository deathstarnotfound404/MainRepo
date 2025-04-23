package model;
import java.util.*;

public class Tekton {
    private int id;
    private int rovarLatogatottsag = 0;
    private int fonalFokszam = 0;
    private List<BaseSpora> sporaLista;
    private List<GombaFonal> kapcsolodoFonalak;
    private List<Tekton> szomszedosTektonok;
    private boolean defendFonalak = false;
    private boolean maxEgyFonal = false;
    private boolean vanGombaTestTektonon = false;
    private TektonHatas tektonHatas;
    private Gomba tektononLevoGomba;
    private Rovar tektononLevoRovar;

    public Tekton(TektonHatas hatas) {
        id = Field.genID();
        sporaLista = new ArrayList<>();
        kapcsolodoFonalak = new ArrayList<>();
        szomszedosTektonok = new ArrayList<>();
        tektonHatas = hatas;
    }

    public List<Tekton> getSzomszedok() { return null; }

    public String hatasKifejtes() {
        return tektonHatas.hatas();
    }

    public int sporaCount() {
        return sporaLista.size();
    }

    public void addSpora(BaseSpora spora) {
        sporaLista.add(spora);
    }

    public Rovar tektonTores(){
        //TODO

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
        if(tektononLevoGomba != null && !defendFonalak) {
            tektononLevoGomba.fonalFelszivodas(this);
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

    public void addKapcsolodoFonalak(GombaFonal gf) {
        //TODO maxegyfonal ellenőrzése
        if(maxEgyFonal) {
            if(kapcsolodoFonalak.size() >= 1) {
                return;
            }
        }

        if(!kapcsolodoFonalak.contains(gf)){
            this.kapcsolodoFonalak.add(gf);
            this.fonalFokszam = kapcsolodoFonalak.size();
        }
    }

    public void setRovar(Rovar r) {
        if(tektononLevoRovar!=null){
            if(r == null){
                rovarLatogatottsag++;
            } else {
                //TODO jelezni, hogy nem lehetséges a mozgatás
                return;
            }
        }
        this.tektononLevoRovar = r;
    }

    public void setGomba(Gomba g){
        if(!vanGombaTestTektonon && this.tektononLevoGomba == null) {
            this.tektononLevoGomba = g;
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
}
