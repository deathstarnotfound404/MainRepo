package model;

public class FonalDefenderHatas extends TektonHatas {
    public FonalDefenderHatas() {
        super();
    }

    @Override
    public String hatas() {
        super.tekton.setDefendFonalak(true);
        return "DefenderHatas";
    }
}
