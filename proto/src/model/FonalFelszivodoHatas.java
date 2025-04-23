package model;

public class FonalFelszivodoHatas extends TektonHatas {

    public FonalFelszivodoHatas() {
        super();
    }

    @Override
    public String hatas() {
        //Fonalak felszívása az adott tektonról
        super.tekton.fonalakFelszivasa();
        return "Felszivas";
    }
}
