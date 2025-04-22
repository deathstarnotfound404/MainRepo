package model;

public class FonalGatloHatas extends TektonHatas {
    public FonalGatloHatas() {
        super();
    }

    @Override
    public String hatas() {
        int fokszam = super.tekton.getFokszam();
        if(fokszam == 0) {
            return "NincsFonalGatlo";
        } else {
            return "FonalGatlo";
        }
    }
}