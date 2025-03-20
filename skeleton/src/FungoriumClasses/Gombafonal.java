package FungoriumClasses;

public class Gombafonal implements IDestroyable {
    private Gomba AlapGomba;
    private Tekton startTekton;
    private Tekton celTekton;

    public Gombafonal(Tekton startTekton, Tekton celTekton) {
        System.out.println("<<<return Gombafonal()");
    }

    public Gomba getAlapGomba() {
        System.out.println("<<<return getAlapGomba()");
        return null;
    }

    public Tekton getStartTekton() {
        System.out.println("<<<return getStartTekton()");
        return null;
    }

    public Tekton getCelTekton() {
        System.out.println("<<<return getCelTekton()");
        return null;
    }

    public boolean connectedToAlapGomba() {
        System.out.println("<<<return connectedToAlapGomba()");
        return false;
    }

    @Override
    public void elpusztul() {
        System.out.println("<<<return elpusztul()");
    }
}
