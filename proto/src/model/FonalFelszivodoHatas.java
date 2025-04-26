package model;

public class FonalFelszivodoHatas extends TektonHatas {

    public FonalFelszivodoHatas() {
        super();
        hatasEsemenyfuggo = true;
    }

    @Override
    public String hatas() {
        //Fonalak felszívása az adott tektonról
        if(super.tekton.getLatogatottsag()%5 == 0 && super.tekton.getLatogatottsag() != 0) {
            super.tekton.fonalakFelszivasa();
            return "Felszivas";
        } else {
            return "NincsFonalFelszivas";
        }
    }
}
