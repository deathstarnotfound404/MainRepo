package test;

import model.*;

import java.util.*;

public class Teszt_23 extends BaseTest{

    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    //growGombatest -t1 -gsz0

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Gombasz> gombaszList = new ArrayList<>();
    private List<Gomba>  gombaList = new ArrayList<>();

    @Override
    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        //Rovarasz r = new Rovarasz("TesztRovarasz");
        gombaszList.add(g);

        field.addGombasz(g);
        //field.addRovarasz(r);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new GombaTestGatloHatas());
        tektonList.add(t1);
        tektonList.add(t2);

        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);;

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));

        Gomba gomba = new Gomba(t1, g, 6);
        g.addGomba(gomba);
        gombaList.add(gomba);

        GombaFonal gf = new GombaFonal(gomba, t1, t2);

        gomba.addFonal(gf);

        //Tekton init
        t1.addKapcsolodoFonalak(gf);
        t2.addKapcsolodoFonalak(gf);

        Spora spora = new Spora();
        t2.addSpora(spora);
        t2.addSpora(spora);
        t2.addSpora(spora);
        t2.addSpora(spora);
        t2.addSpora(spora);
        t2.addSpora(spora);

        elvartParancsok.add("growGombaTest -t1 -gsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "growGombaTest":
                int tektonNum = Integer.parseInt(parts[1].substring(2)); // "-t1" -> 1
                int gombaszNum = Integer.parseInt(parts[2].substring(4)); // "-gsz0" -> 0

                // Kikeressük az objektumokat
                Tekton celTekton = tektonList.get(tektonNum);
                Gombasz gombasz = gombaszList.get(gombaszNum);

                if (celTekton != null && gombasz != null) {
                    gombasz.gombatestNovesztes(celTekton, false);
                    String ret = celTekton.hatasKifejtes();
                    if(ret == "GombaTestGatlo") {
                        log.append("Success: Tekton hatás\nGombaTestGatloHatas: Gátló\nGombaTest growth failed\n");
                    }
                    else log.append("Incomplete: Tekton hatás\nGombaTestGatloHatas\n");
                } else {
                    log.append("[HIBA] Nem található gomba/tekton/gombász azonosító!\n");
                }
                break;

            case "exit":
                break;

            default:
                log.append("Teszt: Ismeretlen parancs: ").append(parancs).append("\n");
                break;
        }
    }

    @Override
    public void runTest() {
        log.append("[Test_23] - Tekton Hatás - Fonal Gátló Hatás\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_23 - Bemenet]\n\t> " + kovetkezoElvart);

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
        log.append("------------------------------------------------------------------------------\n\nCél állapot\n");
        log.append(field.printGameState());
        tesztWriteOut();
    }

    @Override
    public String getResult() { return log.toString(); }

    private void tesztWriteOut() {
        log.append("[Test_23] - Teszt vége\n");
        saveTestResult(log, "23");
    }
}
