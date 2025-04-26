package model;

import java.util.List;

public class Rovar implements IDestroyable{
    private Tekton helyzet;
    private int tapanyag = 0;
    private double evesHatekonysag = 1;
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

    public double getEvesHatekonysag() {
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
            Rovar rovarKlon = new Rovar();
            rovarKlon.helyzet = szabadTekton;
            rovarKlon.rovarasz = this.rovarasz;
            this.rovarasz.addRovar(rovarKlon, szabadTekton);
            return rovarKlon;
        } else {
            return null;
        }
    }

    public boolean lep(Tekton celTekton) {
        List<Tekton> szomszedLista = this.helyzet.getSzomszedosTektonok();
        //Nem léphet ugyan oda
        if(celTekton.getId() == this.id) {
            System.out.println("Hiba: Nem léphet ugyan oda, ahol van!");
            return false;
        }

        //Ha a két tekton nem szomszédosak
        if(!szomszedLista.contains(celTekton) && !celTekton.getSzomszedok().contains(this.helyzet)){
            System.out.println("Hiba: A kiválasztott két tekton nem szomszédos!");
            return false;
        }

        //Fonallal összekötött-e a két tekton
        if(!Tekton.ketTektonFonallalOsszekotott(this.helyzet, celTekton)) {
            System.out.println("Hiba: A kiválasztott két tekton nincs fonalakkal összekötve!");
            return false;
        }

        if(celTekton.vanRovarATektonon() || celTekton.getRovar() != null) {
            System.out.println("Hiba: Már van Rovar a tekton!");
            return false;
        }

        this.helyzet.setRovar(null);    //Add latogatottsag itt megtörtéik
        this.setHelyzet(celTekton);
        celTekton.setRovar(this);
        System.out.println("[moveRovar] Lépés sikeres!");
        return true;
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

    public void setEvesHatekonysag(double val) {
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

    private int elfogyaszthatoMennyisieg(){
        if(maxFogyasztas || evesHatekonysag == 1){
            return this.helyzet.getSporaLista().size();
        }

        if(evesHatekonysag == 0){
            return 0;
        }

        if(evesHatekonysag == 0.5) {
            int size = this.helyzet.getSporaLista().size();
            if(size == 1){
                return 1;
            } else {
                return size/2;
            }
        }

        if(evesHatekonysag == 0.25) {
            int size = this.helyzet.getSporaLista().size();
            if(size == 1){
                return 1;
            } else {
                return size/4;
            }
        }

        return 0;
    }

    public void sporaEves() {
        List<BaseSpora> sporaLista = this.helyzet.getSporaLista();

        int elfogyaszthatoVal = elfogyaszthatoMennyisieg();

        if(sporaLista != null && sporaLista.size() > 0){
            this.addTapanyag(elfogyaszthatoVal);    //Tápanyag hozzáadása
            BaseSpora last = sporaLista.get(elfogyaszthatoVal-1);   //Az utolsó megevett Spóra hatása érvényesül
            last.hatas(this);   //Spora hatas kifejtése a Rovaron

            this.helyzet.getSporaLista().subList(0, elfogyaszthatoVal).clear(); //Sporak törlése

            this.addTapanyag(elfogyaszthatoVal);
        }
    }

    public boolean vag(GombaFonal gf) {
        //TODO -  10sec-ig ne tűnjön el - Majd a Rovarásznál kezelve (az ő hívását időzítjük és ezt hívjuk ha letelt)
        if(this.tudVagni) {
            Tekton t1 = gf.getStartTekton();
            Tekton t2 = gf.getCelTekton();
            Gomba alapGomba = gf.getAlapGomba();

            t1.removeKapcsolodoFonal(gf);
            t2.removeKapcsolodoFonal(gf);

            alapGomba.deleteFonal(gf);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void elpusztul() {
        this.helyzet.setRovar(null);
        this.rovarasz.getRovarLista().remove(this);
    }

    public int getId(){
        return id;
    }
}
