package model;
import java.util.*;

public class Gomba implements IDestroyable {
    private List<List<GombaFonal>> fonalLista;
    private int fonalKeszlet = 0;
    private Tekton tekton;
    private GombaTest gombaTest;
    protected int id;
    protected static int idCounter;

    public Gomba(Tekton t) {}
    public List<List<GombaFonal>> getFonalLista() { return null; }
    public void setFonalKeszlet(int val) {}
    public int getFonalKeszlet() { return 0; }
    public GombaTest getGombatest() { return null; }
    public int sporaTermeles() { return 0; }
    public void gombatestSzintlepes() {}
    public List<GombaFonal> fonalFolytonossagVizsgalat() { return null; }
    public void fonalFelszivodas(Tekton t) {}
    public void setGombaTest(GombaTest gt) {}
    public void deleteFonal(GombaFonal gf) {}
    public boolean szor(Tekton celTekton, GombaTest gt) { return false; }
    public void addFonal(GombaFonal gf) {}
    public boolean decreaseFonalkeszlet() { return false; }
    public void increaseFonalkeszlet(int val) {}
    public Tekton getTekton() { return null; }
    public void setTekton(Tekton t) {}
    public void nemFolytonosFonalTorles() {}
    public void elpusztul() {}
}