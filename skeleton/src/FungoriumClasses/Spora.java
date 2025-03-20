package FungoriumClasses;

public class Spora extends BaseSpora {
    //Spora konstruktora
    public Spora() {
        super();
        System.out.println("<<<return Spora()");
    }

    //hatast implementalo metodus
    @Override
    public void hatas(Rovar r) {
        System.out.println("<<<return hatas()");
    }
}
