package model;

import java.util.List;

public class Rovar {
    private Tekton helyzet;
    private int tapanyag = 0;
    private int evesHatekonysag = 1;
    private boolean tudVagni = true;
    private boolean maxFogyasztas = false;
    private Rovarasz rovarasz;
    private int id;

    public Rovar() {
        id = Field.genID();
    }

    public void addTapanyag(int val) {
        tapanyag += val;
    }

    public int getEvesHatekonysag() {
        return evesHatekonysag;
    }

    public Tekton getHelyzet() {
        return helyzet;
    }

    public int getID() {
        return id;
    }

    public int getTapanyag() {
        return tapanyag;
    }

    public boolean getTudVagni() {
        return tudVagni;
    }

    public void kepessegekAlaphelyzetbe() {
        evesHatekonysag = 1;
        tudVagni = true;
        maxFogyasztas = false;
    }

    private Tekton getUresSzomszedosTekton(){
        List<Tekton> szabadHelyek = helyzet.getSzomszedosTektonok();
        Tekton szabadTekton = null;
        for (Tekton t : szabadHelyek) {
            if(!t.vanRovarATektonon()){
                szabadTekton = t;
                break;
            }
        }
        return szabadTekton;
    }
    public Rovar klonozas() {
        //TODO ellenőrizni, hogy a beállításokat itt kell megtenni vagy ahol visszaadjuk a ROvart
        Tekton szabadTekton = getUresSzomszedosTekton();
        if(szabadTekton != null){
            Rovar klonozas = new Rovar();
            klonozas.helyzet = szabadTekton;
            klonozas.rovarasz = this.rovarasz;
            this.rovarasz.addRovar(klonozas, szabadTekton);
            return klonozas;
        } else {
            return null;
        }
    }

    public void lep(Tekton celTekton) {
        //TODO
    }

    public boolean isMaxFogyasztas() {
        return maxFogyasztas;
    }

    public Rovarasz getRovarasz() {
        return rovarasz;
    }

    public void setHelyzet(Tekton t) {
        helyzet = t;
    }

    public void setEvesHatekonysag(int val) {
        evesHatekonysag = val;
    }

    public void setMaxFogyasztas(boolean val) {
        maxFogyasztas = val;
    }

    public void setRovarasz(Rovarasz rsz) {
        rovarasz = rsz;
    }

    public void setTapanyag(int val) {
        tapanyag = val;
    }

    public void setTudVagni(boolean val) {
        tudVagni = val;
    }

    public void sporaEves() {
        //TODO
    }

    public void vag(GombaFonal gf) {
        //TODO
    }
}
