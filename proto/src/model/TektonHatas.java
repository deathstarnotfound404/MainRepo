package model;
import java.util.*;

public class TektonHatas {
    protected Tekton tekton;
    private static final Random rnd = new Random();

    public TektonHatas() {}

    public String hatas() {
        return "Base";
    }

    public void setTekton(Tekton t) {
        this.tekton = t;
    }

    public Tekton getTekton() {
        return tekton;
    }

    public static TektonHatas generateRandomTektonHatas(){
        int valasztas = rnd.nextInt(5);     //5 féle hatas létezik

        switch (valasztas){
            case 0:
                return new TektonHatas();

            case 1:
                return new FonalDefenderHatas();

            case 2:
                return new FonalFelszivodoHatas();

            case 3:
                return new FonalGatloHatas();

            case 4:
                return new GombaTestGatloHatas();

            default:
                return new TektonHatas();
        }
    }
}
