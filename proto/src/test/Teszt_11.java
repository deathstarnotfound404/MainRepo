package test;

import model.*;

import java.util.*;

public class Teszt_11 extends BaseTest{
    //Parancs > spreadSpora -t1 -g0 gsz0; spreadSpora -t2 -g0 gsz0; exit
    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Gomba> gombaList = new ArrayList<>();
    private List<Gombasz> gombaszList = new ArrayList<>();

    @Override
    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        gombaszList.add(g);

        field.addGombasz(g);

        Tekton t0 = new Tekton(new TektonHatas());
        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new TektonHatas());
        Tekton t3 = new Tekton(new TektonHatas());
        Tekton t4 = new Tekton(new TektonHatas());
        Tekton t5 = new Tekton(new TektonHatas());
        Tekton t6 = new Tekton(new TektonHatas());

        tektonList.add(t0);
        tektonList.add(t1);
        tektonList.add(t2);
        tektonList.add(t3);
        tektonList.add(t4);
        tektonList.add(t5);
        tektonList.add(t6);

        t0.getTektonHatas().setTekton(t0);
        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);
        t3.getTektonHatas().setTekton(t3);
        t4.getTektonHatas().setTekton(t4);
        t5.getTektonHatas().setTekton(t5);
        t6.getTektonHatas().setTekton(t6);

        for(int i = 0; i < 7; ++i) {
            if (!tektonList.get(i).getTektonHatas().isHatasEsemenyfuggo()) {
                tektonList.get(i).hatasKifejtes();
            }
        }

        field.addTekton(t0);
        field.addTekton(t1);
        field.addTekton(t2);
        field.addTekton(t3);
        field.addTekton(t4);
        field.addTekton(t5);
        field.addTekton(t6);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(1), Field.getTektonList().get(2));
        Tekton.connectSzomszedok(Field.getTektonList().get(2), Field.getTektonList().get(3));
        Tekton.connectSzomszedok(Field.getTektonList().get(3), Field.getTektonList().get(4));
        Tekton.connectSzomszedok(Field.getTektonList().get(4), Field.getTektonList().get(5));
        Tekton.connectSzomszedok(Field.getTektonList().get(5), Field.getTektonList().get(6));

        Gomba g1 = new Gomba(t0, g, 6);
        Gomba g2 = new Gomba(t2, g, 6);
        Gomba g3 = new Gomba(t4, g, 6);
        g.addGomba(g1);
        g.addGomba(g2);
        g.addGomba(g3);
        gombaList.add(g1);
        gombaList.add(g2);
        gombaList.add(g3);

        //Céltektonon van spora
        g.fonalVasarlas(g1);
        g.fonalVasarlas(g2);
        g.fonalVasarlas(g3);

        GombaFonal gf1 = new GombaFonal(g2, t2, t3);
        g2.addFonal(gf1);

        t2.addKapcsolodoFonalak(gf1);
        t3.addKapcsolodoFonalak(gf1);

        elvartParancsok.add("growFonal -st0 -ct1 -g0 -gsz0");
        elvartParancsok.add("growFonal -st2 -ct3 -g1 -gsz0");
        elvartParancsok.add("growFonal -st4 -ct6 -g2 -gsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "growFonal":
                int stratTektonNum = Integer.parseInt(parts[1].substring(3)); // "-st0" -> 0
                int celTektonNum = Integer.parseInt(parts[2].substring(3));    // "-ct1" -> 1
                int gombaNum = Integer.parseInt(parts[3].substring(2)); // "-g0" -> 0
                int gombaszNum = Integer.parseInt(parts[4].substring(4)); // "-gsz0" -> 0

                // Kikeressük az objektumokat
                Gomba gomba = gombaList.get(gombaNum);
                Tekton startTekton = tektonList.get(stratTektonNum);
                Tekton celTekton = tektonList.get(celTektonNum);
                Gombasz gombasz = gombaszList.get(gombaszNum);

                if (gomba != null && celTekton != null && gombasz != null && startTekton != null) {
                    if (gombasz.gombafonalIranyitas(gomba, startTekton, celTekton, false)) {
                        log.append("GombaFonal growth completed.\n").append("Source: t").append(stratTektonNum).append(" -> Target: t").append(celTektonNum).append("\n").append("Gomba Network: g").append(gombaNum).append(" (Managed by: gsz)").append(gombaszNum).append("\n\n");
                    } else {
                        log.append("GombaFonal growth incomplete.\n\n");
                    }
                } else {
                    log.append("[HIBA] Nem található rovar/tekton/rovarász azonosító!\n");
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
        log.append("[Test_11] - Gombafonal Növesztés - Irányítás, Fonal nem lerakható\n\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_11 - Bemenet]\n\t> " + kovetkezoElvart);

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
        log.append("[Test_11] - Teszt vége\n");
        saveTestResult(log, "11");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
