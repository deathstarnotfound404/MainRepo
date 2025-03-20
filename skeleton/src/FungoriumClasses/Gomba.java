package FungoriumClasses;

import java.util.ArrayList;
import java.util.List;

public class Gomba implements IDestroyable {
    private int fonalKeszlet = 0;
    private List<List<Gombafonal>> fonalLista;
    private GombaTest GombaTest;

    public Gomba(Tekton t) {
        fonalLista = new ArrayList<List<Gombafonal>>();
        System.out.println("<<<return Gomba()");
    }

    public void setTekton(Tekton t) {
        System.out.println("<<<return setTekton()");
    }

    public Tekton getTekton() {
        System.out.println("<<<return getTekton()");
        return null;
    }

    public void setFonalKeszlet(int val) {
        System.out.println("<<<return setFonalKeszlet()");
    }

    public int getFonalKeszlet() {
        System.out.println("<<<return getFonalKeszlet()");
        return fonalKeszlet;
    }

    public void setGombaTest(GombaTest t) {
        System.out.println("<<<return setGombaTest()");
    }

    public GombaTest getGombaTest() {
        System.out.println("<<<return getGombaTest()");
        return GombaTest;
    }

    public List<List<Gombafonal>> getFonalLista() {
        System.out.println("<<<return getFonalLista()");
        return fonalLista;
    }

    public void sporaTermeles() {
        System.out.println("<<<return sporaTermeles()");
    }

    public void gombatestSzintlepes() {
        System.out.println("<<<return gombatestSzintlepes()");
    }

    public List<Gombafonal> fonalFolytonossagVizsgalat() {
        System.out.println("<<<return fonalFolytonossagVizsgalat()");
        return null;
    }

    public void fonalFelszivodas(Gombafonal gf) {
        System.out.println("<<<return fonalFelszivodas()");
    }

    public void deleteFonal(Gombafonal gf) {
        System.out.println("<<<return deleteFonal()");
    }

    public boolean szor(Tekton celTekton, GombaTest gt) {
        System.out.println("<<<return szor()");
        return false;
    }

    public void addFonal(Gombafonal gf) {
        System.out.println("<<<return addFonal()");
    }

    public boolean decreaseFonalKeszlet() {
        System.out.println("<<<return decreaseFonalKeszlet()");
        return false;
    }

    public void increaseFonalKeszlet(int val) {
        System.out.println("<<<return increaseFonalKeszlet()");
    }

    @Override
    public void elpusztul() {
        System.out.println("<<<return elpusztul()");
    }
}
