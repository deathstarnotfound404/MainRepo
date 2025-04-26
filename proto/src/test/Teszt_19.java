package test;

import model.*;

import java.util.*;

public class Teszt_19 extends BaseTest{
    private final StringBuilder log = new StringBuilder();
    private final Field field = new Field();  // Teszt saját Field-je
    private final Queue<String> elvartParancsok = new LinkedList<>();

    private List<Tekton> tektonList = new ArrayList<>();
    private List<Rovarasz> rovaraszList = new ArrayList<>();
    private List<Rovar> rovarList = new ArrayList<>();

    @Override
    protected void inicializalas() {
        Gombasz g = new Gombasz("TesztGombasz");
        Rovarasz r = new Rovarasz("TesztRovarasz");
        rovaraszList.add(r);

        field.addGombasz(g);
        field.addRovarasz(r);

        Tekton t1 = new Tekton(new TektonHatas());
        Tekton t2 = new Tekton(new TektonHatas());
        Tekton t3 = new Tekton(new TektonHatas());
        tektonList.add(t1);
        tektonList.add(t2);
        tektonList.add(t3);

        t1.getTektonHatas().setTekton(t1);
        t2.getTektonHatas().setTekton(t2);
        t3.getTektonHatas().setTekton(t3);

        if (!t1.getTektonHatas().isHatasEsemenyfuggo()) {
            t1.hatasKifejtes();
        }
        if (!t2.getTektonHatas().isHatasEsemenyfuggo()) {
            t2.hatasKifejtes();
        }
        if (!t3.getTektonHatas().isHatasEsemenyfuggo()) {
            t3.hatasKifejtes();
        }

        field.addTekton(t1);
        field.addTekton(t2);
        field.addTekton(t3);

        Tekton.connectSzomszedok(Field.getTektonList().get(0), Field.getTektonList().get(1));
        Tekton.connectSzomszedok(Field.getTektonList().get(1), Field.getTektonList().get(2));

        Gomba gomba = new Gomba(t1, g, 0);
        g.addGomba(gomba);

        Rovar rovar = new Rovar();
        rovarList.add(rovar);
        rovar.setRovarasz(r);
        r.addRovar(rovar, t1);
        rovar.setHelyzet(t1);
        t1.setRovar(rovar);

        GombaFonal gf = new GombaFonal(gomba, t1, t2);
        GombaFonal gf2 = new GombaFonal(gomba, t2, t3);
        gomba.addFonal(gf);
        gomba.addFonal(gf2);

        t1.addKapcsolodoFonalak(gf);
        t2.addKapcsolodoFonalak(gf);
        t2.addKapcsolodoFonalak(gf2);
        t3.addKapcsolodoFonalak(gf2);

        t2.addSpora(new LassitoSpora());
        for(int i = 0; i< 4; ++i) {
            t3.addSpora(new Spora());
        }

        elvartParancsok.add("moveRovar -r0 -t1 -rsz0");
        elvartParancsok.add("moveRovar -r0 -t2 -rsz0");
        elvartParancsok.add("exit");
    }

    @Override
    protected void parancsFeldolgozas(String parancs) {
        String[] parts = parancs.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "moveRovar":
                int rovarNum = Integer.parseInt(parts[1].substring(2)); // "-r0" -> 0
                int tektonNum = Integer.parseInt(parts[2].substring(2)); // "-t1" -> 1
                int rovaraszNum = Integer.parseInt(parts[3].substring(4)); // "-rsz1" -> 1

                // Kikeressük az objektumokat
                Rovar rovar = rovarList.get(rovarNum);
                Tekton celTekton = tektonList.get(tektonNum);
                Rovarasz rovarasz = rovaraszList.get(rovaraszNum);

                if (rovar != null && celTekton != null && rovarasz != null) {
                    if (rovarasz.rovarIranyitas(rovar, celTekton)) {
                        log.append("Success: Rovar movement completed.\n" + "Rovar: ").append(rovarNum).append(" → Target: ").append(tektonNum).append("\n").append("Managed by: rsz").append(rovaraszNum).append("\n\n");
                        if(rovar.sporaEves()) {
                            log.append("Success: Rovar successfully consumed Spora.\n\n");
                        } else {
                            log.append("Incomplete: Rovar not consumed Spora.\n");
                        }
                        celTekton.hatasKifejtes();

                        List<Tekton> modositando = new ArrayList<>(Field.getTektonList());
                        for (Tekton t : modositando) {
                            if (t.tektonTores()){
                                log.append("Success: Tekton kettétörés\nTekton: ").append(tektonNum).append("\n");
                            }
                        }
                    } else {
                        log.append("Error: Rovar movement incomplete.\n");
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
        log.append("[Test_19] - Spora Elfogyasztása - Lassito Spora\n" +
                "-------------------------------------------------------------------------------\n\n");

        inicializalas();

        Scanner scanner = new Scanner(System.in);

        log.append("Kezdő állapot:\n");
        log.append(field.printGameState());

        log.append("\nKimenet:\n\n");

        while (!elvartParancsok.isEmpty()) {
            String kovetkezoElvart = elvartParancsok.peek();
            System.out.println("[Test_19 - Bemenet]\n\t> " + kovetkezoElvart);

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
        log.append("[Test_19] - Teszt vége\n");
        saveTestResult(log, "19");
    }

    @Override
    public String getResult() {
        return log.toString();
    }
}
