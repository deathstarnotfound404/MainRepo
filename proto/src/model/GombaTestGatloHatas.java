package model;

public class GombaTestGatloHatas extends TektonHatas {
    public GombaTestGatloHatas() {
        super();
    }

    @Override
    public String hatas() {
        super.tekton.setVanGombaTest(true); //TODO ELL. HOGY EZT NEM SÉRTJÜK-E VALAHOL AHOL LÉTREHOZUNK GOMBÁT
        return "GombaTestGatlo";
    }
}
