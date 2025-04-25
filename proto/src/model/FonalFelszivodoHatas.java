package model;

public class FonalFelszivodoHatas extends TektonHatas {

    public FonalFelszivodoHatas() {
        super();
        hatasEsemenyfuggo = true;
    }

    @Override
    public String hatas() {
        //Fonalak felszívása az adott tektonról
        super.tekton.fonalakFelszivasa();
        return "Felszivas";
    }
}
