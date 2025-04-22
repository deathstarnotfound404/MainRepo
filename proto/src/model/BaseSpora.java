package model;
import java.util.*;

public abstract class BaseSpora implements IDestroyable {
    protected int tapanyag = 1;
    protected int id;
    private static final Random rnd = new Random();

    protected BaseSpora() {
        // konstruktor
        id = Field.genID();
    }

    public abstract void hatas(Rovar r);

    public static BaseSpora generateRandomSpora(){
        int valasztas = rnd.nextInt(6);     //6 féle spóra létezik

        switch (valasztas){
            case 0:
                return new Spora();

            case 1:
                return new KlonozoSpora();

            case 2:
                return new GyorsitoSpora();

            case 3:
                return new BenitoSpora();

            case 4:
                return new LassitoSpora();

            case 5:
                return new VagasGatloSpora();

            default:
                return new Spora();
        }
    }

    public int getTapanyag() {
        return tapanyag;
    }
    public void setTapanyag(int val) { this.tapanyag = val; }

    public int getId() { return id; }

    public void elpusztul() {
        //TODO
    }
}