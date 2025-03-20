package FungoriumClasses;


public class LassitoSpora extends BaseSpora {
    public LassitoSpora() {
        super();
        System.out.println("<<<return LassitoSpora()");
    }

    //hatas kifejtese
    @Override
    public void hatas(Rovar r) {
        System.out.println("<<<retrun hatas()");
    }
}
