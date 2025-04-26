package test;
import model.*;
import java.util.*;

public class Teszt_03 extends BaseTest{
    //Parancs > spreadSpora -t2 -g0 gsz0; spreadSpora -t3 -g0 gsz0; exit
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

        tektonList.add(t0);
        tektonList.add(t1);
        tektonList.add(t2);
        tektonList.add(t3);

        t0.getTektonHatas().setTekton(t0);
        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);
        t3.getTektonHatas().setTekton(t3);

        if (!t0.getTektonHatas().isHatasEsemenyfuggo()) {
            t0.hatasKifejtes();
        }
        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }

        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }

        if (!t3.getTektonHatas().isHatasEsemenyfuggo()) {
            t3.hatasKifejtes();
        }

        field.addTekton(t0);
        field.addTekton(t1);
        field.addTekton(t2);
        field.addTekton(t3);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(1), Field.getTektonList().get(2));
        Tekton.connectSzomszedok(Field.getTektonList().get(2), Field.getTektonList().get(3));
        Gomba gomba = new Gomba(t0, g, 6);
        gomba.getGombatest().setSzint(3);
        g.addGomba(gomba);
        gombaList.add(gomba);

        elvartParancsok.add("spreadSpora -t2 -g0 -gsz0");
        elvartParancsok.add("spreadSpora -t3 -g0 -gsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "spreadSpora":
                int tektonNum = Integer.parseInt(parts[1].substring(2)); // "-t0" -> 0
                int gombaNum = Integer.parseInt(parts[2].substring(2)); // "-g0" -> 0
                int gombaszNum = Integer.parseInt(parts[3].substring(4)); // "-gsz0" -> 0

                // Kikeressük az objektumokat
                Gomba gomba = gombaList.get(gombaNum);
                Tekton celTekton = tektonList.get(tektonNum);
                Gombasz gombasz = gombaszList.get(gombaszNum);

                if (gomba != null && celTekton != null && gombasz != null) {
                    if (gombasz.szoras(gomba, celTekton)) {
                        log.append("Success: Spore spreading completed.\n" + "Target: t").append(tektonNum).append("\n").append("Spore Source: gsz").append(gombaszNum).append("\n\n");
                    } else {
                        log.append("Incomplete: Spore spreading incomplete.\n" + "Target: t").append(tektonNum).append("\n").append("Spore Source: gsz").append(gombaszNum).append("\n\n");
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
        log.append("[Test_03] - Spóra szórása - Szórás nem szomszédos Tektonra\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_03 - Bemenet]\n\t> " + kovetkezoElvart);

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
        log.append("[Test_03] - Teszt vége\n");
        saveTestResult(log, "03");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
