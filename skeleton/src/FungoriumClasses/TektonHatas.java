package FungoriumClasses;

public class TektonHatas {

    private Tekton tekton;

    public TektonHatas() {
        System.out.println("<<<return TektonHatas()");
    }

    public String hatas() {
        System.out.println("<<<return hatas()");
        return null;
    }

    public void setTekton(Tekton t) {
        this.tekton = t;
        System.out.println("<<<return setTekton()");
    }

    public Tekton getTekton() {
        System.out.println("<<<return getTekton()");
        return null;
    }
}
