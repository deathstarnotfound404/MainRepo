package FungoriumClasses;

public class Gombafonal implements IDestroyable {
    private Gomba AlapGomba;
    private Tekton startTekton;
    private Tekton celTekton;

    public Gombafonal(Tekton startTekton, Tekton celTekton) {
        this.startTekton = startTekton;
        this.celTekton = celTekton;
    }

    void setAlapGomba(Gomba AlapGomba) {
        this.AlapGomba = AlapGomba;
    }

    public Gomba getAlapGomba() {
        return AlapGomba;
    }

    public Tekton getStartTekton() {
        return startTekton;
    }

    public Tekton getCelTekton() {
        return celTekton;
    }

    public boolean connectedToAlapGomba() {
        //TODO szekvencia alapján implementálni
        return false;
    }

    @Override
    public void elpusztul() {
    }
}
