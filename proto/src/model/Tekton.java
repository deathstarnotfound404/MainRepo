package model;
import java.util.*;

public class Tekton {
    private List<Tekton> szomszedok;
    private TektonHatas hatas;
    private int id;
    private static int idCounter;

    public Tekton() {}
    public List<Tekton> getSzomszedok() { return null; }
    public void setSzomszedok(List<Tekton> szomszedok) {}
    public void setHatas(TektonHatas hatas) {}
    public TektonHatas getHatas() { return hatas; }
    public int getId() { return id; }
}
