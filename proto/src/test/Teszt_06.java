package test;

import model.*;

import java.util.*;

public class Teszt_06 extends BaseTest{
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

        tektonList.add(t0);
        tektonList.add(t1);
        tektonList.add(t2);

        t0.getTektonHatas().setTekton(t0);
        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);

        if (!t0.getTektonHatas().isHatasEsemenyfuggo()) {
            t0.hatasKifejtes();
        }
        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }

        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }

        field.addTekton(t0);
        field.addTekton(t1);
        field.addTekton(t2);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(1), Field.getTektonList().get(2));

        Gomba gomba = new Gomba(t0, g, 0);
        gomba.getGombatest().setSzint(1);
        Gomba gomba2 = new Gomba(t1, g, 0);
        gomba2.getGombatest().setSzint(2);
        Gomba gomba3 = new Gomba(t2, g, 0);
        gomba3.getGombatest().setSzint(3);
        g.addGomba(gomba);
        g.addGomba(gomba2);
        g.addGomba(gomba3);
        gombaList.add(gomba);
        gombaList.add(gomba2);
        gombaList.add(gomba3);

        elvartParancsok.add("produceSpora");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "produceSpora":
                for (Gombasz gsz : gombaszList) {
                    gsz.sporaTermelesAll();
                }
                log.append("Success: Spore production completed.\n");
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
        log.append("[Test_06] - Spóra Termelés\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_06 - Bemenet]\n\t> " + kovetkezoElvart);

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
        log.append("[Test_06] - Teszt vége\n");
        saveTestResult(log, "06");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
