package FungoriumClasses;

public class Rovarasz {
    private Rovar rovaraszRovarja;
    public Rovarasz() {
        System.out.println("<<<return Rovarasz()");
    }

    public Rovar getRovar() {
        System.out.println("<<<return getRovar()");
        return new Rovar();
    }

    public void addRovar(Rovar r, Tekton t) {
        System.out.println("<<<return addRovar()");
    }

    public int calcAllTapanyagScore() {
        System.out.println("<<<return calcAllTapanyagScore()");
        return 0;
    }

    public boolean rovarIranyitas(Rovar r, Tekton celTekton) {
        System.out.println("<<<return rovarIranyitas()");
        return false;
    }

    public boolean fonalVagas(Rovar r, Gombafonal gf) {
        System.out.println("<<<return fonalVagas()");
        return false;
    }
}
