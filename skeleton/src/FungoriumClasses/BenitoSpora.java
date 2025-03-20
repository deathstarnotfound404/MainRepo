package FungoriumClasses;

public class BenitoSpora extends BaseSpora {
    //BenitoSpora konstruktora
    public BenitoSpora() {
        super();
        System.out.println("<<<return BenitoSpora()");
    }

    //a spora hatasanak kifejtese
    @Override
    public void hatas(Rovar r) {
        System.out.println("<<<return hatas()");
    }
}
