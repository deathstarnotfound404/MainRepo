package FungoriumClasses;

public class GyorsitoSpora extends BaseSpora {
    //GyorsitoSpora konstruktora
    public GyorsitoSpora() {
        super();
        System.out.println("<<<return GyorsitoSpora()");
    }

    //a spora hatasanak implementalasa
    @Override
    public void hatas(Rovar r) {
        System.out.println("<<<return hatas()");
    }
}
