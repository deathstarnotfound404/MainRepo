package FungoriumClasses;

public class TektonHatas {

    private Tekton tekton;

    public TektonHatas() {

    }

    public String hatas() {
        return "Base";  //Alapértelmezett működés
    }

    public void setTekton(Tekton t) {
        this.tekton = t;
    }

    public Tekton getTekton() {
        return this.tekton;
    }
}
