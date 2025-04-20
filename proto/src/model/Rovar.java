package model;

public class Rovar {
    private Tekton helyzet;
    private int tapanyag = 0;
    private int evesHatekonysag = 1;
    private boolean tudVagni = true;
    private boolean maxFogyasztas;
    private Rovarasz rovarasz;
    private int rovarID;

    public Rovar() {}
    public void addTapanyag(int val) {}
    public int getEvesHatekonysag() { return evesHatekonysag; }
    public Tekton getHelyzet() { return helyzet; }
    public int getRovarID() { return rovarID; }
    public int getTapanyag() { return tapanyag; }
    public boolean getTudVagni() { return tudVagni; }
    public void kepessegekAlaphelyzetbe() {}
    public Rovar klonozas() { return null; }
    public void lep(Tekton celTekton) {}
    public boolean getMaxFogyasztas() { return maxFogyasztas; }
    public Rovarasz getRovarasz() { return rovarasz; }
    public void setHelyzet(Tekton t) {}
    public void setEvesHatekonysag(int val) {}
    public void setMaxFogyasztas(boolean val) {}
    public void setRovarID(int val) {}
    public void setRovarasz(Rovarasz rsz) {}
    public void setTapanyag(int val) {}
    public void setTudVagni(boolean val) {}
    public void sporaEves() {}
    public void vag(GombaFonal gf) {}
}
