package model;

public class FonalGatloHatas extends TektonHatas {
    public FonalGatloHatas() {
        super();
    }

    @Override
    public String hatas() {
        int fokszam = super.tekton.getFokszam();
        if(fokszam < 1) {
            super.tekton.setMaxEgyFonal(false);
            return "NincsFonalGatlo";
        } else {
            super.tekton.setMaxEgyFonal(true);
            return "FonalGatlo";
        }
    }
}
