package test;
import model.*;
import proto.CommandHandler;

import java.util.*;

public class Teszt_01 extends BaseTest {
    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    @Override
    public void runTest() {
        log.append("[Test_01] - Tekton kettétörés\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_01 - Bemenet]\n\t> " + kovetkezoElvart);

            System.out.print("[Teszt parancs] > ");
            String parancs = scanner.nextLine().trim();

            if (parancs.equalsIgnoreCase(kovetkezoElvart)) {
                elvartParancsok.poll();  // Eltávolítjuk a sorból
                parancsFeldolgozas(parancs);
            } else {
                log.append("[HIBA] Hibás parancs: ").append(parancs).append(" (várt: ").append(kovetkezoElvart).append(")\n");
                System.out.println("Nem megfelelő parancs ehhez a teszthez. A helyes parancsot add meg.");
            }
        }

        log.append("\nCél állapot\n");
        log.append(field.printGameState());

        tesztWriteOut();
    }

    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        Rovarasz r = new Rovarasz("TesztRovarasz");
        field.addGombasz(g);
        field.addRovarasz(r);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new TektonHatas());
        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));

        Gomba gomba = new Gomba(t1, g, 0);
        g.addGomba(gomba);

        Rovar rovar = new Rovar();
        rovar.setRovarasz(r);
        r.addRovar(rovar, t1);
        rovar.setHelyzet(t1);
        t1.setRovar(rovar);

        GombaFonal gf = new GombaFonal(gomba, t1, t2);

        gomba.addFonal(gf);

        //Tekton init
        t1.setRovarLatogatottsag(9);
        t1.addKapcsolodoFonalak(gf);
        t2.addKapcsolodoFonalak(gf);

        elvartParancsok.add("moveRovar -r1 -t1 -rsz1");
        elvartParancsok.add("exit");
    }

    protected void parancsFeldolgozas(String parancs) {
        switch (parancs) {
            case "moveRovar -r1 -t1 -rsz1":
                //log.append("Teszt: Rovarász kiválasztva.\n");
                break;
            case "exit":
                log.append("Teszt: Kilépés a tesztből.\n");
                break;
            default:
                log.append("Teszt: Ismeretlen parancs: ").append(parancs).append("\n");
        }
    }

    private void tesztWriteOut() {
        log.append("[Test_01] - Teszt vége\n");
        saveTestResult(log, "01");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
