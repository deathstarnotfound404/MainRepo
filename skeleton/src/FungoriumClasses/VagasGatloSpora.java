package FungoriumClasses;

public class VagasGatloSpora extends BaseSpora {
    //VagasGatloSpora konstruktora
    public VagasGatloSpora() {
        super();
        System.out.println("<<<return VagasGatloSpora()");
    }

    //hatas kifejtese
    @Override
    public void hatas(Rovar r) {
        System.out.println("<<< return hatas()");
    }
}
