package test;

import model.*;

import java.util.*;

public class Teszt_14 extends BaseTest{

    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    //growGombaTest -t1 -g0 -gsz0

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Gomba> gombaList = new ArrayList<>();
    private List<Gombasz> gombaszList = new ArrayList<>();


    @Override
    protected void inicializalas() {
        Gombasz gsz0 = new Gombasz("TesztGombasz");
        gombaszList.add(gsz0);

        field.addGombasz(gsz0);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new TektonHatas());
        tektonList.add(t1);
        tektonList.add(t2);

        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);

        t2.addSpora(new Spora());
        t2.addSpora(new Spora());
        t2.addSpora(new Spora());
        t2.addSpora(new Spora());
        t2.addSpora(new Spora());

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));

        Gomba gomba = new Gomba(t1, gsz0, 0);
        gsz0.addGomba(gomba);
        gombaList.add(gomba);

        GombaFonal gf = new GombaFonal(gomba, t1, t2);

        gomba.addFonal(gf);

        //Tekton init
        t1.addKapcsolodoFonalak(gf);
        t2.addKapcsolodoFonalak(gf);

        elvartParancsok.add("growGombaTest -t1 -g0 -gsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "growGombaTest":
                int tektonNum = Integer.parseInt(parts[1].substring(2)); // "-t1" -> 1
                int gombaNum = Integer.parseInt(parts[2].substring(2)); // "-g0" -> 1
                int gombaszNum = Integer.parseInt(parts[3].substring(4)); // "-gsz0" -> 1

                // Kikeressük az objektumokat
                Tekton celTekton = tektonList.get(tektonNum);
                Gomba gomba = gombaList.get(gombaNum);
                Gombasz gombasz = gombaszList.get(gombaszNum);

                if(celTekton != null && gomba != null && gombasz != null) {
                    if(gombasz.gombatestNovesztes(celTekton, false)) {
                        celTekton.hatasKifejtes();
                        log.append("Success: Gomba growth completed.\n" + "Target: t").append(tektonNum).append("\n").append("Gomba: g").append(gombaNum).append("\n").append("Gombasz: gsz").append(gombaszNum).append("\n\n");
                    }
                    else log.append("Incomplete: Gomba growth incomplete.\n" + "Target: t").append(tektonNum).append("\n").append("Gomba: g").append(gombaNum).append("\n").append("Gombasz: gsz").append(gombaszNum).append("\n\n");
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
        log.append("[Test_14] - Gombatest Növesztés\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_14 - Bemenet]\n\t> " + kovetkezoElvart);

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

    private void tesztWriteOut() {
        log.append("[Test_14] - Teszt vége\n");
        saveTestResult(log, "14");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
